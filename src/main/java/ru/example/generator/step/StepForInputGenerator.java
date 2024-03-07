package ru.example.generator.step;

import ru.example.generator.TemplateGenerator;
import utils.pageobject.yaml.Element;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StepForInputGenerator {
    Map<String, Object> elementsForTemplate = new HashMap<>();
    Set<String> stepsForInput = new HashSet<>();
    String templateFilePath;

    //генерирует шаги для работы с полем ввода
    protected Set<String> generateStepsForInput(Element element, String pageName) throws Exception {
        stepsForInput.clear();
        stepsForInput.add(generateFillingStepForInput(element, pageName));
        stepsForInput.add(generateClearingStepForInput(element, pageName));

        return stepsForInput;
    }

    private String generateFillingStepForInput(Element element, String pageName) throws Exception {
        templateFilePath = "actions/input/InputFillingTemplate.ftl";
        elementsForTemplate.clear();

        elementsForTemplate.put("elementName", element.getElementName());
        elementsForTemplate.put("pageName", pageName);

        return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
    }

    private String generateClearingStepForInput(Element element, String pageName) throws Exception {
        templateFilePath = "actions/input/InputClearingTemplate.ftl";
        elementsForTemplate.clear();

        elementsForTemplate.put("elementName", element.getElementName());
        elementsForTemplate.put("pageName", pageName);

        return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
    }
}
