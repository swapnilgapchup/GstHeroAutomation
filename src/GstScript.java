/**
 * Created by IntelliJ IDEA.
 * User: Swapnil Gapchup
 * Date: 3/8/18
 */
import GstActions.GstFunctions;
import GstActions.LoginToApplication;
import com.pere.seleniumActions.EnterTextField;
import com.pere.seleniumActions.InitiateChrome;
import com.pere.seleniumActions.InitiateDriver;
import com.pere.fileUtil.ReadProperties;
import com.pere.seleniumActions.WebDriverWaitCustom;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Properties;

import static GstActions.GstFunctions.gstR1;
import static com.pere.browser.BrowserActions.openNewTab;

public class GstScript {
    /**
     * This is the main method written for the Execution of testcases of Application GST Hero
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Properties pro;
        pro = ReadProperties.returnProperties();
        try {
            //initiate WebDriver
            InitiateDriver ic = new InitiateChrome();
            WebDriver driver;
            //set local chrom driver path
            driver = ic.setupDriver(pro.getProperty("path"));
            // driver.manage().window().maximize();

            LoginToApplication la = new LoginToApplication();
            la.login(driver);
            //Thread.sleep(3000);
            //File GSTR1
            gstR1(driver);
            //open new tab to start process of Email verification
            //openNewTab(driver);
            //System.out.println(driver.getTitle());
            WebDriverWait wait = new WebDriverWait(driver, 30);

            Thread.sleep(3000);
            //Close and quit WebDriver
            ic.closeDriver(driver);
        } catch (NotFoundException r) {
            System.out.println(r);
        } catch (org.openqa.selenium.WebDriverException e) {
            System.out.println(e);
        }
    }
}
