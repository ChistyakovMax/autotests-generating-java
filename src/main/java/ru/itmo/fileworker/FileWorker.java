package ru.itmo.fileworker;

import utils.Constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class FileWorker {
    private static File file;

    public static Set<String> listFilesForFolder(final File folder, Set<String> testFiles) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry, testFiles);
            } else {
                testFiles.add(fileEntry.getName());
            }
        }

        return testFiles;
    }

    public static void createPageObjectClass(String pageObject, String name) {
        file = new File(Constants.PAGES_PATH + name + "Page.java");
        createFile(pageObject, name, file);

    }

    public static void createTestSuiteClass(String testCase, String name) {
        file = new File(Constants.TEST_SUITES_PATH + name + "Test.java");
        createFile(testCase, name, file);
    }

    public static void createFile(String content, String name, File file) {
        if (file.delete()) {
            System.out.println("File deleted: " + file.getName());
        }
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            System.out.println("File created: " + file.getName());
        } catch (IOException e) {
            System.err.println("An error was occurred while creating file: " + file.getName());
            throw new RuntimeException(e);
        }
    }
}
