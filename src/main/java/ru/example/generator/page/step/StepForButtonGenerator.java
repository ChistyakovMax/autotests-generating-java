package ru.example.generator.page.step;

import ru.example.generator.TemplateGenerator;
import utils.Prettier;
import utils.pageobject.yaml.Element;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StepForButtonGenerator {
    Map<String, Object> elementsForTemplate = new HashMap<>();
    Set<String> stepsForButton = new HashSet<>();
    String templateFilePath;

    //генерирует шаги для работы с Кнопкой
    protected Set<String> generateStepsForButton(Element element, String pageName) throws Exception {
        stepsForButton.clear();
        stepsForButton.add(generateClickStepForButton(element, pageName));
        stepsForButton.add(generateDoubleClickStepForButton(element, pageName));

        return stepsForButton;
    }

    private String generateClickStepForButton(Element element, String pageName) throws Exception {
        templateFilePath = "page/step/button/ButtonClickTemplate.ftl";
        elementsForTemplate.clear();

        elementsForTemplate.put("elementName", element.getElementName());
        elementsForTemplate.put("elementNameWithUpperCaseFirstLetter", Prettier.getElementNameWithUpperCaseFirstLetter(element));
        elementsForTemplate.put("pageName", pageName);

        return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
    }

    private String generateDoubleClickStepForButton(Element element, String pageName) throws Exception {
        templateFilePath = "page/step/button/ButtonDoubleClickTemplate.ftl";
        elementsForTemplate.clear();

        elementsForTemplate.put("elementName", element.getElementName());
        elementsForTemplate.put("elementNameWithUpperCaseFirstLetter", Prettier.getElementNameWithUpperCaseFirstLetter(element));
        elementsForTemplate.put("pageName", pageName);

        return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
    }
}
