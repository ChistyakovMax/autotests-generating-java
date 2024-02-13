package ru.example.filewriter;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCreator {
    private static Writer filen;
    private static File file;
    private static Path path;
    public static void createPageObjectClass(String pageObject, String name) throws IOException {
//        file = new FileWriter("src/main/java/ru/example/pages/" + name + ".java");
//        file.write(pageObject);
//        file.flush();
//        file.close();
        file = new File("./src/main/java/ru/example/pages/" + name + ".java");
        if(file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        }
        file.createNewFile();
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(pageObject);
        bw.close();
    }
}
