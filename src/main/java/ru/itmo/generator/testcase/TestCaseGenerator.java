package ru.itmo.generator.testcase;

import ru.itmo.filewriter.FileCreator;
import ru.itmo.generator.TemplateGenerator;
import utils.Prettier;
import utils.StringTransformer;
import utils.testcase.TestCase;
import utils.testcase.step.TestCaseStepAction;
import utils.testcase.step.TestCaseStepAssert;
import utils.testcase.step.TestCaseStep;
import utils.testcase.step.TestCaseStepComment;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static utils.Constants.ACTIONS;
import static utils.Constants.ASSERTION;


public class TestCaseGenerator {

    private final String exceptionMessage = "Строка должна начинаться с " + ASSERTION + " либо " + ACTIONS + "!";
    private final String testCaseTemplateFilePath = "testcase/TestCaseTemplate.ftl";
    private final String baseTestTemplateFilePath = "testcase/BaseTestTemplate.ftl";
    private Set<String> pages = new HashSet<>();
    private List<String> steps = new ArrayList<>();
    private Map<String, Object> elementsForTemplate = new HashMap<>();
    private String pageInTest = "";

    private TestCase getTestCaseFromTextFile() throws Exception {
        TestCase testCase = new TestCase();
        List<String> stepsFromFile;
        List<TestCaseStep> testCaseSteps= new ArrayList<>();

        //чтение файла
        String filePath = "src/main/resources/manual_tests/test1.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        stepsFromFile = new LinkedList<>(Arrays.asList(fileContent.split("\\n")));

        //прислоение тест-кейсу имени
        testCase.setName(Prettier.getNameWithUpperCaseFirstLetter(stepsFromFile.get(0).trim().split(" ")[1]));
        stepsFromFile.remove(0);

        //присвоение тест-кейсу списка шагов из теста
        for (String stepFromFile: stepsFromFile) {
            //если комментарии - добавить
            if(stepFromFile.startsWith("//")) {
                testCaseSteps.add(new TestCaseStepComment(stepFromFile));
                continue;
            }
            stepFromFile = stepFromFile.trim();
            String testStepType = stepFromFile.split(" ")[0].toUpperCase();
            if(ASSERTION.equals(testStepType)) {
                testCaseSteps.add(new TestCaseStepAssert(stepFromFile));
            } else if(ACTIONS.contains(testStepType)) {
                testCaseSteps.add(new TestCaseStepAction(stepFromFile));
            } else throw new IllegalArgumentException(exceptionMessage);
        }
        testCase.setTestSteps(testCaseSteps);

        return testCase;
    }

    public void generateTestCase() throws Exception {
        TestCase testCase = getTestCaseFromTextFile();
        generateBaseTest();
        pages.clear();
        steps.clear();
        elementsForTemplate.clear();
        String testName = testCase.getName();

        for (TestCaseStep step: testCase.getTestSteps()) {
            pageInTest = TestCaseStepGenerator.generatePageInTestCase(step);
            if(!pageInTest.isEmpty() && !pages.contains(pageInTest)) {
                steps.add(TestCaseStepGenerator.generateInitOfPage(step));
            }
            pages.add(pageInTest);
            steps.add(TestCaseStepGenerator.generateTestCaseStep(step));
        }

        String testCasePages = StringTransformer.transformToStringWithOneEnter(this.pages);
        String testCaseSteps = StringTransformer.transformToStringWithTabulation(this.steps);

        elementsForTemplate.put("name", testName);
        elementsForTemplate.put("testCasePages", testCasePages);
        elementsForTemplate.put("testCaseSteps", testCaseSteps);

        String testCaseClass = TemplateGenerator.generateFromTemplate(elementsForTemplate, testCaseTemplateFilePath);
        System.out.println(testCaseClass);
        FileCreator.createTestCaseClass(testCaseClass, testName);

    }

    private void generateBaseTest() throws Exception {
        String baseTestName = "Base";
        String baseTest = TemplateGenerator.generateFromTemplate(baseTestTemplateFilePath);
        System.out.println(baseTest);
        FileCreator.createTestCaseClass(baseTest, baseTestName);
    }
}
