package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;

public class ListSection implements Section {
    private List<String> textList;

    public ListSection(String... texts){
        this(Arrays.asList(texts));
    }
    public ListSection(List<String> textList) {
        this.textList = textList;
    }

    @Override
    public String toString() {
        return "\nListSection{" +
                "textList=" + textList +
                "}\n";
    }
}
