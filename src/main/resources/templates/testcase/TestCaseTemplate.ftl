package ru.itmo;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.itmo.pages.*;

import java.time.Duration;

@DisplayName("TestCase ${name}")
public class ${name}Test extends BaseTest {

    ${testCasePages}

    @Test
    @DisplayName("${name}")
    public void testCase${name}() {
        ${testCaseSteps}
    }

}
