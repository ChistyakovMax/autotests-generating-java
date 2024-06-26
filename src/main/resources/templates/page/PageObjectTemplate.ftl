package ru.itmo.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.*;

@Getter
@Accessors(fluent = true)
public class ${pageName}Page extends BasePage {

    public ${pageName}Page(WebDriver driver) {
        super(driver);
        currentUrl = baseUrl + "${additionalUrl}";
    }

    ${webElements}

    ${steps}
}