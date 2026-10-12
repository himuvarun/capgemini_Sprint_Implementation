package Login;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.LoginPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginFunctionalTest extends BaseClass {

    @Test(groups = "Functional")
    public void verifyUsernameAcceptsValidInput() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-FUN-001", "Username");
        LoginPage login = new LoginPage(driver);
        login.enterUsername(username);

        String actual = driver.findElement(By.id("user-name")).getAttribute("value");
        Assert.assertEquals(actual, username, "Username was not accepted");
        Reporter.log("Username accepted successfully", true);
    }

    @Test(groups = "Functional")
    public void verifyPasswordIsMasked() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String password = excel.getDataByTestId("Login", "LOGIN-FUN-001", "Password");
        LoginPage login = new LoginPage(driver);
        login.enterPassword(password);
        
        Assert.assertTrue(login.passwordMasked(),"Password is not masked");
        Reporter.log("Password masking verified", true);
    }
}