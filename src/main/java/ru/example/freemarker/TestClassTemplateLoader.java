package ru.example.freemarker;

import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TestClassTemplateLoader {
    private static final Map<String, String> actionsAndMethods = createActionsAndMethodsHashMap();

    private static Map createActionsAndMethodsHashMap() {
        Map<String, String> map = new HashMap<>();
        map.put("click", "click");
        map.put("tap", "click");
        map.put("fill", "sendKeys");
        map.put("fill_with", "sendKeys");
        map.put("is_visible", "isVisible");
        map.put("isVisible", "isVisible");
        map.put("is_displayed", "isVisible");

        return map;
    }

    private static String getMethodFromAction(String action) {
        return actionsAndMethods.get(action.toLowerCase());
    }

    static Configuration cfg;
    static Template template;

    public static void createPageObjectClassByTemplate(String className, String classFilling) throws Exception {
        cfg = ConfigurationUtil.getConfiguration();

        Map<String, Object> map = new HashMap<>();
        map.put("className", className);
        map.put("classFilling", classFilling);

        template = cfg.getTemplate("ScreenClassTemplate.ftl");
        //File output
        Writer file = new FileWriter("src/main/java/ru/example/screens/" + className + ".java");
        template.process(map, file);
        file.flush();
        file.close();
    }

    public static void createTestCaseClassByTemplate(String testNumber, String testCaseSteps, Set<String> testCaseScreensSet)
            throws Exception {
        String testCaseScreens = String.join("\n\t", testCaseScreensSet);

        cfg = ConfigurationUtil.getConfiguration();
        Map<String, Object> map = new HashMap<>();
        map.put("testNumber", testNumber);
        map.put("testCaseSteps", testCaseSteps);
        map.put("testCaseScreens", testCaseScreens);

        template = cfg.getTemplate("AutotestTemplate.ftl");
        Writer file = new FileWriter("src/test/java/ru/example/freemaker/tests/TestCase" + testNumber + ".java");
        template.process(map, file);
        file.flush();
        file.close();
    }

    public static String getStepByTemplate(String screen, String element, String action, String step) throws Exception {
        cfg = ConfigurationUtil.getConfiguration();
        Map<String, Object> map = new HashMap<>();
        map.put("screen", screen);
        map.put("element", element);
        map.put("action", getMethodFromAction(action));

        switch (getMethodFromAction(action)) {
            case "sendKeys":
                map.put("parameter", ("\"" + step.split(" ")[3]) + "\"");
                break;

            case "click":
            default:
                map.put("parameter", "");
                map.put("parameterType", "");
        }

        template = cfg.getTemplate("TestMethodTemplate.ftl");

        StringWriter stringWriter = new StringWriter();
        template.process(map, stringWriter);

        return stringWriter.toString();
    }

    public static String getAssertByTemplate(String step) throws Exception {
        String check = step.split(" ")[2];
        String screen = step.split(" ")[1].split("\\.")[0];
        String element = step.split(" ")[1].split("\\.")[1];

        cfg = ConfigurationUtil.getConfiguration();
        Map<String, Object> map = new HashMap<>();
        map.put("screen", screen);
        map.put("element", element);

        switch (check) {
            case "is_visible":
                template = cfg.getTemplate("AssertThatIsDisplayedTemplate.ftl");
                break;
            case "equals":
                map.put("expected", ("\"" + step.split(" ")[3]) + "\"");
                template = cfg.getTemplate("AssertEqualsTemplate.ftl");
                break;

            default:
        }

        StringWriter stringWriter = new StringWriter();
        template.process(map, stringWriter);

        return stringWriter.toString();
    }

    public static String getTestStep(String screen, String action, String element) {
        StringBuilder testStep = new StringBuilder();
        testStep.append(screen.substring(0, 1).toLowerCase())
                .append(screen.substring(1))
                .append(".")
                .append(getMethodFromAction(action))
                .append("_")
                .append(element)
                .append("();\n\t\t");

        return testStep.toString();
    }
}