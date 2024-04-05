package ru.itmo.generator.testsuite.testcase;

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

import static utils.testcase.types.TestStepActionType.*;
import static utils.testcase.types.TestStepAssertType.*;

public class TestCaseStepGenerator {
    static Map<String, Object> elementsForTemplate;
    static String templateFilePath;

    public static String generatePageInTestCase(TestCaseStep step) throws Exception {
        elementsForTemplate = new HashMap<>();
        templateFilePath = "testsuite/testcase/PageInTestCaseTemplate.ftl";

        if (!step.getPageName().isEmpty()) {
            elementsForTemplate.put("pageClassName", Prettier.getNameWithUpperCaseFirstLetter(step.getPageName()));
            elementsForTemplate.put("pageName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getPageName()));
            return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
        }
        return "";
    }

    public static String generateTestCaseStep(TestCaseStep step) throws Exception {
        elementsForTemplate = new HashMap<>();
        Map<TestStepAssertType, String> asserts = getAssertsHashMap();
        Map<TestStepActionType, String> actions = getActionsHashMap();

        //шаблоны Ассертов
        String templateAssertFilePath = "testsuite/teststep/assert/TestStepAssertTemplate.ftl";
        String templateAssertFilePathWithParameter = "testsuite/teststep/assert/TestStepAssertTemplateWithParameter.ftl";
        String templateAssertPageIsOpened = "testsuite/teststep/assert/TestStepAssertPageIsOpened.ftl";
        String templateAssertURLIsOpened = "testsuite/teststep/assert/TestStepAssertURLIsOpened.ftl";

        //шаблоны Действий
        String templateActionFilePath = "testsuite/teststep/action/TestStepActionTemplate.ftl";
        String templateActionFilePathWithParameter = "testsuite/teststep/action/TestStepActionTemplateWithParameter.ftl";
        String templateGoToUrl = "testsuite/teststep/action/GoToUrlTemplate.ftl";
        String templateGoToPage = "testsuite/teststep/action/GoToPageTemplate.ftl";
        String templateWaitForElement = "testsuite/teststep/WaitForElementTemplate.ftl";

        TestCaseStepAssert testCaseStepAssert;
        TestCaseStepAction testCaseStepAction;
        TestCaseStepComment testCaseStepComment;

        if (step.getTestStepType().equals(TestStepType.ASSERT)) {
            testCaseStepAssert = (TestCaseStepAssert) step;
            elementsForTemplate.put("assertType", asserts.get(testCaseStepAssert.getTestStepAssertType()));
            switch (testCaseStepAssert.getTestStepAssertType()) {
                case EQUALS:
                    elementsForTemplate.put("pageName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getPageName()));
                    elementsForTemplate.put("elementNameWithUpperCaseFirstLetter", Prettier.getNameWithUpperCaseFirstLetter(step.getElementName()));
                    elementsForTemplate.put("parameter", testCaseStepAssert.getExpectedText());
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateAssertFilePathWithParameter);

                case IS_CURRENT_PAGE:
                    elementsForTemplate.put("pageName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getPageName()));
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateAssertPageIsOpened);

                case IS_CURRENT_URL:
                    elementsForTemplate.put("URL", ((TestCaseStepAssert) step).getExpectedUrl());
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateAssertURLIsOpened);
                default:
                    elementsForTemplate.put("pageName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getPageName()));
                    elementsForTemplate.put("elementNameWithUpperCaseFirstLetter", Prettier.getNameWithUpperCaseFirstLetter(step.getElementName()));
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateAssertFilePath);
            }

        } else if (step.getTestStepType().equals(TestStepType.ACTION)) {
            testCaseStepAction = (TestCaseStepAction) step;

            switch (testCaseStepAction.getTestStepActionType()) {
                case GO_TO_PAGE:
                    elementsForTemplate.put("pageName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getPageName()));
                    return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateGoToPage);

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
        templateFilePath = "testsuite/teststep/InitPageTemplate.ftl";

        if (!step.getPageName().isEmpty()) {
            elementsForTemplate.put("pageClassName", step.getPageName());
            elementsForTemplate.put("pageName", Prettier.getElementNameWithLowerCaseFirstLetter(step.getPageName()));
            return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
        }
        return "";
    }

    public static String generateInitOfPage(String page) throws Exception {
        elementsForTemplate = new HashMap<>();
        page = page.trim().replace(";", "");
        templateFilePath = "testsuite/teststep/InitPageTemplate.ftl";

        elementsForTemplate.put("pageClassName", page.split(" ")[0]);
        elementsForTemplate.put("pageName", page.split(" ")[1]);
        return TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
    }
}
