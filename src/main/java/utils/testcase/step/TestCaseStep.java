package utils.testcase.step;

import lombok.Getter;
import lombok.Setter;
import utils.Prettier;
import utils.testcase.types.TestStepType;

@Getter
@Setter
public abstract class TestCaseStep {
    protected String pageName = "";
    protected String elementName = "";
    protected TestStepType testStepType;
    private String pageAndElement = "";

    private String getPageAndElement(String step) {
        return step.split(" ")[1];
    }

    protected String getPageName(String step) {
        pageAndElement = getPageAndElement(step);
        return Prettier
                .getElementNameWithUpperCaseFirstLetter
                        (pageAndElement.split("\\.")[0]);
    }

    protected String getElementName(String step) {
        pageAndElement = getPageAndElement(step);
        return Prettier
                .getElementNameWithLowerCaseFirstLetter
                        (pageAndElement.split("\\.")[1]);
    }

}
