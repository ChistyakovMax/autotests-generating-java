package ru.itmo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

//общий класс для тестов
public abstract class BaseTest {

    //WebDriver driver;
    RemoteWebDriver driver;
    private static String remoteUrlChrome = "http://localhost:4445/wd/hub";

    @Before
    public void setup() throws MalformedURLException {

        //тест на Google Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver(options);
        driver = new RemoteWebDriver(new URL(remoteUrlChrome), options);

        driver.get("${baseUrl}");
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
