package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.AbstractSection;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.SectionType;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.util.HtmlPrinter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class ResumeServlet extends HttpServlet {
    private Storage storage = Config.get().getStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type","text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String uuid = request.getParameter("uuid");
        Writer writer = response.getWriter();
        if (uuid == null) {
            writer.write(
                    "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                            "    <title>Курс Basejava</title>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<header>Онлайн Java курсы <a href=\"http://topjava.ru\">Topjava</a></header>\n" +
                            "\t<table border=\"1\">\n"
            );
            for (Resume resume : storage.getAllSorted()) {
                writer.write(
                        "\t\t<tr>\n" +
                                "\t\t\t<td><a href=\"resume?uuid=" + resume.getUuid() + "\">" + resume.getFullName() + "</a></td>\n"
                );
                for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                    writer.write("\t\t\t<td>" + HtmlPrinter.printContact(entry) + "</td>\n");
                }
                writer.write(
                                "\t\t</tr>\n"
                );
            }
            writer.write(
                    "\t</table>\n" +
                            "<footer>Онлайн Java курсы <a href=\"http://topjava.ru\">Topjava</a></footer>\n" +
                            "</body>\n" +
                            "</html>"
            );
        } else {
            Resume resume = storage.get(uuid);

            writer.write(
                    "\t<table>\n" +
                            "\t\t<tr><td><h1>" + resume.getFullName() + "</h1></td></tr>\n" +
                            "\t\t<tr><td><h2>Контакты:</h2></td></tr>\n"
            );

            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                writer.write(
                        "\t\t<tr><td></td><td>" + entry.getKey().getTitle() + "</td><td>" + HtmlPrinter.printContact(entry) + "</td></tr>\n"
                );
            }

            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                writer.write(
                        "\t\t<tr><td><h2>" + entry.getKey().getTitle() + "</h2></td></tr><tr><td colspan=\"3\">" + HtmlPrinter.printSection(entry) + "</td></tr>\n"
                );
            }
            writer.write(
                            "\t</table>\n"
            );
        }
    }
}
