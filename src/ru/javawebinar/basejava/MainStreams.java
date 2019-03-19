package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainStreams {
    public static void main(String[] args) {
        int[] testArr = {1, 2, 3, 2, 3, 3};
        System.out.println(minValue(testArr));

        ArrayList<Integer> testList = new ArrayList<>(Arrays.asList(1, 6, 9));
        System.out.println(oddOrEven(testList));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce((acc, x) -> acc * 10 + x).getAsInt();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        return integers.stream()
                .flatMap(x -> {
                    switch (sum % 2) {
                        case 0:
                            if (x % 2 == 1) {
                                return Stream.of(x);
                            }
                            return Stream.empty();
                        case 1:
                            if (x % 2 == 0) {
                                return Stream.of(x);
                            }
                            return Stream.empty();
                        default:
                            return Stream.empty();
                    }
                })
                .collect(Collectors.toList());
    }
}
