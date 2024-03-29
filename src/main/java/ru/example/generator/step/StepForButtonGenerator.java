package ru.example.generator.step;

import ru.example.generator.TemplateGenerator;
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
        templateFilePath = "actions/button/FreeMakerButtonClickTemplate.ftl";
        elementsForTemplate.clear();

        elementsForTemplate.put("elementName", element.getElementName());
        elementsForTemplate.put("pageName", pageName);

        return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
    }

    private String generateDoubleClickStepForButton(Element element, String pageName) throws Exception {
        templateFilePath = "actions/button/FreeMakerButtonDoubleClickTemplate.ftl";
        elementsForTemplate.clear();

        elementsForTemplate.put("elementName", element.getElementName());
        elementsForTemplate.put("pageName", pageName);

        return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
    }
}
