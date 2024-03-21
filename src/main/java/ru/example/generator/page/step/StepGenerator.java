package ru.example.generator.page.step;

import utils.pageobject.ElementType;
import utils.pageobject.yaml.Element;

import java.util.Set;

public class StepGenerator {
    StepForButtonGenerator stepForButtonGenerator;
    StepForInputGenerator stepForInputGenerator;
    StepForWebElementGenerator stepForWebElementGenerator = new StepForWebElementGenerator();
    Set<String> setOfSteps;

    //генерирует строку для шагов для работы с веб-элементами
    public Set<String> generateStepsByTemplate(Element element, String pageName) throws Exception {
        setOfSteps = stepForWebElementGenerator.generateStepsForWebElement(element, pageName);
        ElementType elementType = ElementType.valueOf(element.getElementType().toUpperCase());

        switch (elementType) {
            case BUTTON:
                stepForButtonGenerator = new StepForButtonGenerator();
                setOfSteps.addAll(stepForButtonGenerator.generateStepsForButton(element, pageName));
                break;
            case INPUT:
                stepForInputGenerator = new StepForInputGenerator();
                setOfSteps.addAll(stepForInputGenerator.generateStepsForInput(element, pageName));
                break;
            case WEBELEMENT:
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + element.getElementType());
        }
        return setOfSteps;
    }

}
