package Login;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.LoginPage;
import objRepo.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginSystemTest extends BaseClass {

    @Test(groups = "System")
    public void verifyLoginToProductsFlow() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        String expectedTitle = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "ExpectedTitle");

        LoginPage login = new LoginPage(driver);
        login.login(username, password);

        ProductPage product = new ProductPage(driver);
        Assert.assertEquals(product.title(),expectedTitle,"Login to Products flow failed");
        Assert.assertTrue(product.count() > 0,"Products are not displayed");
        Reporter.log("Login to Products system flow passed", true);
    }
}