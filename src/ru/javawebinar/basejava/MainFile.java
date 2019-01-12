package ru.javawebinar.basejava;

import java.io.File;

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
        int spacesIndent = 0;
        printDirectory(directory, spacesIndent);

    }

    private static void printDirectory(File directory, int spacesIndent) {
        printFileElement(directory, spacesIndent, "/");

        File[] list = directory.listFiles();
        if (list != null) {
            for (File file : list) {
                if (file.isDirectory()) {
                    printDirectory(file, spacesIndent + 1);
                } else {
                    printFileElement(file, spacesIndent + 1, "+");
                }
            }
        }
    }

    private static void printFileElement(File file, int spacesIndent, String splitter) {
        StringBuilder spaces = new StringBuilder("");
        for (int i=0; i<spacesIndent; i++){
            spaces.append("  ");
        }
        System.out.println(spaces + "    " + splitter + file.getName());
    }
}
