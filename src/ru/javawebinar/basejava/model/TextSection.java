package ru.javawebinar.basejava.model;

public class TextSection implements Section {
    private String text;

    public TextSection(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "\nTextSection{" +
                "text='" + text + '\'' +
                "}\n";
    }
}
