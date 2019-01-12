package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;
import static ru.javawebinar.basejava.util.DateUtil.*;

public class Period {
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String description;

    public Period(int startMonth, int startYear, int endMonth, int endYear, String title, String description) {
        this(of(startYear, Month.of(startMonth)), of(endYear,Month.of(endMonth)), title, description);
    }

    public Period(int startMonth, int startYear, String title, String description) {
        this(of(startYear, Month.of(startMonth)), now(), title, description);
    }

    public Period(LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, "StartDate must not be null");
        Objects.requireNonNull(endDate, "EndDate must not be null");
        Objects.requireNonNull(title, "Title must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return startDate.equals(period.startDate) &&
                endDate.equals(period.endDate) &&
                title.equals(period.title) &&
                Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, title, description);
    }

    @Override
    public String toString() {
        return "\nPeriod{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                "}\n";
    }
}
