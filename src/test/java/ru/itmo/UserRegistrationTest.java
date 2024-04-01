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

@DisplayName("TestSuite UserRegistration")
public class UserRegistrationTest extends BaseTest {

    ProfilePage profilePage;
	RegistrationPage registrationPage;
	PasswordRecoveryPage passwordRecoveryPage;

    @Before
    public void start() {
        profilePage = new ProfilePage(driver);
        PageFactory.initElements(driver, profilePage);

		registrationPage = new RegistrationPage(driver);
        PageFactory.initElements(driver, registrationPage);

		passwordRecoveryPage = new PasswordRecoveryPage(driver);
        PageFactory.initElements(driver, passwordRecoveryPage);
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
		passwordRecoveryPage.clickRestorePasswordButton();
    }

	@Test
    @DisplayName("TestCase UserRegistration_InvalidPassword_GetError2")
    public void testCaseUserRegistration_InvalidPassword_GetError2() {
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
		profilePage.clickConstructorButton();
    }

	@Test
    @DisplayName("TestCase UserRegistration_InvalidPassword_GetError3")
    public void testCaseUserRegistration_InvalidPassword_GetError3() {
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