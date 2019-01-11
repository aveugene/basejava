package ru.javawebinar.basejava.model;

import java.util.Objects;

public class TextSection implements Section {
    private String text;

    public TextSection(String text) {
        Objects.requireNonNull(text, "Text must not be null");
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "\nTextSection{" +
                "text='" + text + '\'' +
                "}\n";
    }
}
