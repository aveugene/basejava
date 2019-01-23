package ru.javawebinar.basejava;

import java.io.File;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainFile {
    public static void main(String[] args) {

        /*
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */

        File directory = new File(".");
        printDirectory(directory, "");





        Optional<Integer> sum = Stream.of(1, 2, 3, 4, 5)
                .reduce((acc, x) -> acc + x);
        System.out.println(sum.get());

        int product = IntStream.range(0, 10)
                .peek(x -> System.out.format("before filter: %d%n", x))
                .filter(x -> x++ % 4 == 0)
                .peek(x -> System.out.format("after filter: %d%n", x))
                .reduce((acc, x) -> acc * x)
                .getAsInt();
        System.out.println(product);



    }

    private static void printDirectory(File directory, String spacesIndent) {
        File[] list = directory.listFiles();
        if (list != null) {
            for (File file : list) {
                if (file.isDirectory()) {
                    System.out.println(spacesIndent + "/" + file.getName());
                    printDirectory(file, spacesIndent + "  ");
                } else {
                    System.out.println(spacesIndent + "+" + file.getName());;
                }
            }
        }
    }
}
