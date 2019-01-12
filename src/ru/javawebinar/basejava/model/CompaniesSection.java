package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CompaniesSection implements Section{
    private final List<Company> companies;

    public CompaniesSection(Company... companies) {
        this(Arrays.asList(companies));
    }

    public CompaniesSection(List<Company> companies) {
        Objects.requireNonNull(companies, "List of companies must not be null");
        this.companies = companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompaniesSection that = (CompaniesSection) o;
        return companies.equals(that.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companies);
    }

    @Override
    public String toString() {
        return companies.toString();
    }
}
