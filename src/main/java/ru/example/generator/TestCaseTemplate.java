package ru.example.generator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import ru.example.freemaker.TestClassTemplateLoader;

public class TestCaseTemplate {

    public static List<String> generateTestCase() throws Exception {
        //read file test1.txt
        String filePath = "src/main/resources/manual_tests/test1.txt";
        String fileContent = "";
        fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

        //return list of actions
        List<String> steps = Arrays.asList(fileContent.split("\n"));

        //for each action generate Step code
        for (String step: steps) {
            String action = step.split(" ")[0];
            String screen = step.split(" ")[1].split("\\.")[0];
            String element = step.split(" ")[1].split("\\.")[1];

            String stepMethod = TestClassTemplateLoader.getStepByTemplate(screen, element, action);
            Path fileScreenPath = Paths.get("src/main/java/ru/example/screens/" + screen + ".java");

            if (Files.exists(fileScreenPath)) {
                TestClassFileWriter.writeToFile(stepMethod, fileScreenPath);
            } else TestClassTemplateLoader.createTestClassByTemplate(screen, stepMethod);

        }

        return null;
    }
}
