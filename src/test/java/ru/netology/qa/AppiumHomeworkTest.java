package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.MainScreen;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppiumHomeworkTest {
    private AndroidDriver driver;
    private MainScreen mainScreen;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Pixel 7");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");

        URL remoteUrl = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

        mainScreen = new MainScreen(driver);
    }

    @Test
    public void testEmptyInputDoesNotChangeText() {
        mainScreen.userInput.clear();
        mainScreen.userInput.sendKeys("   ");
        mainScreen.buttonChange.click();

        assertEquals("Hello UiAutomator!", mainScreen.textToBeChanged.getText());
    }

    @Test
    public void testOpenTextInAnotherActivity() throws InterruptedException {
        mainScreen.userInput.sendKeys("Netology Appium Test");
        mainScreen.buttonActivity.click();

        Thread.sleep(2000);

        assertEquals("Netology Appium Test", mainScreen.newActivityText.getText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}