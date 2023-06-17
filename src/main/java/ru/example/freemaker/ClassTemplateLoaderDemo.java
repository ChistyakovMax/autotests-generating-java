package ru.example.freemaker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
public class ClassTemplateLoaderDemo {

    static Configuration cfg;
    public static void createTestClassByTemplate(String className, String classFilling) throws Exception {
            cfg = ConfigurationUtil.getConfiguration();
            //Create Data Model
            Map<String, Object> map = new HashMap<>();
            map.put("className", className);
            map.put("classFilling", classFilling);
            Template template = cfg.getTemplate("FreeMarkerScreenClassTemplate.ftl");
            //Console output for template
            Writer console = new OutputStreamWriter(System.out);
            template.process(map, console);
            console.flush();
            //File output
            Writer file = new FileWriter (new File("template-output.html"));
            template.process(map, file);
            file.flush();
            file.close();
    }

    public static void createTestMethodByTemplate(String returnType, String name, String element, String action)
            throws Exception {
        cfg = ConfigurationUtil.getConfiguration();
        //Create Data Model
        Map<String, Object> map = new HashMap<>();
        map.put("returnType", returnType);
        map.put("name", name);
        map.put("element", element);
        map.put("action", action);

        Template template = cfg.getTemplate("FreeMarkerTestMethodTemplate.ftl");
        //File output
        Writer file = new FileWriter (new File("template-output.html"));
        template.process(map, file);
        file.flush();
        file.close();
    }
}
