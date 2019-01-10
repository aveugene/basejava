package ru.javawebinar.basejava.model;

public class Link {
    private String url;
    private String name;

    public Link(String name) {
        this.name = name;
    }

    public Link(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Link{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                "}\n";
    }
}
