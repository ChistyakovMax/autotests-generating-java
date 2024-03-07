package ru.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ${pageName}Page extends BasePage {

    public ${pageName}Page(WebDriver driver) {
        super(driver);
    }

${webElements}
${steps}
}