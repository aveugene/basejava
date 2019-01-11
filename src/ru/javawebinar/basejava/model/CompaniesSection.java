package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CompaniesSection implements Section{
    private List<Company> companiesList;

    public CompaniesSection(Company... companies) {
        this(Arrays.asList(companies));
    }

    public CompaniesSection(List<Company> companiesList) {
        Objects.requireNonNull(companiesList, "List of companies must not be null");
        this.companiesList = companiesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompaniesSection that = (CompaniesSection) o;
        return companiesList.equals(that.companiesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companiesList);
    }

    @Override
    public String toString() {
        return "\nCompaniesSection{" +
                "companiesList=" + companiesList +
                "}\n";
    }
}
