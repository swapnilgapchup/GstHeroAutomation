package com.pere.seleniumActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWaitCustom {
    // send driver instance of WebDriver and Element xpath
    public static void waitTill(WebDriver driver, String ExpectedElementName){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ExpectedElementName)
                ));
    }
}
