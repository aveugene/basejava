package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.Company;

public class HtmlPrinter {
    public static String printHtmlDates(Company.Period period){
        return DateUtil.toString(period.getStartDate()) + " - " + DateUtil.toString(period.getEndDate());
    }
}
