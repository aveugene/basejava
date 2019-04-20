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
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {

            function equalize_inputs(type){
                var period_inputs = [];
                $('input[id^="' + type + '"], textarea[id^="' + type + '"]').each(function () {
                    period_inputs.push(this.id);
                });
                var company_ind_to_set = 0;
                var company_ind_in_string = 0;
                var company_ind_now = 0;
                var period_ind_to_set = 0;
                var period_ind_in_string = 0;
                var period_ind_now = 0;
                $.each(period_inputs, function (ind, id) {
                    var id_splitted = id.split("_");
                    company_ind_in_string = id_splitted[1];
                    period_ind_in_string = id_splitted[2];
                    if (ind == 0) {
                        company_ind_now = company_ind_in_string;
                        period_ind_now = period_ind_in_string;
                    }
                    if (company_ind_now != company_ind_in_string) {
                        company_ind_to_set++;
                        company_ind_now = company_ind_in_string;
                        period_ind_now = 0;
                    }
                    if (period_ind_now != period_ind_in_string) {
                        period_ind_to_set++;
                        period_ind_now = period_ind_in_string;
                    }
                    $('#' + id).attr('name', type + '_' + company_ind_to_set + '_' + id_splitted[3]);
                });
            }

            $(document).on('click', '[id^="delete_company_"]', function (e) {
                var del_button_id = $(this).attr('id');
                var del_button_id_split = del_button_id.split("_");
                var element_to_remove = del_button_id.split("delete_")[1];
                var type = del_button_id_split[2];

                $('#' + element_to_remove).remove();

                equalize_inputs(type);
            });

            $(document).on('click', '[id^="insert_company_"]', function (e) {
                var ins_button_id = $(this).attr('id');
                var ins_button_id_split = ins_button_id.split("_");
                var type = ins_button_id_split[2];
                var company = ins_button_id_split[3];
                var period = parseInt(ins_button_id_split[5]) + 10 + Math.floor(Math.random() * 50);
                var element_to_insert = ins_button_id.split("insert_")[1];

                function generatePeriod(company, period) {
                    var period_html = "";
                    period_html += '<div id="company_' + type + '_' + company + '_Period_' + period + '" style="position:relative; padding: 5px 0 5px 20px;"> \
                        <dl> <dt>Период ' + period + '</dt> </dl> \
                        <div style="position:relative; padding: 5px 0 5px 20px;"> \
                            <script> $(function () { $("#' + type + '_' + company + '_' + period + '_STARTDATE").datepicker({ dateFormat: "mm/yy", changeMonth: "true", changeYear: "true", firstDay: 1 }); }); <\/script> \
                            <script> $(function () { $("#' + type + '_' + company + '_' + period + '_ENDDATE").datepicker({ dateFormat: "mm/yy", changeMonth: "true", changeYear: "true", firstDay: 1 }); }); <\/script> \
                            <dl> <dt>Начальная дата:</dt> <dd><input type="text" id="' + type + '_' + company + '_' + period + '_STARTDATE" name="' + type + '_' + company + '_STARTDATE" size="20" value=""></dd> </dl> \
                            <dl> <dt>Конечная дата:</dt> <dd><input type="text" id="' + type + '_' + company + '_' + period + '_ENDDATE" name="' + type + '_' + company + '_ENDDATE" size="20" value=""></dd> </dl> \
                            <dl> <dt>Название должности:</dt> <dd><input type="text" id="' + type + '_' + company + '_' + period + '_TITLE" name="' + type + '_' + company + '_TITLE" size="20" value=""></dd> </dl> \
                            <dl> \
                                <dt>Описание должности:</dt> \
                                <dd><textarea id="' + type + '_' + company + '_' + period + '_DESCRIPTION" name="' + type + '_' + company + '_DESCRIPTION" cols="75" rows="10"></textarea></dd> \
                            </dl> \
                        </div> \
                        <input style="padding: 0 30px 0 30px;" type="button" id="delete_company_' + type + '_' + company + '_Period_' + period + '" onclick="" value="удалить период"> \
                        <input style="padding: 0 30px 0 30px;" type="button" id="insert_company_' + type + '_' + company + '_Period_' + period + '" onclick="" value="вставить период"> \
                    </div> ';
                    return period_html;
                }

                if (element_to_insert.indexOf("Period") > -1) {
                    var period_html = generatePeriod(company, period)
                    $('#' + element_to_insert).after(period_html);
                } else {
                    var company = parseInt(ins_button_id_split[3]) + 10 + Math.floor(Math.random() * 50);
                    var period_html = generatePeriod(company, "0");
                    var company_html = "";
                    company_html += '<div id="company_' + type + '_' + company + '" style="position:relative; padding: 20px 0 20px 0;"> \
                        <dl> <dt> Название компании: </dt> <dd><input type="text" name="' + type + '" size="60" value=""> </dd> </dl> \
                        <dl> <dt> Сайт компании: </dt> <dd><input type="text" name="' + type + '_WEBPAGE" size="60" value=""></dd></dl> ';
                    company_html += period_html;
                    company_html += '' +
                        '<input type="button" id="delete_company_' + type + '_' + company + '" value="удалить компанию"> \
                        <input type="button" id="insert_company_' + type + '_' + company + '" value="вставить компанию"> \
                        </div>';
                    $('#' + element_to_insert).after(company_html);
                }
                equalize_inputs(type);
            });
        });
    </script>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form
            method="post"
            action="resume"
            enctype="application/x-www-form-urlencoded">
        <input
                type="hidden"
                name="uuid"
                value="${resume.uuid}">
        <dl>
            <dt> Имя: </dt>
            <dd><input
                    type="text"
                    name="fullName"
                    size=50
                    value="${resume.fullName}"></dd>
        </dl>
        <h3> Контакты :</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt> ${type.title} </dt>
                <dd><input
                        type="text"
                        name="${type.name()}"
                        size=30
                        value="${resume.getContact(type)}">
                </dd>
            </dl>
        </c:forEach>
        <hr>
        <h3> Секции :</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <jsp:useBean id="type" type="ru.javawebinar.basejava.model.SectionType"/>
            <c:if test="${resume.getSection(type) != null}">
                <c:set var="section" value="${resume.getSection(type)}"/>
                <jsp:useBean id="section" type="ru.javawebinar.basejava.model.AbstractSection"/>

                <h3> ${type.title} </h3>
                <c:choose>
                    <c:when test="${type == 'OBJECTIVE' || type == 'PERSONAL'}">
                        <input
                                type="text"
                                name="${type.name()}"
                                size=100
                                value="<%=section%>">
                    </c:when>
                    <c:when test="${type == 'ACHIEVEMENT' || type == 'QUALIFICATIONS'}">
                        <textarea
                                name="${type.name()}"
                                cols="100"
                                rows="10"> <%=String.join("\n", ((ListSection) section).getTexts())%> </textarea>
                    </c:when>
                    <c:when test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
                        <c:forEach var="company" items="<%=((CompaniesSection) section).getCompanies()%>"
                                   varStatus="companyCounter">
                            <div
                                    id="company_${type}_${companyCounter.index}"
                                    style="position:relative; padding: 20px 0 20px 0;">
                                <dl>
                                    <dt> Название компании:
                                    </dt>
                                    <dd><input
                                            type="text"
                                            name="${type}"
                                            size="60"
                                            value="${company.websiteLink.name}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt> Сайт компании:
                                    </dt>
                                    <dd><input
                                            type="text"
                                            name="${type}_WEBPAGE"
                                            size="60"
                                            value="${company.websiteLink.url}"></dd>
                                </dl>
                                <c:forEach var="period" items="${company.periods}" varStatus="periodCounter">
                                    <jsp:useBean id="period" type="ru.javawebinar.basejava.model.Company.Period"/>
                                    <div
                                            id="company_${type}_${companyCounter.index}_Period_${periodCounter.index}"
                                            style="position:relative; padding: 5px 0 5px 20px;">
                                        <dl>
                                            <dt> Период ${periodCounter.index} </dt>
                                        </dl>
                                        <div
                                                style="position:relative; padding: 5px 0 5px 20px;">
                                            <script> $(function () {
                                                $("#${type}_${companyCounter.index}_${periodCounter.index}_STARTDATE").datepicker({
                                                    dateFormat: "mm/yy",
                                                    changeMonth: "true",
                                                    changeYear: "true",
                                                    firstDay: 1
                                                });
                                            }); </script>
                                            <script> $(function () {
                                                $("#${type}_${companyCounter.index}_${periodCounter.index}_ENDDATE").datepicker({
                                                    dateFormat: "mm/yy",
                                                    changeMonth: "true",
                                                    changeYear: "true",
                                                    firstDay: 1
                                                });
                                            }); </script>
                                            <dl>
                                                <dt>Начальная дата:</dt>
                                                <dd><input type="text"
                                                           id="${type}_${companyCounter.index}_${periodCounter.index}_STARTDATE"
                                                           name="${type}_${companyCounter.index}_STARTDATE"
                                                           size="20"
                                                           value="<%=DateUtil.toString(period.getStartDate())%>"></dd>
                                            </dl>
                                            <dl>
                                                <dt>Конечная дата:</dt>
                                                <dd><input type="text"
                                                           id="${type}_${companyCounter.index}_${periodCounter.index}_ENDDATE"
                                                           name="${type}_${companyCounter.index}_ENDDATE"
                                                           size="20"
                                                           value="<%=DateUtil.toString(period.getEndDate())%>"></dd>
                                            </dl>
                                            <dl>
                                                <dt>Название должности:</dt>
                                                <dd><input type="text"
                                                           id="${type}_${companyCounter.index}_${periodCounter.index}_TITLE"
                                                           name="${type}_${companyCounter.index}_TITLE"
                                                           size="20" value="${period.title}"></dd>
                                            </dl>
                                            <dl>
                                                <dt>Описание должности:</dt>
                                                <dd><textarea
                                                        id="${type}_${companyCounter.index}_${periodCounter.index}_DESCRIPTION"
                                                        name="${type}_${companyCounter.index}_DESCRIPTION"
                                                        cols="75" rows="10">${period.description}</textarea>
                                                </dd>
                                            </dl>
                                        </div>
                                        <input style="padding: 0 30px 0 30px;" type="button"
                                               id="delete_company_${type}_${companyCounter.index}_Period_${periodCounter.index}"
                                               onclick=''
                                               value="удалить период">
                                        <input style="padding: 0 30px 0 30px;" type="button"
                                               id="insert_company_${type}_${companyCounter.index}_Period_${periodCounter.index}"
                                               onclick=''
                                               value="вставить период">
                                    </div>
                                </c:forEach>
                                <input type="button" id="delete_company_${type}_${companyCounter.index}" onclick='' value="удалить компанию">
                                <input type="button" id="insert_company_${type}_${companyCounter.index}" onclick='' value="вставить компанию">
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