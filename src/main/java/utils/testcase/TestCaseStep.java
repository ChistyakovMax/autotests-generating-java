package utils.testcase;

import utils.Prettier;

public abstract class TestCaseStep {
    protected String pageName = "";
    protected String elementName = "";
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
        return pageAndElement.split("\\.")[1];
    }

}
