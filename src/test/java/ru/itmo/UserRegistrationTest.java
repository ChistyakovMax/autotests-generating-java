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

import static org.junit.Assert.assertTrue;

@DisplayName("TestSuite UserRegistration")
public class UserRegistrationTest extends BaseTest {

    RegistrationPage registrationPage;

    @Before
    public void start() {
        registrationPage = new RegistrationPage(driver);
        PageFactory.initElements(driver, registrationPage);
    }

    @Test
    @DisplayName("TestCase UserRegistration_InvalidPassword_GetError")
    public void testCaseUserRegistration_InvalidPassword_GetError() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
		new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(registrationPage.signUpButton()));
		registrationPage.fillNameInput("Max");
		registrationPage.fillEmailInput("testemail@gmail.com");
		//Минимальный пароль — шесть символов
		registrationPage.fillPasswordInput("1234");
		registrationPage.clickSignUpButton();
		new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(registrationPage.incorrectPasswordError()));
		registrationPage.assertIncorrectPasswordErrorEquals("Некорректный пароль");
    }

}