package ru.example.generator.testcase;

import utils.testcase.TestCase;
import utils.testcase.TestCaseAction;
import utils.testcase.TestCaseAssert;
import utils.testcase.TestCaseStep;
import utils.testcase.types.TestStepType;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestCaseGenerator {

    private String assertion = "Assert";
    private String action = "Action";
    private String exceptionMessage = "Строка должна начинаться с " + assertion + " либо " + action + "!";

    private TestCase getTestCaseFromTextFile() throws Exception {
        TestCase testCase = new TestCase();
        List<String> stepsFromFile;
        List<TestCaseStep> testCaseSteps= new ArrayList<>();

        //чтение файла
        String filePath = "src/main/resources/manual_tests/test1.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        stepsFromFile = Arrays.asList(fileContent.split("\n"));

        //прислоение тест-кейсу имени
        testCase.setName(stepsFromFile.get(0).split(" ")[1]);
        stepsFromFile.remove(0);

        //присвоение тест-кейсу списка шагов из теста
        for (String stepFromFile: stepsFromFile) {
            TestStepType testStepType = TestStepType.valueOf(stepFromFile.split(" ")[0].toUpperCase());
            switch (testStepType) {
                case ASSERT:
                    testCaseSteps.add(new TestCaseAssert(stepFromFile));
                    break;
                case CLICK:
                case DOUBLE_CLICK:
                case FILL:
                case CLEAR:
                case GO_TO:
                    testCaseSteps.add(new TestCaseAction(stepFromFile));
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + testStepType);
            }
        }

        return testCase;
    }
}
