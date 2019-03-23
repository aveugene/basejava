package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainStreams {
    public static void main(String[] args) {
        int[] testArr = {1, 2, 3, 2, 3, 3};
        System.out.println(minValue(testArr));

        ArrayList<Integer> testList = new ArrayList<>(Arrays.asList(1, 6, 9, 4,4));
        System.out.println(oddOrEven(testList));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce((acc, x) -> acc * 10 + x).getAsInt();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
/*
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        return integers.stream()
                .flatMap(x -> {
                    switch (sum % 2) {
                        case 0:
                            return getStream(x, 1);
                        case 1:
                            return getStream(x, 0);
                        default:
                            return Stream.empty();
                    }
                })
                .collect(Collectors.toList());
*/

        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        Map<Boolean, List<Integer>> collect = integers.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0));
        if ( sum % 2 == 0 ) {
            return collect.get(false);
        } else {
            return collect.get(true);
        }

    }

    /*private static Stream<? extends Integer> getStream(Integer x, int i) {
        if (x % 2 == i) {
            return Stream.of(x);
        }
        return Stream.empty();
    }*/


}
