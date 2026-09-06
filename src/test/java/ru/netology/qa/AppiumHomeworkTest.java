package ru.netology.qa;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppiumHomeworkTest {
    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Pixel 7");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:app", "C:\\Users\\Anton\\Desktop\\Netology_HW\\uiautomator_hw\\app\\build\\outputs\\apk\\debug\\app-debug.apk");

        URL remoteUrl = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void testEmptyInputDoesNotChangeText() {
        MobileElement userInput = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        userInput.clear();
        userInput.sendKeys("   ");

        MobileElement buttonChange = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        buttonChange.click();

        MobileElement textToBeChanged = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        assertEquals("Hello UiAutomator!", textToBeChanged.getText());
    }

    @Test
    public void testOpenTextInAnotherActivity() throws InterruptedException {
        MobileElement userInput = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        userInput.sendKeys("Netology Appium Test");

        MobileElement buttonActivity = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        buttonActivity.click();

        Thread.sleep(2000);

        MobileElement newActivityText = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/text");
        assertEquals("Netology Appium Test", newActivityText.getText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}