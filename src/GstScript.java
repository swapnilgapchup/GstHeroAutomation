import GstActions.GstFunctions;
import com.pere.seleniumActions.InitiateChrome;
import com.pere.fileUtil.ReadProperties;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Properties;
import static com.pere.browser.BrowserActions.openNewTab;

public class GstScript {
    public static void main(String[] args) {
        Properties pro;
        pro = ReadProperties.returnProperties();
        try {
           //initiate WebDriver
           InitiateChrome ic = new InitiateChrome();
            WebDriver driver;
           //set local chrom driver path
            driver = ic.setupDriver(pro.getProperty("path"));
            // login to the GST portal
            GstFunctions.login(driver,pro.getProperty("emailAddress"),pro.getProperty("password"),pro.getProperty("gstBaseUrl")
                    ,pro.getProperty("emailField"),pro.getProperty("passwordField"),pro.getProperty("submitButton"));
            //open new tab to start process of Email verification
            openNewTab(driver);
            //System.out.println(driver.getTitle());
            WebDriverWait wait = new WebDriverWait(driver,30);
            // Close and quit WebDriver
            ic.closeChromeDriver(driver);
       }catch(NotFoundException r){
            System.out.println("base url not accessible");
       }
    }
}
