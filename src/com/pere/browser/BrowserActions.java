package com.pere.browser;

import com.pere.fileUtil.ReadProperties;
import com.sun.istack.internal.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Properties;

public class BrowserActions {
    public static void openNewTab(@NotNull WebDriver driver) {
        Properties pro=ReadProperties.returnProperties();
        String a = pro.getProperty("StringToOpenGmail");
        ((JavascriptExecutor) driver).executeScript(a);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void uploadFile(WebDriver driver, String filePath, String uploadButtonPath, String tagOfUploadFile) {


            WebElement uploadElement = driver.findElement(By.id(tagOfUploadFile));

            // enter the file path onto the file-selection input field
            uploadElement.sendKeys(filePath);

            // click the "UploadFile" button
            driver.findElement(By.name("send")).click();

    }

}