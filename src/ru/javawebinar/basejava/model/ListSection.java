package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final List<String> texts;

    public ListSection(String... texts){
        this(Arrays.asList(texts));
    }

    public ListSection(List<String> texts) {
        Objects.requireNonNull(texts, "List of texts must not be null");
        this.texts = texts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return texts.equals(that.texts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(texts);
    }

    @Override
    public String toString() {
        return texts.toString();
    }
}
