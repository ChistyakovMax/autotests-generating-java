package ru.itmo.generator.testsuite;

import ru.itmo.fileworker.FileWorker;
import ru.itmo.generator.TemplateGenerator;
import ru.itmo.generator.testsuite.testcase.TestCaseGenerator;
import ru.itmo.generator.testsuite.testcase.TestCaseStepGenerator;
import utils.Constants;
import utils.Prettier;
import utils.StringTransformer;
import utils.testcase.TestCase;
import utils.testcase.TestSuite;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static ru.itmo.generator.testsuite.testcase.TestCaseGenerator.generateBaseTest;
import static ru.itmo.generator.testsuite.testcase.TestCaseGenerator.generateTestCase;

public class TestSuiteGenerator {
    private final String testSuiteTemplateFilePath = "testsuite/TestSuiteTemplate.ftl";
    private TestSuite testSuite;
    private List<String> testCases;
    private List<String> beforeSteps;
    private Set<String> pages;
    private Map<String, Object> elementsForTemplate;

    private TestSuite getTestSuiteFromFile(String filePath) throws Exception {
        TestSuite testSuite = new TestSuite();
        List<String> testCasesFromFile;
        List<TestCase> testCases = new ArrayList<>();

        //чтение файла
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        testCasesFromFile = new LinkedList<>(Arrays.asList(fileContent.split("\\r\\n\\r\\n")));

        //присвоение тест-сьюту имени
        testSuite.setName(Prettier
                .getNameWithUpperCaseFirstLetter(testCasesFromFile.get(0).trim().split(" ")[1]));
        testCasesFromFile.remove(0);

        //присвоение тест-сьюту списка тест-кейсов из теста
        for (String testCaseFromFile : testCasesFromFile) {
            testCases.add(TestCaseGenerator.getTestCaseFromFile(testCaseFromFile));
        }
        testSuite.setTestCases(testCases);

        return testSuite;
    }

    public void generateTestSuite(String filePath) throws Exception {
        testSuite = getTestSuiteFromFile(filePath);

        testCases = new ArrayList<>();
        beforeSteps = new ArrayList<>();
        pages = new HashSet<>();
        elementsForTemplate = new HashMap<>();

        String suiteName = testSuite.getName();
        for (TestCase testCase : testSuite.getTestCases()) {
            testCases.add(generateTestCase(testCase));
            pages.addAll(testCase.getPages());
        }
        for (String page : pages) {
            beforeSteps.add(TestCaseStepGenerator.generateInitOfPage(page));
        }

        String tcPages = StringTransformer
                .transformToString(pages, 1, 0);
        String bSteps = StringTransformer
                .transformToString(beforeSteps, 2, 1);
        String tCases = StringTransformer
                .transformToString(testCases, 2, 0);

        elementsForTemplate.put("name", suiteName);
        elementsForTemplate.put("testCasePages", tcPages);
        elementsForTemplate.put("beforeSteps", bSteps);
        elementsForTemplate.put("testCases", tCases.trim());

        String testSuiteClass = TemplateGenerator
                .generateFromTemplate(elementsForTemplate, testSuiteTemplateFilePath);
        FileWorker.createTestSuiteClass(testSuiteClass, suiteName);
    }

    public void generateAllTestSuites() throws Exception {
        generateBaseTest();
        final File folder = new File(Constants.TESTS_PATH);
        Set<String> testFiles = new HashSet<>();
        FileWorker.listFilesForFolder(folder, testFiles);
        for (String testFile : testFiles) {
            generateTestSuite(Constants.TESTS_PATH + testFile);
        }
    }
}
