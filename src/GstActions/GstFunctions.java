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
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.pere.seleniumActions.WebDriverWaitCustom.waitTillPageIsReady;

public class GstFunctions {

    public static void threadWait(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     *  Expected data for mapping GSTR1 step
     */


    public static void login(WebDriver driver, String userName, String password, String baseUrl, String userIdElement, String userPasswordElement, String submitButtonPath) {
        Properties pro;
        pro = ReadProperties.returnProperties();

        //To get desired URL loaded
        driver.get(baseUrl);

        //maximise window
        driver.manage().window().maximize();
        /*driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);*/
        driver.manage().deleteAllCookies();

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
        //Wait till redirected page gets completely loaded
        waitTillPageIsReady(driver);
        /*new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));*/
    }

    public static void gstR1(WebDriver driver) throws AWTException, InterruptedException {
        Properties pro;
        pro = ReadProperties.returnProperties();
        // Select date controller and click on itMonthSlection
        WebDriverWaitCustom.waitTill(driver,pro.getProperty("monthSlection"));
        //Select month dropdown
        EnterTextField.clickButton(driver,pro.getProperty("monthSlection"));
        // Select first month from array and set it
        EnterTextField.clickButton(driver, pro.getProperty("monthdiv"));
        threadWait();
        //Click on the map file button
        EnterTextField.clickButton(driver,pro.getProperty("mapExcelButton"));
        //Select excel type
        WebDriverWaitCustom.waitTill(driver, pro.getProperty("fileTypeSelectionDropdown"));
        Select dropdown = new Select(driver.findElement(By.xpath(pro.getProperty("fileTypeSelectionDropdown"))));
        dropdown.selectByIndex(1);
        System.out.println("Dropdown value Selected");
        //Click to process mapping
        EnterTextField.clickButton(driver,pro.getProperty("processAndNext"));
        //wait till next page lodes
        threadWait();
       // System.out.println(driver.findElement(By.xpath("(//table/tbody/tr[1]/td[1])[1]")));
        compareMapping(driver);

        //uploadButtonPath =//*[@id="gstr1SaveButton"]
        System.out.println("gstr1SaveButton need found");
                 EnterTextField.clickButton(driver,pro.getProperty("uploadButtonPath"));
                 threadWait();
                 System.out.println("gstr1SaveButton found");
                 //click ok on the modal of the GSTR1 for process confirmation
        ((JavascriptExecutor)driver).executeScript("var modalOpen = document.getElementsByClassName('btn-ok-black');modalOpen[0].click();");


        waitTillPageIsReady(driver);
        threadWait();
        System.out.println(pro.getProperty("filePath")+"This is it");
       // WebDriverWaitCustom.waitTill(driver,"//*[@id=\"invoiceExcel\"]");
        WebElement element = driver.findElement(By.xpath("(//td/div/a/span)[1]"));
                element.sendKeys(pro.getProperty("filePath"));

        //tagOfUploadFile = //*[@id="invoiceExcel"]

        /*WebElement element = driver.findElement(By.xpath("(//td/div/a/span)[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.sendKeys(pro.getProperty("filePath"));
        System.out.println("tagOfUploadFile found");
        actions.build().perform();*//*
        StringSelection s = new StringSelection("filePath");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);*/
        /*try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_SLASH);
            robot.keyPress(KeyEvent.getExtendedKeyCodeForChar('z'));
            robot.keyPress(KeyEvent.getExtendedKeyCodeForChar('p'));
            robot.keyPress(KeyEvent.getExtendedKeyCodeForChar('.'));
            robot.keyPress(KeyEvent.getExtendedKeyCodeForChar('x'));
            robot.keyPress(KeyEvent.getExtendedKeyCodeForChar('l'));
            robot.keyPress(KeyEvent.getExtendedKeyCodeForChar('s'));
            robot.keyPress(KeyEvent.getExtendedKeyCodeForChar('x'));
            robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
        }catch(AWTException e){
            System.out.println(e);
        }*/
        waitTillPageIsReady(driver);
        driver.findElement(By.xpath("//*[@id=\"processAndNextBtn\"]")).click();
        waitTillPageIsReady(driver);
        /*element.sendKeys(pro.getProperty("filePath"));

        System.out.println("tagOfUploadFile found");
        threadWait();

        try{
            WebDriverWaitCustom.waitTill(driver,pro.getProperty("processOffline"));
            if(EnterTextField.getTextValue(driver,pro.getProperty("processOffline"))=="Process Offline"){
                System.out.println("Step 2 Completed Draft generated on step 3");
            }
        }catch(ElementNotVisibleException e){
            System.out.println(e);*/
  if(assertValues(driver,"//*[@id=\"b2b4AtaxValue\"]","9,69,049.11")){
       System.out.println("Business to Business tax value is compared for given text file which is correct");
        }
        // to check status of GSTR1 uploaded
      /* if(assertValues(driver,pro.getProperty("gstr1StatusElement"),pro.getProperty("gstr1DraftGeneratedStatus"))){
        System.out.println("Draft Generated is the status ");
       }else{
           System.out.println("Draft Generation fail");
       }
       WebDriverWaitCustom.waitTill(driver,pro.getProperty("uploadButtonPath1"));
       EnterTextField.clickButton(driver,pro.getProperty("uploadButtonPath1"));*/
        //processButton=//*[id="invoiceExcel"]
    }


   public static void compareMapping(WebDriver driver) {
        Properties pro;
       pro = ReadProperties.returnProperties();
       // Read expected text for mapping
       String row1cloumn1value=pro.getProperty("row1column1value");
       String row1cloumn2value=pro.getProperty("row1column2value");
       String row2cloumn1value=pro.getProperty("row2column1value");
       String row2cloumn2value=pro.getProperty("row2column2value");
        //Assert expected and actual text at given place
       if(assertValues(driver,pro.getProperty("row1column1"),row1cloumn1value )&&
               assertValues(driver,pro.getProperty("row1column2"),row1cloumn2value)&&
               assertValues(driver,pro.getProperty("row2column1"),row2cloumn1value)&&
               assertValues(driver,pro.getProperty("row2column2"),row2cloumn2value)){
           System.out.println("Value Mapping Correct");
       }else{
           System.out.println("Value Mapping Mismatch");
       }
       EnterTextField.clickButton(driver,pro.getProperty("processButtonPath"));

       threadWait();
       if(assertValues(driver, pro.getProperty("mappingStatusPath"), pro.getProperty("expectedStatus"))) {
           System.out.println("Mapping Status is Completed");
       }else{
           System.out.println("Mapping Status is Incompleted");
       }

   }
    public static boolean assertValues(WebDriver driver, String elementPath, String expectedText) {
        WebElement TxtBoxContent = driver.findElement(By.xpath(elementPath));
        String textValueAt = TxtBoxContent.getText();
        System.out.println("value at '" + elementPath + "' is " +"'"+textValueAt+"'");
        if(textValueAt.contains(expectedText)){
            System.out.println("Success");
        }else{
            System.out.println("mismatch at" +elementPath+" Actual value is "+textValueAt+" Expected Value is"+expectedText);
        }
            return (textValueAt.contains(expectedText));

    }
}
