package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.*;

import java.util.Map;

public class HtmlPrinter {
    public static String printContact(Map.Entry<ContactType, String> entry) {
        String returnString = "";
        ContactType type = entry.getKey();
        String value = entry.getValue();
        switch (type) {
            case PHONE:
                returnString = "<a href=\"tel:" + value + "\">" + value + "</a>";
                break;
            case EMAIL:
                returnString = "<a href=\"mailto:" + value + "\">" + value + "</a>";
                break;
            case SKYPE:
                returnString = "<a href=\"skype:" + value + "?call\">" + value + "</a>";
                break;
            default:
                returnString = "<a href=\"" + value + "\">" + value + "</a>";
                break;
        }
        return returnString;
    }

    public static String printSection(Map.Entry<SectionType, AbstractSection> entry) {
        String returnString = "";
        SectionType type = entry.getKey();
        AbstractSection section = entry.getValue();
        switch (type) {
            case PERSONAL:
            case OBJECTIVE:
                TextSection textSection = (TextSection) section;
                returnString = textSection.getContent();
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                ListSection listSection = (ListSection) section;
                returnString = "\n\t\t\t\t<ul>\n<li>" + String.join("</li>\n<li>", listSection.getTexts()) + "</li>\n\t\t\t\t</ul>\n";
                break;
        }

        return returnString;
    }


}
