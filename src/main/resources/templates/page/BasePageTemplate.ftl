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
public abstract class BasePage {

    WebDriver driver;
    protected static final String baseUrl = "${baseUrl}";
    protected String currentUrl;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCurrentUrl() {
            return currentUrl;
    }

    ${webElements}

    ${steps}

    @Step("Проверка, что драйвер находится на текущей странице")
    public void assertCurrentPageIsOpened() {
        assertTrue(driver.getCurrentUrl().contains(getCurrentUrl()));
    }
}
