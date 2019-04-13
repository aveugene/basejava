package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.util.DateUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage = Config.get().getStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume resume = storage.get(uuid);
        resume.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.addContact(type, value);
            } else {
                resume.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(type, new TextSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSection(type, new ListSection(value.split("\\r\\n")));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        String[] names = request.getParameterValues(type.name());
                        String[] webpages = request.getParameterValues(type.name() + "_WEBPAGE");
                        List<Company> companyList = new ArrayList<>();
                        for (int i = 0; i < names.length; i++) {
                            String name = names[i];
                            String webpage = webpages[i];
                            Link link = new Link(webpage, name);
                            String[] startDates = request.getParameterValues(type.name() + "_" + i + "_STARTDATE");
                            String[] endDates = request.getParameterValues(type.name() + "_" + i + "_ENDDATE");
                            String[] titles = request.getParameterValues(type.name() + "_" + i + "_TITLE");
                            String[] descriptions = request.getParameterValues(type.name() + "_" + i + "_DESCRIPTION");
                            List<Company.Period> periodList = new ArrayList<>();
                            for (int j = 0; j < startDates.length; j++) {
                                periodList.add(new Company.Period(DateUtil.toDate(startDates[j]), DateUtil.toDate(endDates[j]), titles[j], descriptions[j]));
                            }
                            companyList.add(new Company(link, periodList));
                        }
                        resume.addSection(type, new CompaniesSection(companyList));
                        break;
                }
            } else {
                resume.getSections().remove(type);
            }
        }
        storage.update(resume);
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                resume = storage.get(uuid);
                break;
            default:
                throw new IllegalStateException("Action " + action + " is illegal.");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

}
