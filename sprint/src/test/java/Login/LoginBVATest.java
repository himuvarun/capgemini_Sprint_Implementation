package Login;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.LoginPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginBVATest extends BaseClass {

    @Test(groups = "BVA")
    public void verifyMinimumUsernameInputBehavior() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-BVA-001", "Username");
        String expectedInput = excel.getDataByTestId("Login", "LOGIN-BVA-001", "ExpectedInput");
       
        LoginPage page = new LoginPage(driver);
        page.enterUsername(username);
        Assert.assertEquals(driver.findElement(org.openqa.selenium.By.id("user-name")).getAttribute("value"),expectedInput,"Username boundary input was not accepted");
        Reporter.log("Minimum username input boundary verified", true);
    }
}
