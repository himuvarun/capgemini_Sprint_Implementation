package baseClass;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import genericUtility.FileUtility;
import genericUtility.ScreenshotUtility;

public class BaseClass {

    protected WebDriver driver;

    protected FileUtility fileUtility = new FileUtility();
    protected ScreenshotUtility screenshotUtility = new ScreenshotUtility();

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        Reporter.log("Swag Labs test suite started", true);
    }

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void launchApplication(@Optional("chrome") String browser) throws Exception {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(fileUtility.getDataFromProperties("swaglabs.url"));
        Reporter.log("Browser launched: " + browser, true);
    }

    @AfterMethod(alwaysRun = true)    
    public void closeBrowser(ITestResult result) {
        if (result.getMethod().getGroups() != null) {
            for (String group : result.getMethod().getGroups()) {
                if (group.equalsIgnoreCase("Negative")) {
                    if (driver != null) {
                        screenshotUtility.capture(driver,result.getMethod().getMethodName());
                    }
                    break;
                }
            }
        }
            if (driver != null) {
                driver.quit();
                driver = null;
            }
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        Reporter.log("Swag Labs test suite completed", true);
    }
}