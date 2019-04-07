package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.*;

import java.util.Map;

public class HtmlPrinter {
    public static String printContact (Map.Entry<ContactType, String> entry) {
        String returnString = "";
        ContactType type = entry.getKey();
        String value = entry.getValue();
        if (type.equals(ContactType.PHONE)){
            returnString = "<a href=\"tel:" + value + "\">" + value + "</a>";
        } else if (type.equals(ContactType.EMAIL)) {
            returnString = "<a href=\"mailto:" + value + "\">" + value + "</a>";
        } else if (type.equals(ContactType.SKYPE)) {
            returnString = "<a href=\"skype:" + value + "?call\">" + value + "</a>";
        } else {
            returnString = "<a href=\"" + value + "\">" + value + "</a>";
        }
        return returnString;
    }

    public static String printSection (Map.Entry<SectionType, AbstractSection> entry) {
        String returnString = "";
        SectionType type = entry.getKey();
        AbstractSection section = entry.getValue();
        if (type.equals(SectionType.PERSONAL) || type.equals(SectionType.OBJECTIVE)) {
            TextSection textSection = (TextSection) section;
            returnString = textSection.getContent();
        } else {
            ListSection listSection = (ListSection) section;
            returnString = "\n\t\t\t\t<ul>\n<li>" + String.join("</li>\n<li>", listSection.getTexts()) + "</li>\n\t\t\t\t</ul>\n";
        }

        return returnString;
    }


}
