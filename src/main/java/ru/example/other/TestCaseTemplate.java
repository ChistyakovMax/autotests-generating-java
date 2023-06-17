package ru.example.other;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.example.freemaker.ClassTemplateLoaderDemo;

public class TestCaseTemplate {
    private static Map<String,String> actionsAndMethods = createActionsAndMethodsHashMap();

    //create Hash-Map (actions and methods like "Click", ".click()")
    private static Map createActionsAndMethodsHashMap() {
        Map<String, String> map = new HashMap<>();
        map.put("click", ".click()");

        return map;
    }

    private static String getMethodFromAction(String action) {
        return actionsAndMethods.get(action.toLowerCase());
    }
    private static String getStepTemplate(String screen, String element, String action) {
        return new StringBuilder().append("public ")
                .append(screen)
                .append(" ")
                .append(action)
                .append(element)
                .append("() {\n\t\t")
                .append(element)
                .append(getMethodFromAction(action))
                .append(";\n\t}\n")
                .toString();
    }

    public static List<String> getTestCase() throws Exception {
        //read file test1.txt
        String filePath = "src/main/resources/manual_tests/test1.txt";
        String fileContent = "";
        fileContent = new String(Files.readAllBytes(Paths.get(filePath)));


        //return list of actions
        List<String> steps = Arrays.asList(fileContent.split("\n"));

        StringBuilder newSteps = new StringBuilder();
        //for each action generate Step code
        for (String step: steps) {
            String action = step.split(" ")[0];
            String screen = step.split(" ")[1].split("\\.")[0];
            String element = step.split(" ")[1].split("\\.")[1];

            String stepMethod = getStepTemplate(screen, element, action);
            Path fileScreenPath = Paths.get("src/main/java/ru/example/screens" + screen + ".java");

            if (Files.exists(fileScreenPath)){
                StringBuilder screenFileContent = new StringBuilder();
                try {
                    screenFileContent.append(Files.readAllBytes(fileScreenPath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                screenFileContent
                        .append("\n")
                        .append(stepMethod);

            }
            else {
                ClassTemplateLoaderDemo.createTestClassByTemplate(screen, stepMethod);
            }

        }

        //create or check that it's exists AccountScreen.java

        //write step code to file AccountScreen.java

        return null;
    }

    public static void main(String[] args) throws Exception {
        //System.out.println(getStepTemplate("ScreenA", "elementX", "Click"));
        getTestCase();
    }
}
