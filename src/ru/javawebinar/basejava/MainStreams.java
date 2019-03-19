package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainStreams {
    public static void main(String[] args) {
        int[] testArr = {1, 2, 3, 2, 3, 3};
        System.out.println(minValue(testArr));

        ArrayList<Integer> testList = new ArrayList<>(Arrays.asList(1, 2, 3, 1));
        System.out.println(oddOrEven(testList));
    }

    private static int minValue(int[] values) {
        AtomicInteger result = new AtomicInteger();
        Arrays.stream(values).sorted().distinct().forEach(x -> result.set(result.get() * 10 + x));
        return result.get();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream()
                .flatMap(x -> {
                    switch (integers.stream().mapToInt(Integer::intValue).sum() % 2) {
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
