<%@ page import="ru.javawebinar.basejava.model.CompaniesSection" %>
<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page import="ru.javawebinar.basejava.util.DateUtil" %>
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
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <hr>
        <h3>Секции:</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <jsp:useBean id="type" type="ru.javawebinar.basejava.model.SectionType"/>
            <c:if test="${resume.getSection(type) != null}">
                <c:set var="section" value="${resume.getSection(type)}"/>
                <jsp:useBean id="section" type="ru.javawebinar.basejava.model.AbstractSection"/>

                <h3>${type.title}</h3>
                <c:choose>
                    <c:when test="${type == 'OBJECTIVE' || type == 'PERSONAL'}">
                        <input type="text" name="${type.name()}" size=100 value="<%=section%>">
                    </c:when>
                    <c:when test="${type == 'ACHIEVEMENT' || type == 'QUALIFICATIONS'}">
                        <textarea name="${type.name()}" cols="100"
                                  rows="10"><%=String.join("\n", ((ListSection) section).getTexts())%></textarea>
                    </c:when>
                    <c:when test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
                        <c:forEach var="company" items="<%=((CompaniesSection) section).getCompanies()%>"
                                   varStatus="companyCounter">
                            <div style="position:relative; padding: 0 0 20px 0;">
                                <dl>
                                    <dt>Название компании:</dt>
                                    <dd><input type="text" name="${type}" size="60" value="${company.websiteLink.name}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Сайт компании:</dt>
                                    <dd><input type="text" name="${type}_WEBPAGE" size="60"
                                               value="${company.websiteLink.url}"></dd>
                                </dl>
                                <c:forEach var="period" items="${company.periods}" varStatus="periodCounter">
                                    <jsp:useBean id="period" type="ru.javawebinar.basejava.model.Company.Period"/>
                                    <div style="position:relative; padding: 5px 0 5px 20px;">
                                        <dl>
                                            <dt>Период ${periodCounter.index}</dt>
                                        </dl>
                                        <div style="position:relative; padding: 5px 0 5px 20px;">
                                            <dl>
                                                <dt>Начальная дата:</dt>
                                                <dd><input type="text"
                                                           name="${type}_${companyCounter.index}_STARTDATE"
                                                           size="20"
                                                           value="<%=DateUtil.toString(period.getStartDate())%>"></dd>
                                            </dl>
                                            <dl>
                                                <dt>Конечная дата:</dt>
                                                <dd><input type="text"
                                                           name="${type}_${companyCounter.index}_ENDDATE"
                                                           size="20"
                                                           value="<%=DateUtil.toString(period.getEndDate())%>"></dd>
                                            </dl>
                                            <dl>
                                                <dt>Название должности:</dt>
                                                <dd><input type="text"
                                                           name="${type}_${companyCounter.index}_TITLE"
                                                           size="20" value="${period.title}"></dd>
                                            </dl>
                                            <dl>
                                                <dt>Описание должности:</dt>
                                                <dd><input type="text"
                                                           name="${type}_${companyCounter.index}_DESCRIPTION"
                                                           size="20" value="${period.description}"></dd>
                                            </dl>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </c:if>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>