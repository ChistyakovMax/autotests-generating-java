package ru.itmo.generator.testsuite.testcase;

import ru.itmo.fileworker.FileWorker;
import ru.itmo.generator.TemplateGenerator;
import ru.itmo.generator.page.PageGenerator;
import utils.Prettier;
import utils.StringTransformer;
import utils.testcase.TestCase;
import utils.testcase.step.TestCaseStep;
import utils.testcase.step.TestCaseStepAction;
import utils.testcase.step.TestCaseStepAssert;
import utils.testcase.step.TestCaseStepComment;

import java.util.*;

import static utils.Constants.*;


public class TestCaseGenerator {

    private static final String testCaseTemplateFilePath = "testsuite/testcase/TestCaseTemplate.ftl";
    private static final String baseTestTemplateFilePath = "testsuite/testcase/BaseTestTemplate.ftl";
    private static List<String> steps;
    private static Map<String, Object> elementsForTemplate;

    public static TestCase getTestCaseFromFile(String testCaseFromFile) throws Exception {
        TestCase testCase = new TestCase();
        List<String> stepsFromFile;
        List<TestCaseStep> testCaseSteps = new ArrayList<>();
        Set<String> pagesInTest = new HashSet<>();

        //чтение
        stepsFromFile = new LinkedList<>(Arrays.asList(testCaseFromFile.split("\\n")));

        //прислоение тест-кейсу имени
        testCase.setName(Prettier
                .getNameWithUpperCaseFirstLetter(stepsFromFile.get(0).trim().split(" ")[1]));
        stepsFromFile.remove(0);

        //присвоение тест-кейсу списка шагов из теста
        for (String stepFromFile : stepsFromFile) {
            //если комментарии - добавить
            if (stepFromFile.startsWith("//")) {
                testCaseSteps.add(new TestCaseStepComment(stepFromFile));
                continue;
            }
            stepFromFile = stepFromFile.trim();
            String testStepType = stepFromFile.split(" ")[0].toUpperCase();
            if (ASSERTION.equals(testStepType)) {
                testCaseSteps.add(new TestCaseStepAssert(stepFromFile));
            } else if (ACTIONS.contains(testStepType)) {
                testCaseSteps.add(new TestCaseStepAction(stepFromFile));
            } else throw new IllegalArgumentException(wrongTestStepTypeExceptionMessage);
        }
        testCase.setTestSteps(testCaseSteps);

        //добавить используемые страницы страницы в тест
        for (TestCaseStep testCaseStep : testCase.getTestSteps()) {
            pagesInTest.add(TestCaseStepGenerator.generatePageInTestCase(testCaseStep));
        }
        pagesInTest.removeAll(Arrays.asList("", null));
        testCase.setPages(pagesInTest);

        return testCase;
    }

    public static String generateTestCase(TestCase testCase) throws Exception {
        steps = new ArrayList<>();
        elementsForTemplate = new HashMap<>();
        String testName = testCase.getName();

        for (TestCaseStep step : testCase.getTestSteps()) {
            steps.add(TestCaseStepGenerator.generateTestCaseStep(step));
        }

        String testCaseSteps = StringTransformer
                .transformToString(steps, 1, 1);

        elementsForTemplate.put("name", testName);
        elementsForTemplate.put("testCaseSteps", testCaseSteps);

        return TemplateGenerator
                .generateFromTemplate(elementsForTemplate, testCaseTemplateFilePath);
    }

    public static void generateBaseTest() throws Exception {
        String baseUrl = new PageGenerator().getPagesFromYAML().getBaseUrl();
        elementsForTemplate = new HashMap<>();
        elementsForTemplate.put("baseUrl", baseUrl);
        String baseTest = TemplateGenerator
                .generateFromTemplate(elementsForTemplate, baseTestTemplateFilePath);
        FileWorker.createTestSuiteClass(baseTest, BASE_TEST_NAME);
    }
}
