package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.basejava.util.DateUtil.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Company implements Serializable{
    private static final long serialVersionUID = 1L;

    private Link websiteLink;
    private List<Period> periods;

    public Company() {
    }

    public Company (String url, String name, Period... periods){
        this(new Link(url, name), Arrays.asList(periods));
    }

    public Company(Link websiteLink, List<Period> periods) {
        Objects.requireNonNull(websiteLink, "Website Link must not be null");
        Objects.requireNonNull(periods, "List of periods must not be null");
        this.websiteLink = websiteLink;
        this.periods = periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return websiteLink.equals(company.websiteLink) &&
                periods.equals(company.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(websiteLink, periods);
    }

    @Override
    public String toString() {
        return "\nCompany{" +
                "websiteLink=" + websiteLink +
                ", periods=" + periods +
                "}\n";
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String title;
        private String description;

        public Period() {
        }

        public Period(int startMonth, int startYear, int endMonth, int endYear, String title, String description) {
            this(of(startYear, Month.of(startMonth)), of(endYear,Month.of(endMonth)), title, description);
        }

        public Period(int startMonth, int startYear, String title, String description) {
            this(of(startYear, Month.of(startMonth)), NOW, title, description);
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
}
