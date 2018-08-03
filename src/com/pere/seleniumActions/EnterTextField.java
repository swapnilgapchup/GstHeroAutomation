package com.pere.seleniumActions;
/**
 * Created by IntelliJ IDEA.
 * User: Swapnil Gapchup
 * Date: 3/8/18
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*Enter value in the field as text.The element can be found by xpath
Element is cleared first for any data if present then the expected value is entered
@param driver, elementPath, elementTextToEnter
*/
public class EnterTextField {
    public static void enterTextInField(WebDriver driver, String elementPath, String elementTextToEnter){
        WebElement el =driver.findElement(By.xpath(elementPath));
        el.clear();
        el.sendKeys(elementTextToEnter);
    }

        /*This function is used to click the expected button element using WebDriver instance drievr and path of the button element.
        @param driver, elementPath
        */
    public static void clickButton(WebDriver driver,String elementPath){

        // System.out.println(elementPath);
        // System.out.println(elementTextToEnter);
        WebElement el =driver.findElement(By.xpath(elementPath));
        el.click();
    }
            /*This function is used to get Value at element using, WebDriver instance drievr and path of the element.
            @param driver, elementPath
            */
            public static String getTextValue(WebDriver driver,String elementPath){

                // System.out.println(elementPath);
                // System.out.println(elementTextToEnter);
                WebElement el =driver.findElement(By.xpath(elementPath));
                return el.getText();
            }
}
