package ru.example.generator.page.step;

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
        stepsForWebElement.add(generateAssertIsDisplayedForWebElement(element, pageName));
        stepsForWebElement.add(generateAssertIsNotDisplayedForWebElement(element, pageName));
        stepsForWebElement.add(generateAssertEqualsForWebElement(element, pageName));

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

    private String generateAssertIsDisplayedForWebElement(Element element, String pageName) throws Exception {
        templateFilePath = "actions/webelement/WebElementAssertIsDisplayed.ftl";
        elementsForTemplate.clear();

        elementsForTemplate.put("elementName", element.getElementName());
        elementsForTemplate.put("elementNameWithUpperCaseFirstLetter", Prettier.getElementNameWithUpperCaseFirstLetter(element));
        elementsForTemplate.put("pageName", pageName);

        return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
    }

    private String generateAssertIsNotDisplayedForWebElement(Element element, String pageName) throws Exception {
        templateFilePath = "actions/webelement/WebElementAssertIsNotDisplayed.ftl";
        elementsForTemplate.clear();

        elementsForTemplate.put("elementName", element.getElementName());
        elementsForTemplate.put("elementNameWithUpperCaseFirstLetter", Prettier.getElementNameWithUpperCaseFirstLetter(element));
        elementsForTemplate.put("pageName", pageName);

        return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
    }

    private String generateAssertEqualsForWebElement(Element element, String pageName) throws Exception {
        templateFilePath = "actions/webelement/WebElementAssertEquals.ftl";
        elementsForTemplate.clear();

        elementsForTemplate.put("elementName", element.getElementName());
        elementsForTemplate.put("elementNameWithUpperCaseFirstLetter", Prettier.getElementNameWithUpperCaseFirstLetter(element));
        elementsForTemplate.put("pageName", pageName);

        return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
    }
}
