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
public class PasswordRecoveryPage extends BasePage {

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
        currentUrl = baseUrl + "forgot-password";
    }

    @FindBy(xpath = ".//button[text()='Восстановить']")
    private WebElement restorePasswordButton;

    @Step("Взять текст у элемента restorePasswordButton на странице PasswordRecovery")
    public String getTextRestorePasswordButton() {
        return restorePasswordButton.getText();
    }

	@Step("Проверка, что текст элемента restorePasswordButton соответствует параметру")
    public void assertRestorePasswordButtonEquals(String expected) {
        assertEquals(expected, getTextRestorePasswordButton());
    }

	@Step("Двойной клик на кнопку restorePasswordButton на странице PasswordRecovery")
    public PasswordRecoveryPage doubleClickRestorePasswordButton() {
        restorePasswordButton.click();
        restorePasswordButton.click();

        return this;
    }

	@Step("Проверка, что элемент restorePasswordButton отображается на странице PasswordRecovery")
    public void assertRestorePasswordButtonIsDisplayed() {
        assertTrue(restorePasswordButton.isDisplayed());
    }

	@Step("Клик на кнопку restorePasswordButton на странице PasswordRecovery")
    public PasswordRecoveryPage clickRestorePasswordButton() {
        restorePasswordButton.click();

        return this;
    }

	@Step("Проверка, что элемент restorePasswordButton НЕ отображается на странице PasswordRecovery")
    public void assertRestorePasswordButtonIsNotDisplayed() {
        assertFalse(restorePasswordButton.isDisplayed());
    }
}