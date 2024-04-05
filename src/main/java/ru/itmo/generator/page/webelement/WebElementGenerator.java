package ru.itmo.generator.page.webelement;

import ru.itmo.generator.TemplateGenerator;
import utils.pageobject.yaml.Element;

import java.util.HashMap;
import java.util.Map;

public class WebElementGenerator {
    Map<String, Object> elementsForTemplate = new HashMap<>();
    String templateFilePath = "page/step/webelement/WebElementTemplate.ftl";

    //генерирует сет для веб-элемента
    public String generateWebElementByTemplate(Element element) throws Exception {
        elementsForTemplate = new HashMap<>();

        elementsForTemplate.put("elementLocator", element.getElementLocator());
        elementsForTemplate.put("elementName", element.getElementName());

        return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
    }
}
