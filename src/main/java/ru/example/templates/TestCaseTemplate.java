package ru.example.templates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.Template;

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
                .append("() {\n")
                .append(element)
                .append(getMethodFromAction(action))
                .append("\n}\n")
                .toString();
    }

    public static List<String> getTestCase() {
        //read file test1.txt
        String filePath = "src/main/resources/manual_tests/test1.txt";
        String fileContent = "";
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //return list of actions
        List<String> steps = Arrays.asList(fileContent.split("\n"));

        StringBuilder newSteps = new StringBuilder();
        //for each action generate Step code
        for (String str: steps) {
            String action = str.split(" ")[0];
            String screenAndAction = str.split(" ")[1];

            String screen = screenAndAction.split("\\.")[0];
            String element = screenAndAction.split("\\.")[1];

            String step = (getStepTemplate(screen, element, action));
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
                        .append(step);

            }
            else {
                Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
                Map<String, Object> root = new HashMap<>();
                root.put("className", screen);
                root.put("classFilling", step);
            }

        }

        //create or check that it's exists AccountScreen.java

        //write step code to file AccountScreen.java

        return null;
    }

    public static void main(String[] args) {
        //System.out.println(getStepTemplate("ScreenA", "elementX", "Click"));
        getTestCase();
    }
}
