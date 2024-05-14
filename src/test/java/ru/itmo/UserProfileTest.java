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

@DisplayName("TestSuite UserProfile")
public class UserProfileTest extends BaseTest {

    MainPage mainPage;
	LoginPage loginPage;
	ProfilePage profilePage;

    @Before
    public void start() {
        mainPage = new MainPage(driver);
        PageFactory.initElements(driver, mainPage);

		loginPage = new LoginPage(driver);
        PageFactory.initElements(driver, loginPage);

		profilePage = new ProfilePage(driver);
        PageFactory.initElements(driver, profilePage);
    }

    @Test
    @DisplayName("TestCase ClickPersonalAccountButtonRedirectedToProfilePage")
    public void testCaseClickPersonalAccountButtonRedirectedToProfilePage() {
        driver.get(loginPage.getCurrentUrl());
		new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(loginPage.signInButton()));
		//ввести креды
		loginPage.fillEmailInput("chistyakovmaxim2000@gmail.com");
		loginPage.fillPasswordInput("Password1234!");
		loginPage.clickSignInButton();
		//потом перейти на главную страницу
		new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(mainPage.getOrderButton()));
		//а с главной страницы перейти в Профиль
		mainPage.clickPersonalAccountButton();
		new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(profilePage.profileLink()));
		profilePage.assertCurrentPageIsOpened();
    }

	@Test
    @DisplayName("TestCase ClickConstructorRedirectedToMainPage")
    public void testCaseClickConstructorRedirectedToMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
		new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(loginPage.signInButton()));
		//ввести креды
		loginPage.fillEmailInput("chistyakovmaxim2000@gmail.com");
		loginPage.fillPasswordInput("Password1234!");
		loginPage.clickSignInButton();
		//потом перейти на главную страницу
		new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(mainPage.getOrderButton()));
		//а с главной страницы перейти в Профиль
		mainPage.clickPersonalAccountButton();
		new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(profilePage.profileLink()));
		profilePage.clickConstructorButton();
		new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(mainPage.getOrderButton()));
		assertTrue(driver.getCurrentUrl().contains("https://stellarburgers.nomoreparties.site/"));
    }

}