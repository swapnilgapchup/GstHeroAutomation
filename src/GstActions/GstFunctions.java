package GstActions;
/**
 * Created by IntelliJ IDEA.
 * User: Swapnil Gapchup
 * Date: 3/8/18
 */
import com.pere.seleniumActions.EnterTextField;
import com.pere.seleniumActions.InitiateChrome;
import com.pere.seleniumActions.WebDriverWaitCustom;
import com.pere.fileUtil.ReadProperties;
import com.perennial.selenium.ScreenshotHelper;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class GstFunctions {

    public static void login(WebDriver driver, String userName, String password, String baseUrl, String userIdElement, String userPasswordElement, String submitButtonPath) {
        Properties pro;
        pro = ReadProperties.returnProperties();
        //InitiateChrome ic = new InitiateChrome();

        //To get desired URL loaded
        driver.get(baseUrl);

        //maximise window
        driver.manage().window().maximize();
        /*driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);*/

        //To take screenshot
        ScreenshotHelper.captureScreenshot(driver, pro.getProperty("imageSavePath"));
        //wait until dom gives email_id
        WebDriverWaitCustom.waitTill(driver, userIdElement);
        //set text to the email id field
        EnterTextField.enterTextInField(driver, userIdElement, userName);
        //set password in the password field
        EnterTextField.enterTextInField(driver, userPasswordElement, password);
        //Click on login button
        EnterTextField.clickButton(driver, submitButtonPath);
    }

    public static void gstR1(WebDriver driver) {
        // Select date controller and click on it
        WebDriverWaitCustom.waitTill(driver, "//*[@id=\"clientDashReturnPeriod\"]");
        EnterTextField.clickButton(driver, "//*[@id=\"clientDashReturnPeriod\"]");
        // Select first month from array and select it
        EnterTextField.clickButton(driver, "//td/span[@class='month'][1]");
        //Click on the map file button/tr[1]/td[6]/a
        //WebElement element = driver.findElement(By.cssSelector("#example1 .text-center .gstr1MappingUpld"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = driver.findElement(By.xpath("//tr[1]/td[6]/a"));
        element.click();
        WebDriverWaitCustom.waitTill(driver, "//*[@id=\"templateList\"]");
        Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"templateList\"]")));
        dropdown.selectByIndex(1);
        System.out.println("Dropdown value Selected");
        EnterTextField.clickButton(driver,"//button[2]");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(driver.findElement(By.xpath("(//table/tbody/tr[1]/td[1])[1]")));
        compareMapping(driver);

    }

    public static int compareMapping(WebDriver driver) {
        if(assertValues(driver,"(//table/tbody/tr[1]/td[1])[1]","Document / Voucher / Invoice Type*")&&
           assertValues(driver,"(//table/tbody/tr[1]/td[2])[1]","Document / Voucher / Invoice Type: Regular, Exports, Exports LUT/Bond, Deemed Exports, SEZ Exports, SEZ LUT/Bond, Nil Rated, Non GST, Exempt")&&
           assertValues(driver,"(//table/tbody/tr[2]/td[1])[1]","Supplier GSTIN / UID*")&&
           assertValues(driver,"(//table/tbody/tr[2]/td[2])[1]","Supplier GSTIN / UIN")){
            System.out.println("Value Mapping Correct");
        }else{
            System.out.println("Value mismatch");
        }
        return 1;
    }

    public static boolean assertValues(WebDriver driver, String elementPath, String expectedText) {
        WebElement TxtBoxContent = driver.findElement(By.xpath(elementPath));
        String textValueAt = TxtBoxContent.getText();
        System.out.println("value at '" + elementPath + "' is " +"'"+textValueAt+"'");
            return (textValueAt.contains(expectedText));

    }
}
