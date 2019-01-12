package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;

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
        listDirectory(directory);
    }

    private static void listDirectory(File directory) {
        String[] list = directory.list();
        if (list != null) {
            for (String name : list) {
                File newFile = new File(directory, name);
                if (newFile.isDirectory()) {
                    printPath(newFile);
                    listDirectory(newFile);
                } else {
                    printPath(newFile);
                }
            }
        }
    }

    private static void printPath(File newFile) {
//        System.out.println(newFile.getPath());
        try {
            System.out.println(newFile.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
