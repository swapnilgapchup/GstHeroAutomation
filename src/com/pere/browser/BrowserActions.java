package com.pere.browser;

import com.pere.fileUtil.ReadProperties;
import com.sun.istack.internal.NotNull;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
}