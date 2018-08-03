package com.pere.seleniumActions;
/**
 * Created by IntelliJ IDEA.
 * User: Swapnil Gapchup
 * Date: 3/8/18
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class InitiateChrome implements InitiateDriver{
    /**
     * This function is to return WebDriver of Chrome type
     * @param path
     * @return WebDriver driver instance
     */
    @Override
        public WebDriver setupDriver(String path) {
            Properties properties = new Properties();
            System.setProperty("webdriver.chrome.driver", path);
            WebDriver driver1 = new ChromeDriver();
            driver1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return driver1;
        }

    /**
     * This function is to close and quit the passed driver instance of WebDriver
     * @param driver
     */
        @Override
        public void closeDriver(WebDriver driver){
            driver.close();
            driver.quit();
        }

    }

