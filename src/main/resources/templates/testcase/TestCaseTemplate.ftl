package ru.example;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import ru.example.pages.*;

@DisplayName("TestCase ${name}")
public class ${name}Test extends BaseTest {

    MainPage mainPage;
    ${testCasePages}

    @Before
    public void start() {
        mainPage = new MainPage(driver);
        PageFactory.initElements(driver, mainPage);
        driver.get(mainPage.getCurrentUrl());
    }

    @Test
    @DisplayName("${name}")
    public void testCase${name}() {
        ${testCaseSteps}
    }

}
