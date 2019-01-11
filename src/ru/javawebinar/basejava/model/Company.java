package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Company {
    private Link websiteLink;
    private List<Period> periods;

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
}
