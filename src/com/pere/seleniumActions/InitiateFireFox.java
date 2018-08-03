package com.pere.seleniumActions;
/**
 * Created by IntelliJ IDEA.
 * User: Swapnil Gapchup
 * Date: 3/8/18
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Properties;

public class InitiateFireFox implements InitiateDriver {
    /**
     *
     * @param path
     * @return
     */
    @Override
    public WebDriver setupDriver(String path) {
        Properties properties = new Properties();
        System.setProperty("webdriver.firefox.driver", "/home/perennial/Desktop/firfoxDriver");
        WebDriver driver1 = new FirefoxDriver();
        return driver1;
    }

    @Override
    public void closeDriver(WebDriver driver){
        driver.close();
        driver.quit();
        System.out.print("Driver of FF closed");
    }
}