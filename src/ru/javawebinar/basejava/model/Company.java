package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;

public class Company {
    private Link websiteLink;
    private List<Period> periods;

    public Company (String url, String name, Period... periods){
        this(new Link(url, name), Arrays.asList(periods));
    }

    public Company(Link websiteLink, List<Period> periods) {
        this.websiteLink = websiteLink;
        this.periods = periods;
    }

    @Override
    public String toString() {
        return "\nCompany{" +
                "websiteLink=" + websiteLink +
                ", periods=" + periods +
                "}\n";
    }
}
