package ru.javawebinar.basejava.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static final LocalDate NOW = LocalDate.of(9999, 1, 1);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/yyyy");

    public static String toString(LocalDate date) {
        if (date == null) return "";
        return date.equals(NOW) ? "Сейчас" : date.format(dateTimeFormatter);
    }

    public static LocalDate toDate(String date) {
        if (date.equals("Сейчас")) return NOW;
        YearMonth yearMonth = YearMonth.parse(date, dateTimeFormatter);
        return LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), 1);
    }
}
