package ru.example.tests_generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TestClassFileWriter {

    public static void writeToFile(String fileFilling, Path fileScreenPath) throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append(Files.readString(fileScreenPath, StandardCharsets.UTF_8));
        int lastCLosingBracketIndex = sb.lastIndexOf("}");
        sb.deleteCharAt(lastCLosingBracketIndex)
                .append("\n")
                .append(fileFilling)
                .append("\n}");
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileScreenPath.toFile()));
        writer.write(sb.toString());
        writer.close();
    }

    public static String addFillingToMethod(String method, String filling) {
        List<String> methodList = List.of(method.split("return"));
        StringBuilder sb = new StringBuilder();
        sb.append(methodList.get(0))
                .append(filling)
                .append("\n\t\treturn")
                .append(methodList.get(1));

        return sb.toString();
    }
}