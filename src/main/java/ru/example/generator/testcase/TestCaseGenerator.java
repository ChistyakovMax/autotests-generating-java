package ru.example.generator.testcase;

import ru.example.filewriter.FileCreator;
import ru.example.generator.TemplateGenerator;
import utils.StringTransformer;
import utils.testcase.TestCase;
import utils.testcase.step.TestCaseStepAction;
import utils.testcase.step.TestCaseStepAssert;
import utils.testcase.step.TestCaseStep;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class TestCaseGenerator {

    private final String assertion = "ASSERT";
    private final Set<String> actions = Set.of("CLICK", "DOUBLE_CLICK", "FILL", "CLEAR", "GO_TO");
    private final String exceptionMessage = "Строка должна начинаться с " + assertion + " либо " + actions + "!";
    private final String testCaseTemplateFilePath = "testcase/TestCaseTemplate.ftl";
    private final String baseTestTemplateFilePath = "testcase/BaseTestTemplate.ftl";
    private Set<String> pages = new HashSet<>();
    private List<String> steps = new ArrayList<>();
    private Map<String, Object> elementsForTemplate = new HashMap<>();

    private TestCase getTestCaseFromTextFile() throws Exception {
        TestCase testCase = new TestCase();
        List<String> stepsFromFile;
        List<TestCaseStep> testCaseSteps= new ArrayList<>();

        //чтение файла
        String filePath = "src/main/resources/manual_tests/test1.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        stepsFromFile = new LinkedList<>(Arrays.asList(fileContent.split("\\n")));

        //прислоение тест-кейсу имени
        testCase.setName(stepsFromFile.get(0).trim().split(" ")[1]);
        stepsFromFile.remove(0);

        //присвоение тест-кейсу списка шагов из теста
        for (String stepFromFile: stepsFromFile) {
            stepFromFile = stepFromFile.trim();
            String testStepType = stepFromFile.split(" ")[0].toUpperCase();
            if(assertion.equals(testStepType)) {
                testCaseSteps.add(new TestCaseStepAssert(stepFromFile));
            } else if(actions.contains(testStepType)) {
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
            pages.add(TestCaseStepGenerator.generatePageInTestCase(step));
            steps.add(TestCaseStepGenerator.generateTestCaseStep(step));
        }

        String testCasePages = StringTransformer.transformToString(this.pages);
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
