package Login;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.LoginPage;
import objRepo.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginIntegrationTest extends BaseClass {

    @Test(groups = "Integration")
    public void verifyLoginIntegratesWithProduct() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-INT-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-INT-001", "Password");
        String expectedTitle = excel.getDataByTestId("Login", "LOGIN-INT-001", "ExpectedTitle");

        new LoginPage(driver).login(username, password);
        Assert.assertEquals(new ProductPage(driver).title(),expectedTitle,"Login did not reach Products");
        Reporter.log("Login to Product integration verified", true);
    }
}
