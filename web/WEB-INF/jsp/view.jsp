<%@ page import="ru.javawebinar.basejava.model.TextSection" %>
<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page import="ru.javawebinar.basejava.model.CompaniesSection" %>
<%@ page import="ru.javawebinar.basejava.util.HtmlPrinter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
            <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    </p>
    <hr>
    <p>
        <table>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType, ru.javawebinar.basejava.model.AbstractSection>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="ru.javawebinar.basejava.model.AbstractSection"/>
            <tr>
                <td colspan="2"><h2>${type.title}</h2></td>
            </tr>
            <c:choose>
                <c:when test="${type == 'OBJECTIVE' || type == 'PERSONAL'}">
                    <tr>
                        <td colspan="2">
                            <%=((TextSection) section).getContent()%>
                        </td>
                    </tr>
                </c:when>
                <c:when test="${type == 'ACHIEVEMENT' || type == 'QUALIFICATIONS'}">
                    <tr>
                        <td colspan="2">
                            <ul>
                                <li>
                                    <%=String.join("</li>\n<li>", ((ListSection) section).getTexts())%>
                                </li>
                            </ul>
                        </td>
                    </tr>
                </c:when>
                <c:when test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
                    <c:forEach var="company" items="<%=((CompaniesSection) section).getCompanies()%>">
                        <tr>
                            <td colspan="2">
                                <h3>
                                    <c:choose>
                                        <c:when test="${empty company.websiteLink.url}">
                                            ${company.websiteLink.name}
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${company.websiteLink.url}">${company.websiteLink.name}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </h3>
                            </td>
                        </tr>
                        <c:forEach var="period" items="${company.periods}">
                            <jsp:useBean id="period" type="ru.javawebinar.basejava.model.Company.Period"/>
                            <tr>
                                <td width="20%" style="vertical-align: top">
                                    <%=HtmlPrinter.printHtmlDates(period)%>
                                </td>
                                <td>
                                    <b>${period.title}</b><br>${period.description}
                                </td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </c:when>

            </c:choose>
        </c:forEach>
</table>
    </p>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

