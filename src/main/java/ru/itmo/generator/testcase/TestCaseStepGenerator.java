package ru.itmo.generator.testcase;

import ru.itmo.generator.TemplateGenerator;
import utils.Prettier;
import utils.testcase.step.TestCaseStep;
import utils.testcase.step.TestCaseStepAction;
import utils.testcase.step.TestCaseStepAssert;
import utils.testcase.step.TestCaseStepComment;
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
            elementsForTemplate.put("pageClassName", Prettier.getNameWithUpperCaseFirstLetter(step.getPageName()));
            elementsForTemplate.put("pageName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getPageName()));
            return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
        }
        return "";
    }

    static String generateTestCaseStep(TestCaseStep step) throws Exception {
        elementsForTemplate = new HashMap<>();
        Map<TestStepAssertType, String> asserts = getAssertsHashMap();
        Map<TestStepActionType, String> actions = getActionsHashMap();

        String templateAssertFilePath = "testcase/teststep/assert/TestStepAssertTemplate.ftl";
        String templateAssertFilePathWithParameter = "testcase/teststep/assert/TestStepAssertTemplateWithParameter.ftl";
        String templateActionFilePath = "testcase/teststep/action/TestStepActionTemplate.ftl";
        String templateActionFilePathWithParameter = "testcase/teststep/action/TestStepActionTemplateWithParameter.ftl";
        String templateGoToUrl = "/testcase/teststep/action/GoToUrlTemplate.ftl";
        String templateWaitForElement = "/testcase/teststep/WaitForElementTemplate.ftl";

        TestCaseStepAssert testCaseStepAssert;
        TestCaseStepAction testCaseStepAction;
        TestCaseStepComment testCaseStepComment;

        if (step.getTestStepType().equals(TestStepType.ASSERT)) {
            testCaseStepAssert = (TestCaseStepAssert) step;
            elementsForTemplate.put("pageName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getPageName()));
            elementsForTemplate.put("elementNameWithUpperCaseFirstLetter", Prettier.getNameWithUpperCaseFirstLetter(step.getElementName()));
            elementsForTemplate.put("assertType", asserts.get(testCaseStepAssert.getTestStepAssertType()));
            switch (testCaseStepAssert.getTestStepAssertType()) {
                case EQUALS:
                    elementsForTemplate.put("parameter", testCaseStepAssert.getExpectedText());
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateAssertFilePathWithParameter);
                default:
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateAssertFilePath);
            }

        } else if (step.getTestStepType().equals(TestStepType.ACTION)) {
            testCaseStepAction = (TestCaseStepAction) step;

            switch (testCaseStepAction.getTestStepActionType()) {
                case GO_TO_PAGE:
                    //TODO доделать логику перехода на страницу
                    elementsForTemplate.put("parameter", testCaseStepAction.getReachedUrl());
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateActionFilePathWithParameter);

                case GO_TO_URL:
                    elementsForTemplate.put("url", testCaseStepAction.getReachedUrl());
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateGoToUrl);

                case FILL:
                    elementsForTemplate.put("pageName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getPageName()));
                    elementsForTemplate.put("actionType", actions.get(testCaseStepAction.getTestStepActionType()));
                    elementsForTemplate.put("elementNameWithUpperCaseFirstLetter", Prettier.getNameWithUpperCaseFirstLetter(step.getElementName()));
                    elementsForTemplate.put("parameter", testCaseStepAction.getFillingText());
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateActionFilePathWithParameter);

                case WAIT_FOR_ELEMENT:
                    elementsForTemplate.put("pageName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getPageName()));
                    elementsForTemplate.put("elementName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getElementName()));
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateWaitForElement);
                default:
                    elementsForTemplate.put("pageName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getPageName()));
                    elementsForTemplate.put("actionType", actions.get(testCaseStepAction.getTestStepActionType()));
                    elementsForTemplate.put("elementNameWithUpperCaseFirstLetter", Prettier.getNameWithUpperCaseFirstLetter(step.getElementName()));
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateActionFilePath);
            }

        } else if (step.getTestStepType().equals(TestStepType.COMMENT)) {
            testCaseStepComment = (TestCaseStepComment) step;
            return testCaseStepComment.getComment();
        } else throw new IllegalArgumentException("Unexpected value:" + step.getTestStepType());
    }

    private static Map<TestStepAssertType, String> getAssertsHashMap() {
        Map<TestStepAssertType, String> asserts = new HashMap<>();
        asserts.put(IS_DISPLAYED, "isDisplayed");
        asserts.put(IS_NOT_DISPLAYED, "isNotDisplayed");
        asserts.put(EQUALS, "Equals");
        asserts.put(IS_CURRENT_URL, "isCurrentUrl");

        return asserts;
    }

    private static Map<TestStepActionType, String> getActionsHashMap() {
        Map<TestStepActionType, String> actions = new HashMap<>();
        actions.put(CLICK, "click");
        actions.put(DOUBLE_CLICK, "doubleClick");
        actions.put(FILL, "fill");
        actions.put(CLEAR, "clear");
        actions.put(GO_TO_URL, "goTo");

        return actions;
    }

    public static String generateInitOfPage(TestCaseStep step) throws Exception {
        elementsForTemplate = new HashMap<>();
        templateFilePath = "testcase/teststep/InitPageTemplate.ftl";

        if (!step.getPageName().isEmpty()) {
            elementsForTemplate.put("pageClassName", step.getPageName());
            elementsForTemplate.put("pageName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getPageName()));
            return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
        }
        return "";
    }
}
