package ru.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BasePage {

    WebDriver driver;

    protected static final String baseUrl = "${baseUrl}";

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

${webElements}
${steps}
}
