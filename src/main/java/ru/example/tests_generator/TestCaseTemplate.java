package ru.example.tests_generator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import ru.example.freemarker.TestClassTemplateLoader;

public class TestCaseTemplate {

    public static void generateTestCase() throws Exception {
        //read file test1.txt
        String filePath = "src/main/resources/manual_tests/test1.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String testNumber = "";
        //test step into testCase method
        String testStep;
        StringBuilder testCaseSteps = new StringBuilder();
        Set<String> testCaseScreensSet = new HashSet<>();

        //return list of actions
        List<String> steps = Arrays.asList(fileContent.split("\n"));

        //for each action generate Step code
        for (int i = 0; i < steps.size(); i++) {
            String step = steps.get(i);

            String action = step.split(" ")[0];
            if(action.equals("TestCase")) {
                testNumber = step.split(" ")[1];
                continue;
            }

            if (action.equals("Assert")) continue;
            String screen = step.split(" ")[1].split("\\.")[0];
            String element = step.split(" ")[1].split("\\.")[1];

            String stepMethod = TestClassTemplateLoader.getStepByTemplate(screen, element, action, step);

            for (int j = i + 1; j < steps.size(); j++) {
                String checkedStep = steps.get(j);
                if (!stepIsAssert(checkedStep)) {
                    break;
                }
                stepMethod = addAssertForMethod(stepMethod, checkedStep);
                i++;
            }

            testStep = TestClassTemplateLoader.getTestStep(screen, action, element);
            testCaseSteps.append(testStep);
            addTestCaseScreenToSet(testCaseScreensSet, screen);

            Path fileScreenPath = Paths.get("src/main/java/ru/example/screens/" + screen + ".java");
            if (Files.exists(fileScreenPath)) {
                TestClassFileWriter.writeToFile(stepMethod, fileScreenPath);
            } else TestClassTemplateLoader.createPageObjectClassByTemplate(screen, stepMethod);

            if(i + 1 == steps.size()){
                TestClassTemplateLoader.createTestCaseClassByTemplate(testNumber, testCaseSteps.toString(), testCaseScreensSet);
            }
        }
    }

    public static boolean stepIsAssert(String step) {
        return step.split(" ")[0].equals("Assert");
    }

    public static String addAssertForMethod(String stepMethod, String checkedStep) throws Exception {
        String assertion = TestClassTemplateLoader.getAssertByTemplate(checkedStep);
        stepMethod = TestClassFileWriter.addFillingToMethod(stepMethod, assertion);

        return stepMethod;
    }

    public static void addTestCaseScreenToSet(Set<String> testCaseScreens, String screen) {
        StringBuilder testCaseScreen = new StringBuilder();
        testCaseScreen.append(screen)
                .append(" ")
                .append(screen.substring(0, 1).toLowerCase())
                .append(screen.substring(1))
                .append(";");

        testCaseScreens.add(testCaseScreen.toString());
    }
}