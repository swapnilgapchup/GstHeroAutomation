package com.pere.seleniumActions;
/**
 * Created by IntelliJ IDEA.
 * User: Swapnil Gapchup
 * Date: 3/8/18
 */
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    public static void waitTillPageIsReady(WebDriver driver){
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
