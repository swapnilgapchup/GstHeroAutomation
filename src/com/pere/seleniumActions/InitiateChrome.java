package com.pere.seleniumActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Properties;

public class InitiateChrome {

   public WebDriver setupDriver(String path){
       Properties properties  = new Properties();
       System.setProperty("webdriver.chrome.driver", path);
       WebDriver driver1 = new ChromeDriver();
       return driver1;
   }
   public void closeChromeDriver(WebDriver driver){
       driver.close();
       driver.quit();
   }

}
