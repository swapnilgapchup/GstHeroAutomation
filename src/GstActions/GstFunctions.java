package GstActions;
/**
 * Created by IntelliJ IDEA.
 * User: Swapnil Gapchup
 * Date: 3/8/18
 */
import com.pere.seleniumActions.EnterTextField;
import com.pere.seleniumActions.WebDriverWaitCustom;
import com.pere.fileUtil.ReadProperties;
import com.perennial.selenium.ScreenshotHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.Properties;
import static com.pere.seleniumActions.WebDriverWaitCustom.waitTillPageIsReady;

public class GstFunctions {

    public static void threadWait() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Expected data for mapping GSTR1 step
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
        WebDriverWaitCustom.waitTill(driver, pro.getProperty("monthSlection"));
        //Select month dropdown
        EnterTextField.clickButton(driver, pro.getProperty("monthSlection"));
        // Select first month from array and set it
        EnterTextField.clickButton(driver, pro.getProperty("monthdiv"));
        threadWait();
        //Click on the map file button
        EnterTextField.clickButton(driver, pro.getProperty("mapExcelButton"));
        //Select excel type
        WebDriverWaitCustom.waitTill(driver, pro.getProperty("fileTypeSelectionDropdown"));
        Select dropdown = new Select(driver.findElement(By.xpath(pro.getProperty("fileTypeSelectionDropdown"))));
        dropdown.selectByIndex(1);
        System.out.println("Dropdown value Selected");
        //Click to process mapping
        EnterTextField.clickButton(driver, pro.getProperty("processAndNext"));
        //wait till next page lodes
        threadWait();
        // System.out.println(driver.findElement(By.xpath("(//table/tbody/tr[1]/td[1])[1]")));
        compareMapping(driver);

        //uploadButtonPath =//*[@id="gstr1SaveButton"]
        System.out.println("gstr1SaveButton need found");
        EnterTextField.clickButton(driver, pro.getProperty("uploadButtonPath"));
        threadWait();
        System.out.println("gstr1SaveButton found");
        //click ok on the modal of the GSTR1 for process confirmation
        ((JavascriptExecutor) driver).executeScript("var modalOpen = document.getElementsByClassName('btn-ok-black');modalOpen[0].click();");

        // wait till the page is gettign loaded
        waitTillPageIsReady(driver);
        threadWait();
        //System.out.println(pro.getProperty("filePath")+"This is it");
        // get input element to send file
        WebElement element[] = driver.findElements(new By.ByTagName("input")).toArray(new WebElement[0]);
        element[3].sendKeys(pro.getProperty("filePath"));
        waitTillPageIsReady(driver);
        // Click on next
        driver.findElement(By.xpath("//*[@id=\"processAndNextBtn\"]")).click();
        waitTillPageIsReady(driver);
        //assert value for the Expected GSTR1 output
        /*if (assertValues(driver, "//*[@id=\"b2b4AtaxValue\"]", "9,69,049.11")) {
            System.out.println("Business to Business tax value is compared for given text file which is correct");
        }*/
        gstR1CompareValues(driver,  "b2B_Total_count");

        /*driver.findElement(By.cssSelector(".gstr1TxnDiscard")).click();
        driver.findElement(By.xpath("(//img)[1]")).click();
        driver.findElement(By.cssSelector(".workonthis-btn")).click();

        ((JavascriptExecutor)driver).executeScript("var modalOpen = document.getElementsByClassName('btn-black-submition');modalOpen[0].click();");
        waitTillPageIsReady(driver);
        ((JavascriptExecutor)driver).executeScript("var modalOpen = document.getElementsByClassName('btn-black-submition');modalOpen[0].click();");*/

    }


    public static void compareMapping(WebDriver driver) {
        Properties pro;
        pro = ReadProperties.returnProperties();
        // Read expected text for mapping
        String row1cloumn1value = pro.getProperty("row1column1value");
        String row1cloumn2value = pro.getProperty("row1column2value");
        String row2cloumn1value = pro.getProperty("row2column1value");
        String row2cloumn2value = pro.getProperty("row2column2value");
        //Assert expected and actual text at given place
        if (assertValues(driver, pro.getProperty("row1column1"), row1cloumn1value) &&
                assertValues(driver, pro.getProperty("row1column2"), row1cloumn2value) &&
                assertValues(driver, pro.getProperty("row2column1"), row2cloumn1value) &&
                assertValues(driver, pro.getProperty("row2column2"), row2cloumn2value)) {
            System.out.println("Value Mapping Correct");
        } else {
            System.out.println("Value Mapping Mismatch");
        }
        EnterTextField.clickButton(driver, pro.getProperty("processButtonPath"));

        threadWait();
        if (assertValues(driver, pro.getProperty("mappingStatusPath"), pro.getProperty("expectedStatus"))) {
            System.out.println("Mapping Status is Completed");
        } else {
            System.out.println("Mapping Status is Incompleted");
        }

    }

    public static boolean assertValues(WebDriver driver, String elementPath, String expectedText) {
        WebElement TxtBoxContent = driver.findElement(By.xpath(elementPath));
        String textValueAt = TxtBoxContent.getText();
      /*  System.out.println("value at '" + elementPath + "' is " + "'" + textValueAt + "'");*/
        /*if (textValueAt.contains(expectedText)) {
            System.out.println("Success");
        } else {
            System.out.println("mismatch at" + elementPath + " Actual value is " + textValueAt + " Expected Value is" + expectedText);
        }*/
        return (textValueAt.contains(expectedText));

    }

    public static void gstR1CompareValues(WebDriver driver, String gstR1ValuesElements) {
        Properties pro;
        pro = ReadProperties.returnProperties();

        //String gstR1ValuesElements[] = {"b2B_Total_Tax","b2B_Tax_Amount","b2B_Integrated_TaxValue", "b2B_Central_Tax","b2B_State_Tax", "b2B_Cess"};
        String gstR1ValuesExpected[] = {
                pro.getProperty("b2B_Total_Count_Expected"),
                pro.getProperty("b2B_Total_Tax_Value_Expected"),
                pro.getProperty("b2B_Tax_Amount_Value_Expected"),
                pro.getProperty("b2B_Integrated_TaxValue_Value_Expected"),
                pro.getProperty("b2B_Central_Tax_Value_Expected"),
                pro.getProperty("b2B_State_Tax_Expected"),
                pro.getProperty("b2B_Cess_Expected")};
        switch (gstR1ValuesElements) {
            case "b2B_Total_count":
                System.out.print("b2B_Total_count value is :- ");
                if (assertValues(driver, pro.getProperty("b2B_Total_count"), gstR1ValuesExpected[0])) {

                    System.out.println("correct");
                } else {
                    System.out.println("incorrect");
                }
            case "b2B_Total_Tax":
                System.out.print("b2B_Total_Taxable value is :- ");
                if (assertValues(driver, pro.getProperty("b2B_Total_Tax"), gstR1ValuesExpected[1])) {

                    System.out.println("correct");
                } else {
                    System.out.println("incorrect");
                }
            case "b2B_Tax_Amount":
                System.out.print("b2B_Tax_Amount is :- ");
                if (assertValues(driver, pro.getProperty("b2B_Tax_Amount"), gstR1ValuesExpected[2])) {

                    System.out.println("correct");
                } else {
                    System.out.println("incorrect");
                }
            case "b2B_Integrated_TaxValue":
                System.out.print("b2B_Integrated_TaxValue is :- ");
                if (assertValues(driver, pro.getProperty("b2B_Integrated_TaxValue"), gstR1ValuesExpected[3])) {

                    System.out.println("correct");
                } else {
                    System.out.println("incorrect");
                }
            case "b2B_Central_Tax":
                System.out.print("b2B_Central_Tax is :- ");
                if (assertValues(driver, pro.getProperty("b2B_Central_Tax"), gstR1ValuesExpected[4])) {

                    System.out.println("correct");
                } else {
                    System.out.println("incorrect");
                }
            case "b2B_State_Tax":
                System.out.print("b2B_State_Tax is :- ");
                if (assertValues(driver, pro.getProperty("b2B_State_Tax"), gstR1ValuesExpected[5])) {

                    System.out.println("correct");
                } else {
                    System.out.println("incorrect");
                }
            case "b2B_Cess":
                System.out.print("b2B_Cess is :- ");
                if (assertValues(driver, pro.getProperty("b2B_Cess"), gstR1ValuesExpected[6])) {

                    System.out.println("correct");
                } else {
                    System.out.println("incorrect");
                }
                break;
        }
    }
}
