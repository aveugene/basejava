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
        Resume resume;
        final boolean isCreate = (uuid == null || uuid.length() == 0);
        if (isCreate) {
            resume = new Resume(fullName);
        } else {
            resume = storage.get(uuid);
            resume.setFullName(fullName);
        }
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.setContact(type, value);
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
                        resume.setSection(type, new TextSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.setSection(type, new ListSection(value.split("\\r\\n")));
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
                            String prefix = type.name() + "_" + i;
                            String[] startDates = request.getParameterValues(prefix + "_STARTDATE");
                            String[] endDates = request.getParameterValues(prefix + "_ENDDATE");
                            String[] titles = request.getParameterValues(prefix + "_TITLE");
                            String[] descriptions = request.getParameterValues(prefix + "_DESCRIPTION");
                            List<Company.Period> periodList = new ArrayList<>();
                            for (int j = 0; j < titles.length; j++) {
                                if (startDates[j].length() != 0) {
                                    periodList.add(new Company.Period(DateUtil.toDate(startDates[j]), DateUtil.toDate(endDates[j]), titles[j], descriptions[j]));
                                }
                            }
                            companyList.add(new Company(link, periodList));
                        }
                        resume.setSection(type, new CompaniesSection(companyList));
                        break;
                }
            } else {
                resume.getSections().remove(type);
            }
        }
        if (isCreate) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
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
                resume = storage.get(uuid);
                break;
            case "edit":
                resume = storage.get(uuid);
                for (SectionType type : SectionType.values()) {
                    AbstractSection section = resume.getSection(type);
                    switch (type) {
                        case PERSONAL:
                        case OBJECTIVE:
                            if (section == null) {
                                section = TextSection.EMPTY;
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = ListSection.EMPTY;
                            }
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            if (section == null) {
                                section = CompaniesSection.EMPTY;
                            }
                            break;
                    }
                    resume.setSection(type, section);
                }
                break;
            case "add":
                resume = Resume.EMPTY_RESUME;
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
