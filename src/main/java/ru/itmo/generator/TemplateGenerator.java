package ru.itmo.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import ru.itmo.freemarker.ConfigurationUtil;

import java.io.StringWriter;
import java.util.Map;

public class TemplateGenerator {
    static Configuration cfg;
    static Template template;

    public static String generateFromTemplate(Map<String, Object> elementsForTemplate, String templateFilePath) throws Exception {
        cfg = ConfigurationUtil.getConfiguration();
        template = cfg.getTemplate(templateFilePath);
        StringWriter stringWriter = new StringWriter();
        template.process(elementsForTemplate, stringWriter);

        return stringWriter.toString();
    }

    public static String generateFromTemplate(String templateFilePath) throws Exception {
        cfg = ConfigurationUtil.getConfiguration();
        template = cfg.getTemplate(templateFilePath);
        StringWriter stringWriter = new StringWriter();
        template.process(null, stringWriter);

        return stringWriter.toString();
    }
}
