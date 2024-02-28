package ru.example.filewriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {
    private static File file;

    public static void createPageObjectClass(String pageObject, String name) throws IOException {
        file = new File("./src/main/java/ru/example/pages/" + name + "Page.java");
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        }
        file.createNewFile();
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(pageObject);
        bw.close();
    }
}
