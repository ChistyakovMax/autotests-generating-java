package ru.example.generator.testcase;

import ru.example.generator.TemplateGenerator;
import utils.Prettier;
import utils.testcase.step.TestCaseStep;
import utils.testcase.step.TestCaseStepAction;
import utils.testcase.step.TestCaseStepAssert;
import utils.testcase.types.TestStepActionType;
import utils.testcase.types.TestStepAssertType;
import utils.testcase.types.TestStepType;

import java.util.HashMap;
import java.util.Map;

import static utils.testcase.types.TestStepAssertType.*;
import static utils.testcase.types.TestStepActionType.*;

public class TestCaseStepGenerator {
    static Map<String, Object> elementsForTemplate;
    static String templateFilePath;
    static String generatePageInTestCase(TestCaseStep step) throws Exception {
        elementsForTemplate = new HashMap<>();
        templateFilePath = "testcase/PageInTestCaseTemplate.ftl";

        if (!step.getPageName().isEmpty()) {
            elementsForTemplate.put("pageClassName", step.getPageName());
            elementsForTemplate.put("pageName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getPageName()));
            return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
        }
        return null;
    }

    static String generateTestCaseStep(TestCaseStep step) throws Exception {
        elementsForTemplate = new HashMap<>();
        Map<TestStepAssertType, String> asserts =  getAssertsHashMap();
        Map<TestStepActionType, String> actions =  getActionsHashMap();

        String templateAssertFilePath = "testcase/teststep/assert/TestStepAssertTemplate.ftl";
        String templateAssertFilePathWithParameter = "testcase/teststep/assert/TestStepAssertTemplateWithParameter.ftl";
        String templateActionFilePath = "testcase/teststep/action/TestStepActionTemplate.ftl";
        String templateActionFilePathWithParameter = "testcase/teststep/action/TestStepActionTemplateWithParameter.ftl";

        TestCaseStepAssert testCaseStepAssert;
        TestCaseStepAction testCaseStepAction;

        if(step.getTestStepType().equals(TestStepType.ASSERT)) {
            testCaseStepAssert = (TestCaseStepAssert) step;
            elementsForTemplate.put("pageName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getPageName()));
            elementsForTemplate.put("elementNameWithUpperCaseFirstLetter",Prettier.getElementNameWithUpperCaseFirstLetter(step.getElementName()));
            elementsForTemplate.put("assertType", asserts.get(testCaseStepAssert.getTestStepAssertType()));
            switch (testCaseStepAssert.getTestStepAssertType()) {
                case EQUALS:
                    elementsForTemplate.put("parameter", testCaseStepAssert.getExpectedText());
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateAssertFilePathWithParameter);
                default:
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateAssertFilePath);
            }

        }
        else if (step.getTestStepType().equals(TestStepType.ACTION)) {
            testCaseStepAction = (TestCaseStepAction) step;
            elementsForTemplate.put("pageName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getPageName()));
            elementsForTemplate.put("actionType", actions.get(testCaseStepAction.getTestStepActionType()));
            elementsForTemplate.put("elementNameWithUpperCaseFirstLetter", Prettier.getElementNameWithUpperCaseFirstLetter(step.getElementName()));
            switch (testCaseStepAction.getTestStepActionType()) {
                case FILL:
                    elementsForTemplate.put("parameter", testCaseStepAction.getFillingText());
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateActionFilePathWithParameter);
                case GO_TO:
                    elementsForTemplate.put("parameter", testCaseStepAction.getReachedUrl());
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateActionFilePathWithParameter);
                default:
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateActionFilePath);
            }

        }
        else throw new IllegalArgumentException("Unexpected value:" + step.getTestStepType());
    }

    private static Map getAssertsHashMap() {
        Map<TestStepAssertType, String> asserts = new HashMap<>();
        asserts.put(IS_DISPLAYED, "isDisplayed");
        asserts.put(IS_NOT_DISPLAYED, "isNotDisplayed");
        asserts.put(EQUALS, "Equals");
        asserts.put(IS_CURRENT_URL, "isCurrentUrl");

        return asserts;
    }

    private static Map getActionsHashMap() {
        Map<TestStepActionType, String> actions = new HashMap<>();
        actions.put(CLICK, "click");
        actions.put(DOUBLE_CLICK,"doubleClick");
        actions.put(FILL, "fill");
        actions.put(CLEAR,"clear");
        actions.put(GO_TO,"goTo");

        return actions;
    }
}
