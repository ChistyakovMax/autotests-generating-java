package ru.itmo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.*;

public abstract class BasePage {

    WebDriver driver;
    protected static final String baseUrl = "https://stellarburgers.nomoreparties.site/";
    protected String currentUrl;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCurrentUrl() {
            return currentUrl;
    }

    @FindBy(xpath = ".//a[text() = 'Войти']")
    private WebElement signInLinkButton;

	@FindBy(xpath = ".//div[@class = 'AppHeader_header__logo__2D0X2']")
    private WebElement stellarburgersLogoButton;

    @Step("Проверка, что элемент signInLinkButton НЕ отображается на странице Base")
    public void assertSignInLinkButtonIsNotDisplayed() {
        assertFalse(signInLinkButton.isDisplayed());
    }

	@Step("Проверка, что элемент stellarburgersLogoButton отображается на странице Base")
    public void assertStellarburgersLogoButtonIsDisplayed() {
        assertTrue(stellarburgersLogoButton.isDisplayed());
    }

	@Step("Взять текст у элемента stellarburgersLogoButton на странице Base")
    public String getTextStellarburgersLogoButton() {
        return stellarburgersLogoButton.getText();
    }

	@Step("Двойной клик на кнопку stellarburgersLogoButton на странице Base")
    public BasePage doubleClickStellarburgersLogoButton() {
        stellarburgersLogoButton.click();
        stellarburgersLogoButton.click();

        return this;
    }

	@Step("Клик на кнопку signInLinkButton на странице Base")
    public BasePage clickSignInLinkButton() {
        signInLinkButton.click();

        return this;
    }

	@Step("Взять текст у элемента signInLinkButton на странице Base")
    public String getTextSignInLinkButton() {
        return signInLinkButton.getText();
    }

	@Step("Двойной клик на кнопку signInLinkButton на странице Base")
    public BasePage doubleClickSignInLinkButton() {
        signInLinkButton.click();
        signInLinkButton.click();

        return this;
    }

	@Step("Проверка, что текст элемента stellarburgersLogoButton соответствует параметру")
    public void assertStellarburgersLogoButtonEquals(String expected) {
        assertEquals(expected, getTextStellarburgersLogoButton());
    }

	@Step("Проверка, что элемент signInLinkButton отображается на странице Base")
    public void assertSignInLinkButtonIsDisplayed() {
        assertTrue(signInLinkButton.isDisplayed());
    }

	@Step("Проверка, что текст элемента signInLinkButton соответствует параметру")
    public void assertSignInLinkButtonEquals(String expected) {
        assertEquals(expected, getTextSignInLinkButton());
    }

	@Step("Проверка, что элемент stellarburgersLogoButton НЕ отображается на странице Base")
    public void assertStellarburgersLogoButtonIsNotDisplayed() {
        assertFalse(stellarburgersLogoButton.isDisplayed());
    }

	@Step("Клик на кнопку stellarburgersLogoButton на странице Base")
    public BasePage clickStellarburgersLogoButton() {
        stellarburgersLogoButton.click();

        return this;
    }

    @Step("Проверка, что драйвер находится на текущей странице")
    public void assertCurrentPageIsRight() {
        assertTrue(driver.getCurrentUrl().contains(getCurrentUrl()));
    }
}
