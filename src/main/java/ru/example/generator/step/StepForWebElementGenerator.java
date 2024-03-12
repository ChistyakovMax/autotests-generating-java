package ru.example.generator.step;

import ru.example.generator.TemplateGenerator;
import utils.Prettier;
import utils.pageobject.yaml.Element;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StepForWebElementGenerator {
    Map<String, Object> elementsForTemplate = new HashMap<>();
    Set<String> stepsForWebElement = new HashSet<>();
    String templateFilePath;

    //генерирует шаги для работы с веб-элементом
    protected  Set<String> generateStepsForWebElement(Element element, String pageName) throws Exception {
        stepsForWebElement.clear();
        stepsForWebElement.add(generateGetTextForWebElement(element, pageName));
        stepsForWebElement.add(generateIsDisplayedForWebElement(element, pageName));
        stepsForWebElement.add(generateIsNotDisplayedForWebElement(element, pageName));

        return stepsForWebElement;
    }

    private String generateGetTextForWebElement(Element element, String pageName) throws Exception {
        templateFilePath = "actions/webelement/WebElementGetTextTemplate.ftl";
        elementsForTemplate.clear();

        elementsForTemplate.put("elementName", element.getElementName());
        elementsForTemplate.put("elementNameWithUpperCaseFirstLetter", Prettier.getElementNameWithUpperCaseFirstLetter(element));
        elementsForTemplate.put("pageName", pageName);

        return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
    }

    private String generateIsDisplayedForWebElement(Element element, String pageName) throws Exception {
        templateFilePath = "actions/webelement/WebElementIsDisplayed.ftl";
        elementsForTemplate.clear();

        elementsForTemplate.put("elementName", element.getElementName());
        elementsForTemplate.put("elementNameWithUpperCaseFirstLetter", Prettier.getElementNameWithUpperCaseFirstLetter(element));
        elementsForTemplate.put("pageName", pageName);

        return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
    }

    private String generateIsNotDisplayedForWebElement(Element element, String pageName) throws Exception {
        templateFilePath = "actions/webelement/WebElementIsNotDisplayed.ftl";
        elementsForTemplate.clear();

        elementsForTemplate.put("elementName", element.getElementName());
        elementsForTemplate.put("elementNameWithUpperCaseFirstLetter", Prettier.getElementNameWithUpperCaseFirstLetter(element));
        elementsForTemplate.put("pageName", pageName);

        return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
    }
}
