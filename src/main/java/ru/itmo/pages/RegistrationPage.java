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
public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
        currentUrl = baseUrl + "register";
    }

    @FindBy(xpath = ".//fieldset[2]//input")
    private WebElement emailInput;

	@FindBy(xpath = ".//button[text()='Зарегистрироваться']")
    private WebElement signUpButton;

	@FindBy(xpath = ".//fieldset[3]//input")
    private WebElement passwordInput;

	@FindBy(xpath = ".//p[text()='Некорректный пароль']")
    private WebElement incorrectPasswordError;

	@FindBy(xpath = ".//fieldset[1]//input")
    private WebElement nameInput;

    @Step("Взять текст у элемента emailInput на странице Registration")
    public String getTextEmailInput() {
        return emailInput.getText();
    }

	@Step("Заполнить поле nameInput на странице Registration")
    public RegistrationPage fillNameInput(String parameter) {
        nameInput.sendKeys(parameter);

        return this;
    }

	@Step("Проверка, что элемент emailInput НЕ отображается на странице Registration")
    public void assertEmailInputIsNotDisplayed() {
        assertFalse(emailInput.isDisplayed());
    }

	@Step("Проверка, что текст элемента emailInput соответствует параметру")
    public void assertEmailInputEquals(String expected) {
        assertEquals(expected, getTextEmailInput());
    }

	@Step("Проверка, что элемент passwordInput НЕ отображается на странице Registration")
    public void assertPasswordInputIsNotDisplayed() {
        assertFalse(passwordInput.isDisplayed());
    }

	@Step("Очистить поле nameInput на странице Registration")
    public RegistrationPage clearNameInput() {
        nameInput.clear();

        return this;
    }

	@Step("Взять текст у элемента incorrectPasswordError на странице Registration")
    public String getTextIncorrectPasswordError() {
        return incorrectPasswordError.getText();
    }

	@Step("Проверка, что элемент emailInput отображается на странице Registration")
    public void assertEmailInputIsDisplayed() {
        assertTrue(emailInput.isDisplayed());
    }

	@Step("Очистить поле emailInput на странице Registration")
    public RegistrationPage clearEmailInput() {
        emailInput.clear();

        return this;
    }

	@Step("Взять текст у элемента nameInput на странице Registration")
    public String getTextNameInput() {
        return nameInput.getText();
    }

	@Step("Взять текст у элемента passwordInput на странице Registration")
    public String getTextPasswordInput() {
        return passwordInput.getText();
    }

	@Step("Проверка, что текст элемента passwordInput соответствует параметру")
    public void assertPasswordInputEquals(String expected) {
        assertEquals(expected, getTextPasswordInput());
    }

	@Step("Проверка, что элемент nameInput отображается на странице Registration")
    public void assertNameInputIsDisplayed() {
        assertTrue(nameInput.isDisplayed());
    }

	@Step("Проверка, что текст элемента nameInput соответствует параметру")
    public void assertNameInputEquals(String expected) {
        assertEquals(expected, getTextNameInput());
    }

	@Step("Очистить поле passwordInput на странице Registration")
    public RegistrationPage clearPasswordInput() {
        passwordInput.clear();

        return this;
    }

	@Step("Проверка, что текст элемента signUpButton соответствует параметру")
    public void assertSignUpButtonEquals(String expected) {
        assertEquals(expected, getTextSignUpButton());
    }

	@Step("Проверка, что элемент incorrectPasswordError НЕ отображается на странице Registration")
    public void assertIncorrectPasswordErrorIsNotDisplayed() {
        assertFalse(incorrectPasswordError.isDisplayed());
    }

	@Step("Заполнить поле emailInput на странице Registration")
    public RegistrationPage fillEmailInput(String parameter) {
        emailInput.sendKeys(parameter);

        return this;
    }

	@Step("Взять текст у элемента signUpButton на странице Registration")
    public String getTextSignUpButton() {
        return signUpButton.getText();
    }

	@Step("Проверка, что элемент signUpButton НЕ отображается на странице Registration")
    public void assertSignUpButtonIsNotDisplayed() {
        assertFalse(signUpButton.isDisplayed());
    }

	@Step("Проверка, что текст элемента incorrectPasswordError соответствует параметру")
    public void assertIncorrectPasswordErrorEquals(String expected) {
        assertEquals(expected, getTextIncorrectPasswordError());
    }

	@Step("Проверка, что элемент passwordInput отображается на странице Registration")
    public void assertPasswordInputIsDisplayed() {
        assertTrue(passwordInput.isDisplayed());
    }

	@Step("Проверка, что элемент nameInput НЕ отображается на странице Registration")
    public void assertNameInputIsNotDisplayed() {
        assertFalse(nameInput.isDisplayed());
    }

	@Step("Заполнить поле passwordInput на странице Registration")
    public RegistrationPage fillPasswordInput(String parameter) {
        passwordInput.sendKeys(parameter);

        return this;
    }

	@Step("Проверка, что элемент signUpButton отображается на странице Registration")
    public void assertSignUpButtonIsDisplayed() {
        assertTrue(signUpButton.isDisplayed());
    }

	@Step("Двойной клик на кнопку signUpButton на странице Registration")
    public RegistrationPage doubleClickSignUpButton() {
        signUpButton.click();
        signUpButton.click();

        return this;
    }

	@Step("Проверка, что элемент incorrectPasswordError отображается на странице Registration")
    public void assertIncorrectPasswordErrorIsDisplayed() {
        assertTrue(incorrectPasswordError.isDisplayed());
    }

	@Step("Клик на кнопку signUpButton на странице Registration")
    public RegistrationPage clickSignUpButton() {
        signUpButton.click();

        return this;
    }
}