package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;

public class CompaniesSection implements Section{
    private List<Company> companiesList;

    public CompaniesSection(Company... companies) {
        this(Arrays.asList(companies));
    }

    public CompaniesSection(List<Company> companiesList) {
        this.companiesList = companiesList;
    }

    @Override
    public String toString() {
        return "\nCompaniesSection{" +
                "companiesList=" + companiesList +
                "}\n";
    }
}
