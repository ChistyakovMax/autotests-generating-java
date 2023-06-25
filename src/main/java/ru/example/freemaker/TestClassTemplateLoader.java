package ru.example.freemaker;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

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

    public static void createTestClassByTemplate(String className, String classFilling) throws Exception {
        cfg = ConfigurationUtil.getConfiguration();

        Map<String, Object> map = new HashMap<>();
        map.put("className", className);
        map.put("classFilling", classFilling);

        Template template = cfg.getTemplate("FreeMarkerScreenClassTemplate.ftl");
        //File output
        Writer file = new FileWriter("src/main/java/ru/example/screens/" + className + ".java");
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

        template = cfg.getTemplate("FreeMarkerTestMethodTemplate.ftl");

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
                template = cfg.getTemplate("FreeMakerAssertThatIsDisplayedTemplate.ftl");
                break;
            case "equals":
                map.put("expected", ("\"" + step.split(" ")[3]) + "\"");
                template = cfg.getTemplate("FreeMakerAssertEqualsTemplate.ftl");
                break;

            default:
        }

        StringWriter stringWriter = new StringWriter();
        template.process(map, stringWriter);

        return stringWriter.toString();
    }
}