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
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        currentUrl = baseUrl + "login";
    }

    @FindBy(xpath = ".//button[text()='Войти']")
    private WebElement signInButton;

	@FindBy(xpath = ".//input[@name = 'name']")
    private WebElement emailInput;

	@FindBy(xpath = ".//input[@type = 'password']")
    private WebElement passwordInput;

    @Step("Двойной клик на кнопку signInButton на странице Login")
    public LoginPage doubleClickSignInButton() {
        signInButton.click();
        signInButton.click();

        return this;
    }

	@Step("Очистить поле emailInput на странице Login")
    public LoginPage clearEmailInput() {
        emailInput.clear();

        return this;
    }

	@Step("Заполнить поле emailInput на странице Login")
    public LoginPage fillEmailInput(String parameter) {
        emailInput.sendKeys(parameter);

        return this;
    }

	@Step("Проверка, что элемент passwordInput отображается на странице Login")
    public void assertPasswordInputIsDisplayed() {
        assertTrue(passwordInput.isDisplayed());
    }

	@Step("Проверка, что элемент signInButton НЕ отображается на странице Login")
    public void assertSignInButtonIsNotDisplayed() {
        assertFalse(signInButton.isDisplayed());
    }

	@Step("Взять текст у элемента emailInput на странице Login")
    public String getTextEmailInput() {
        return emailInput.getText();
    }

	@Step("Взять текст у элемента signInButton на странице Login")
    public String getTextSignInButton() {
        return signInButton.getText();
    }

	@Step("Проверка, что элемент signInButton отображается на странице Login")
    public void assertSignInButtonIsDisplayed() {
        assertTrue(signInButton.isDisplayed());
    }

	@Step("Клик на кнопку signInButton на странице Login")
    public LoginPage clickSignInButton() {
        signInButton.click();

        return this;
    }

	@Step("Проверка, что текст элемента emailInput соответствует параметру")
    public void assertEmailInputEquals(String expected) {
        assertEquals(expected, getTextEmailInput());
    }

	@Step("Проверка, что элемент passwordInput НЕ отображается на странице Login")
    public void assertPasswordInputIsNotDisplayed() {
        assertFalse(passwordInput.isDisplayed());
    }

	@Step("Проверка, что текст элемента signInButton соответствует параметру")
    public void assertSignInButtonEquals(String expected) {
        assertEquals(expected, getTextSignInButton());
    }

	@Step("Очистить поле passwordInput на странице Login")
    public LoginPage clearPasswordInput() {
        passwordInput.clear();

        return this;
    }

	@Step("Проверка, что элемент emailInput отображается на странице Login")
    public void assertEmailInputIsDisplayed() {
        assertTrue(emailInput.isDisplayed());
    }

	@Step("Заполнить поле passwordInput на странице Login")
    public LoginPage fillPasswordInput(String parameter) {
        passwordInput.sendKeys(parameter);

        return this;
    }

	@Step("Взять текст у элемента passwordInput на странице Login")
    public String getTextPasswordInput() {
        return passwordInput.getText();
    }

	@Step("Проверка, что элемент emailInput НЕ отображается на странице Login")
    public void assertEmailInputIsNotDisplayed() {
        assertFalse(emailInput.isDisplayed());
    }

	@Step("Проверка, что текст элемента passwordInput соответствует параметру")
    public void assertPasswordInputEquals(String expected) {
        assertEquals(expected, getTextPasswordInput());
    }
}