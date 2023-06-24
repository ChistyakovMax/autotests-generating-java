package ru.example.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestClassFileWriter {

    public static void writeToFile(String fileFilling, Path fileScreenPath) throws IOException {
        StringBuilder screenFileContent = new StringBuilder();

        screenFileContent.append(Files.readString(fileScreenPath, StandardCharsets.UTF_8));
        int lastCLosingBracketIndex = screenFileContent.lastIndexOf("}");
        screenFileContent
                .deleteCharAt(lastCLosingBracketIndex)
                .append("\n")
                .append(fileFilling)
                .append("\n}");
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileScreenPath.toFile()));
        writer.write(screenFileContent.toString());
        writer.close();
    }

}
