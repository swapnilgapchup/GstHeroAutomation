package com.pere.seleniumActions;
/**
 * Created by IntelliJ IDEA.
 * User: Swapnil Gapchup
 * Date: 3/8/18
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Properties;

/**
 * We can use this interface method to get Drivers of type Chrome or Firefox
 */
public interface InitiateDriver{
   WebDriver setupDriver(String path);
   void closeDriver(WebDriver driver);
}
