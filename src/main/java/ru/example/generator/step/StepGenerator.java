package ru.example.generator.step;

import utils.pageobject.ElementType;
import utils.pageobject.yaml.Element;

import java.util.HashSet;
import java.util.Set;

public class StepGenerator {
    StepForButtonGenerator stepForButtonGenerator;
    StepForInputGenerator stepForInputGenerator;
    StepForWebElementGenerator stepForWebElementGenerator;
    Set<String> setOfSteps;

    //генерирует строку для шагов для работы с веб-элементами
    public Set<String> generateStepsByTemplate(Element element, String pageName) throws Exception {
        setOfSteps = new HashSet<>();

        ElementType elementType = ElementType.valueOf(element.getElementType().toUpperCase());
        switch (elementType) {
            case BUTTON:
                stepForButtonGenerator = new StepForButtonGenerator();
                setOfSteps = stepForButtonGenerator.generateStepsForButton(element, pageName);
                break;
            case INPUT:
                stepForInputGenerator = new StepForInputGenerator();
                setOfSteps = stepForInputGenerator.generateStepsForInput(element, pageName);
                break;
            case WEBELEMENT:
                stepForWebElementGenerator = new StepForWebElementGenerator();
                setOfSteps = stepForWebElementGenerator.generateStepsForWebElement(element, pageName);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + element.getElementType());
        }
        return setOfSteps;
    }

}
