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
public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver) {
        super(driver);
        currentUrl = baseUrl + "account/profile";
    }

    @FindBy(xpath = ".//a[text() = 'Профиль']")
    private WebElement profileLink;

	@FindBy(xpath = ".//p[text() = 'Конструктор']")
    private WebElement constructorButton;

	@FindBy(xpath = ".//button[text()='Выход']")
    private WebElement logOutButton;

    @Step("Двойной клик на кнопку constructorButton на странице Profile")
    public ProfilePage doubleClickConstructorButton() {
        constructorButton.click();
        constructorButton.click();

        return this;
    }

	@Step("Взять текст у элемента constructorButton на странице Profile")
    public String getTextConstructorButton() {
        return constructorButton.getText();
    }

	@Step("Клик на кнопку logOutButton на странице Profile")
    public ProfilePage clickLogOutButton() {
        logOutButton.click();

        return this;
    }

	@Step("Взять текст у элемента profileLink на странице Profile")
    public String getTextProfileLink() {
        return profileLink.getText();
    }

	@Step("Клик на кнопку constructorButton на странице Profile")
    public ProfilePage clickConstructorButton() {
        constructorButton.click();

        return this;
    }

	@Step("Проверка, что текст элемента logOutButton соответствует параметру")
    public void assertLogOutButtonEquals(String expected) {
        assertEquals(expected, getTextLogOutButton());
    }

	@Step("Проверка, что элемент profileLink НЕ отображается на странице Profile")
    public void assertProfileLinkIsNotDisplayed() {
        assertFalse(profileLink.isDisplayed());
    }

	@Step("Проверка, что элемент profileLink отображается на странице Profile")
    public void assertProfileLinkIsDisplayed() {
        assertTrue(profileLink.isDisplayed());
    }

	@Step("Двойной клик на кнопку logOutButton на странице Profile")
    public ProfilePage doubleClickLogOutButton() {
        logOutButton.click();
        logOutButton.click();

        return this;
    }

	@Step("Взять текст у элемента logOutButton на странице Profile")
    public String getTextLogOutButton() {
        return logOutButton.getText();
    }

	@Step("Проверка, что элемент logOutButton отображается на странице Profile")
    public void assertLogOutButtonIsDisplayed() {
        assertTrue(logOutButton.isDisplayed());
    }

	@Step("Проверка, что элемент constructorButton НЕ отображается на странице Profile")
    public void assertConstructorButtonIsNotDisplayed() {
        assertFalse(constructorButton.isDisplayed());
    }

	@Step("Проверка, что текст элемента profileLink соответствует параметру")
    public void assertProfileLinkEquals(String expected) {
        assertEquals(expected, getTextProfileLink());
    }

	@Step("Проверка, что элемент constructorButton отображается на странице Profile")
    public void assertConstructorButtonIsDisplayed() {
        assertTrue(constructorButton.isDisplayed());
    }

	@Step("Проверка, что текст элемента constructorButton соответствует параметру")
    public void assertConstructorButtonEquals(String expected) {
        assertEquals(expected, getTextConstructorButton());
    }

	@Step("Проверка, что элемент logOutButton НЕ отображается на странице Profile")
    public void assertLogOutButtonIsNotDisplayed() {
        assertFalse(logOutButton.isDisplayed());
    }
}