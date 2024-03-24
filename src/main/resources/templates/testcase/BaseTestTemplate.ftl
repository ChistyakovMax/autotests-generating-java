package ru.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//общий класс для тестов
public abstract class BaseTest {

    WebDriver driver;

    @Before
    public void setup() {

        //тест на Google Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
