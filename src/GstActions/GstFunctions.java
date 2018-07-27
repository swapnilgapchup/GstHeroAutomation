package GstActions;
import com.pere.seleniumActions.EnterTextField;
import com.pere.seleniumActions.InitiateChrome;
import com.pere.seleniumActions.WebDriverWaitCustom;
import com.pere.fileUtil.ReadProperties;
import com.perennial.selenium.ScreenshotHelper;
import org.openqa.selenium.WebDriver;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class GstFunctions {

    public static void login(WebDriver driver, String userName, String password, String baseUrl, String userIdElement, String userPasswordElement, String submitButtonPath){
        Properties pro;
        pro = ReadProperties.returnProperties();
        InitiateChrome ic = new InitiateChrome();

        //To get desired URL loaded
        driver.get(baseUrl);

        //maximise window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //To take screenshot
        ScreenshotHelper.captureScreenshot(driver, pro.getProperty("imageSavePath"));
        //wait until dom gives email_id
        WebDriverWaitCustom.waitTill(driver, userIdElement);
        //set text to the email id field
        EnterTextField.enterTextInField(driver, userIdElement, userName);
        //set password in the password field
        EnterTextField.enterTextInField(driver,userPasswordElement,password);
        //Click on login button
        EnterTextField.clickButton(driver,submitButtonPath);
    }
    public static void gstR1(WebDriver driver){
        // Select date controller and click on it
        EnterTextField.clickButton(driver,"//input[@id='clientDashReturnPeriod']");
        // Select first month from array and select it
        EnterTextField.clickButton(driver,"//td/span[@class='month'][1]");
        // Select action for  mapping fields on dashboard of GSTR 1
        EnterTextField.clickButton(driver,"//tr[1]/td[6]/a");//MAPPING INITIATION BUTTON
    }

}
