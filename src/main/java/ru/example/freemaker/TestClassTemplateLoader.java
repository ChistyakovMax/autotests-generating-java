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
    private static Map<String,String> actionsAndMethods = createActionsAndMethodsHashMap();

    private static Map createActionsAndMethodsHashMap() {
        Map<String, String> map = new HashMap<>();
        map.put("click", "click");
        map.put("tap", "click");

        return map;
    }
    static Configuration cfg;
    public static void createTestClassByTemplate(String className, String classFilling) throws Exception {
            cfg = ConfigurationUtil.getConfiguration();

            Map<String, Object> map = new HashMap<>();
            map.put("className", className);
            map.put("classFilling", classFilling);

            Template template = cfg.getTemplate("FreeMarkerScreenClassTemplate.ftl");
            //File output
            Writer file = new FileWriter (new File("src/main/java/ru/example/screens/" + className + ".java"));
            template.process(map, file);
            file.flush();
            file.close();
    }

    public static String getStepByTemplate(String screen, String element, String action) throws Exception {
        cfg = ConfigurationUtil.getConfiguration();
        Map<String, Object> map = new HashMap<>();
        map.put("screen", screen);
        map.put("element", element);
        map.put("action", getMethodFromAction(action));

        Template template = cfg.getTemplate("FreeMarkerTestMethodTemplate.ftl");

        StringWriter stringWriter = new StringWriter();
        template.process(map, stringWriter);

        return stringWriter.toString();
    }

    public static String getAssertEqualsByTemplate(String screen, String expected, String element) throws Exception {
        cfg = ConfigurationUtil.getConfiguration();
        Map<String, Object> map = new HashMap<>();
        map.put("screen", screen);
        map.put("expected", expected);
        map.put("element", element);

        Template template = cfg.getTemplate("FreeMakerAssertEqualsTemplate.ftl");

        StringWriter stringWriter = new StringWriter();
        template.process(map, stringWriter);

        return stringWriter.toString();
    }




    public static String getStep(String screen, String element, String action) {
        return new StringBuilder().append("\tpublic ")
                .append(screen)
                .append(" ")
                .append(action)
                .append(element)
                .append("() {\n\t\t")
                .append(element)
                .append(getMethodFromAction(action))
                .append(";\n\t}")
                .toString();
    }

    private static String getMethodFromAction(String action) {
        return actionsAndMethods.get(action.toLowerCase());
    }

}
