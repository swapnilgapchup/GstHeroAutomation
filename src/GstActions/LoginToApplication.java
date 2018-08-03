package GstActions;
/**
 * Created by IntelliJ IDEA.
 * User: Swapnil Gapchup
 * Date: 3/8/18
 */

import com.pere.fileUtil.ReadProperties;
import com.pere.seleniumActions.EnterTextField;
import com.pere.seleniumActions.InitiateDriver;
import com.pere.seleniumActions.WebDriverWaitCustom;
import com.perennial.selenium.ScreenshotHelper;
import org.openqa.selenium.WebDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public  class LoginToApplication {
    static String loginUrl;
    static String userIdElementpath;
    static String passwordElementPath;
    static String submitButtonpath;
    static String userIdElementText;
    static String passwordElementText;


    public static void getLoginData(){
        Properties pro;
        pro = ReadProperties.returnProperties();
        //login url for Any application
        loginUrl = pro.getProperty("gstBaseUrl");
        //Emailid or userid element path
        userIdElementpath = pro.getProperty("emailField");

        passwordElementPath = pro.getProperty("passwordField");
        submitButtonpath = pro.getProperty("submitButton");
        userIdElementText= pro.getProperty("emailAddress");
        passwordElementText=pro.getProperty("password");

        System.out.println("Email Element "+ pro.getProperty("emailField"));
        System.out.println("Password Element "+pro.getProperty("passwordField"));
        System.out.println("Submit Element "+pro.getProperty("submitButton"));
        System.out.println("Text for Email Address: "+pro.getProperty("emailAddress"));
        System.out.println("Text for password :"+pro.getProperty("password"));

        //driver,,,
    }


    public void login(WebDriver driver){

        getLoginData();
        //To get desired URL loaded
        driver.get(loginUrl);

        System.out.println();
        //maximise window
     //driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //To take screenshot
        //ScreenshotHelper.captureScreenshot(driver, pro.getProperty("imageSavePath"));
        //wait until dom gives email_id
        WebDriverWaitCustom.waitTill(driver,userIdElementpath);
        //set text to the email id field
        EnterTextField.enterTextInField(driver,userIdElementpath,userIdElementText);
        //set password in the password field
        EnterTextField.enterTextInField(driver,passwordElementPath,passwordElementText);
        //Click on login button
        EnterTextField.clickButton(driver,submitButtonpath);
    }

}