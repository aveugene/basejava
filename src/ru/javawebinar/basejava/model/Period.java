package ru.javawebinar.basejava.model;

import java.time.LocalDate;

public class Period {
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String text;

    public Period(int startMonth, int startYear, int endMonth, int endYear, String title, String text) {
        this(LocalDate.of(startYear, startMonth, 1), LocalDate.of(endYear, endMonth, 1), title, text);
    }

    public Period(int startMonth, int startYear, String title, String text) {
        this(LocalDate.of(startYear, startMonth, 1), LocalDate.now(), title, text);
    }

    public Period(LocalDate startDate, LocalDate endDate, String title, String text) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.text = text;
    }

    @Override
    public String toString() {
        return "\nPeriod{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                "}\n";
    }
}
