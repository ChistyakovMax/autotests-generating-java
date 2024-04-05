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
public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
        currentUrl = baseUrl + "";
    }

    @FindBy(xpath = ".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span")
    private WebElement currentTab;

	@FindBy(xpath = ".//div/span[text()='Соусы']")
    private WebElement saucesTabButton;

	@FindBy(xpath = ".//div/span[text()='Булки']")
    private WebElement bunsTabButton;

	@FindBy(xpath = ".//div/span[text()='Начинки']")
    private WebElement toppingTabButton;

	@FindBy(xpath = ".//button[text() = 'Оформить заказ']")
    private WebElement getOrderButton;

	@FindBy(xpath = ".//p[text() = 'Личный Кабинет']")
    private WebElement personalAccountButton;

	@FindBy(xpath = ".//button[text()='Войти в аккаунт']")
    private WebElement signInButton;

    @Step("Проверка, что текст элемента saucesTabButton соответствует параметру")
    public void assertSaucesTabButtonEquals(String expected) {
        assertEquals(expected, getTextSaucesTabButton());
    }

	@Step("Проверка, что текст элемента getOrderButton соответствует параметру")
    public void assertGetOrderButtonEquals(String expected) {
        assertEquals(expected, getTextGetOrderButton());
    }

	@Step("Клик на кнопку toppingTabButton на странице Main")
    public MainPage clickToppingTabButton() {
        toppingTabButton.click();

        return this;
    }

	@Step("Проверка, что текст элемента currentTab соответствует параметру")
    public void assertCurrentTabEquals(String expected) {
        assertEquals(expected, getTextCurrentTab());
    }

	@Step("Проверка, что текст элемента bunsTabButton соответствует параметру")
    public void assertBunsTabButtonEquals(String expected) {
        assertEquals(expected, getTextBunsTabButton());
    }

	@Step("Проверка, что текст элемента toppingTabButton соответствует параметру")
    public void assertToppingTabButtonEquals(String expected) {
        assertEquals(expected, getTextToppingTabButton());
    }

	@Step("Взять текст у элемента saucesTabButton на странице Main")
    public String getTextSaucesTabButton() {
        return saucesTabButton.getText();
    }

	@Step("Двойной клик на кнопку bunsTabButton на странице Main")
    public MainPage doubleClickBunsTabButton() {
        bunsTabButton.click();
        bunsTabButton.click();

        return this;
    }

	@Step("Двойной клик на кнопку getOrderButton на странице Main")
    public MainPage doubleClickGetOrderButton() {
        getOrderButton.click();
        getOrderButton.click();

        return this;
    }

	@Step("Проверка, что элемент toppingTabButton отображается на странице Main")
    public void assertToppingTabButtonIsDisplayed() {
        assertTrue(toppingTabButton.isDisplayed());
    }

	@Step("Проверка, что элемент signInButton НЕ отображается на странице Main")
    public void assertSignInButtonIsNotDisplayed() {
        assertFalse(signInButton.isDisplayed());
    }

	@Step("Двойной клик на кнопку toppingTabButton на странице Main")
    public MainPage doubleClickToppingTabButton() {
        toppingTabButton.click();
        toppingTabButton.click();

        return this;
    }

	@Step("Проверка, что элемент bunsTabButton отображается на странице Main")
    public void assertBunsTabButtonIsDisplayed() {
        assertTrue(bunsTabButton.isDisplayed());
    }

	@Step("Проверка, что текст элемента signInButton соответствует параметру")
    public void assertSignInButtonEquals(String expected) {
        assertEquals(expected, getTextSignInButton());
    }

	@Step("Клик на кнопку signInButton на странице Main")
    public MainPage clickSignInButton() {
        signInButton.click();

        return this;
    }

	@Step("Клик на кнопку getOrderButton на странице Main")
    public MainPage clickGetOrderButton() {
        getOrderButton.click();

        return this;
    }

	@Step("Проверка, что элемент currentTab НЕ отображается на странице Main")
    public void assertCurrentTabIsNotDisplayed() {
        assertFalse(currentTab.isDisplayed());
    }

	@Step("Проверка, что элемент saucesTabButton НЕ отображается на странице Main")
    public void assertSaucesTabButtonIsNotDisplayed() {
        assertFalse(saucesTabButton.isDisplayed());
    }

	@Step("Взять текст у элемента signInButton на странице Main")
    public String getTextSignInButton() {
        return signInButton.getText();
    }

	@Step("Взять текст у элемента bunsTabButton на странице Main")
    public String getTextBunsTabButton() {
        return bunsTabButton.getText();
    }

	@Step("Проверка, что текст элемента personalAccountButton соответствует параметру")
    public void assertPersonalAccountButtonEquals(String expected) {
        assertEquals(expected, getTextPersonalAccountButton());
    }

	@Step("Двойной клик на кнопку personalAccountButton на странице Main")
    public MainPage doubleClickPersonalAccountButton() {
        personalAccountButton.click();
        personalAccountButton.click();

        return this;
    }

	@Step("Проверка, что элемент signInButton отображается на странице Main")
    public void assertSignInButtonIsDisplayed() {
        assertTrue(signInButton.isDisplayed());
    }

	@Step("Двойной клик на кнопку signInButton на странице Main")
    public MainPage doubleClickSignInButton() {
        signInButton.click();
        signInButton.click();

        return this;
    }

	@Step("Клик на кнопку saucesTabButton на странице Main")
    public MainPage clickSaucesTabButton() {
        saucesTabButton.click();

        return this;
    }

	@Step("Проверка, что элемент toppingTabButton НЕ отображается на странице Main")
    public void assertToppingTabButtonIsNotDisplayed() {
        assertFalse(toppingTabButton.isDisplayed());
    }

	@Step("Проверка, что элемент currentTab отображается на странице Main")
    public void assertCurrentTabIsDisplayed() {
        assertTrue(currentTab.isDisplayed());
    }

	@Step("Взять текст у элемента toppingTabButton на странице Main")
    public String getTextToppingTabButton() {
        return toppingTabButton.getText();
    }

	@Step("Двойной клик на кнопку saucesTabButton на странице Main")
    public MainPage doubleClickSaucesTabButton() {
        saucesTabButton.click();
        saucesTabButton.click();

        return this;
    }

	@Step("Взять текст у элемента getOrderButton на странице Main")
    public String getTextGetOrderButton() {
        return getOrderButton.getText();
    }

	@Step("Взять текст у элемента currentTab на странице Main")
    public String getTextCurrentTab() {
        return currentTab.getText();
    }

	@Step("Проверка, что элемент getOrderButton НЕ отображается на странице Main")
    public void assertGetOrderButtonIsNotDisplayed() {
        assertFalse(getOrderButton.isDisplayed());
    }

	@Step("Взять текст у элемента personalAccountButton на странице Main")
    public String getTextPersonalAccountButton() {
        return personalAccountButton.getText();
    }

	@Step("Проверка, что элемент getOrderButton отображается на странице Main")
    public void assertGetOrderButtonIsDisplayed() {
        assertTrue(getOrderButton.isDisplayed());
    }

	@Step("Клик на кнопку bunsTabButton на странице Main")
    public MainPage clickBunsTabButton() {
        bunsTabButton.click();

        return this;
    }

	@Step("Проверка, что элемент personalAccountButton отображается на странице Main")
    public void assertPersonalAccountButtonIsDisplayed() {
        assertTrue(personalAccountButton.isDisplayed());
    }

	@Step("Клик на кнопку personalAccountButton на странице Main")
    public MainPage clickPersonalAccountButton() {
        personalAccountButton.click();

        return this;
    }

	@Step("Проверка, что элемент personalAccountButton НЕ отображается на странице Main")
    public void assertPersonalAccountButtonIsNotDisplayed() {
        assertFalse(personalAccountButton.isDisplayed());
    }

	@Step("Проверка, что элемент saucesTabButton отображается на странице Main")
    public void assertSaucesTabButtonIsDisplayed() {
        assertTrue(saucesTabButton.isDisplayed());
    }

	@Step("Проверка, что элемент bunsTabButton НЕ отображается на странице Main")
    public void assertBunsTabButtonIsNotDisplayed() {
        assertFalse(bunsTabButton.isDisplayed());
    }
}