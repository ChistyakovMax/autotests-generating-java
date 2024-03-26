package utils.testcase.step;

import lombok.Getter;
import lombok.Setter;
import ru.itmo.generator.page.PageGenerator;
import utils.Prettier;
import utils.pageobject.yaml.Element;
import utils.pageobject.yaml.Page;
import utils.pageobject.yaml.Pages;
import utils.testcase.types.TestStepType;

import static utils.Constants.basePageName;

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

    protected String getPageName(String step) throws IllegalArgumentException {
        pageAndElement = getPageAndElement(step);
        boolean isPageFound = false;
        Pages pages = new PageGenerator().getPagesFromYAML();
        String currentPageName = Prettier
                .getNameWithUpperCaseFirstLetter
                        (pageAndElement.split("\\.")[0]);

        for (Page page : pages.getPages()) {
            if (page.getPageName().equals(currentPageName)) {
                isPageFound = true;
                break;
            }
        }

        if (isPageFound) {
            return currentPageName;
        } else {
            throw new IllegalArgumentException("Страницы " + currentPageName + "не существует в YAML файле!");
        }
    }

    protected String getElementName(String step) {
        String currentPageName = getPageName(step);
        pageAndElement = getPageAndElement(step);
        boolean isElementFound = false;
        Pages pages = new PageGenerator().getPagesFromYAML();
        String currentElementName = Prettier
                .getElementNameWithLowerCaseFirstLetter
                        (pageAndElement.split("\\.")[1]);

        foundElement:
        for (Page page : pages.getPages()) {
            if (page.getPageName().equals(currentPageName)
                    || page.getPageName().equals(basePageName)) {
                for (Element element : page.getElements()) {
                    if (element.getElementName().contains(currentElementName)) {
                        isElementFound = true;
                        currentElementName = element.getElementName();
                        break foundElement;
                    }
                }
            }
        }

        if (isElementFound) {
            return currentElementName;
        } else {
            throw new IllegalArgumentException("Элемента " + currentElementName + " не существует на странице " + currentPageName);
        }
    }

}
