package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection implements Section {
    private List<String> textList;

    public ListSection(String... texts){
        this(Arrays.asList(texts));
    }

    public ListSection(List<String> textList) {
        Objects.requireNonNull(textList, "List of texts must not be null");
        this.textList = textList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return textList.equals(that.textList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textList);
    }

    @Override
    public String toString() {
        return "\nListSection{" +
                "textList=" + textList +
                "}\n";
    }
}
