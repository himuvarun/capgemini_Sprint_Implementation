package Product;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.LoginPage;
import objRepo.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ProductSmokeTest extends BaseClass {

    @Test(groups = "Smoke")
    public void verifyProductsDisplayed() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");

        new LoginPage(driver).login(username, password);
        Assert.assertTrue(new ProductPage(driver).count() > 0,"Products are not displayed");
        Reporter.log("Products are displayed successfully", true);
    }
}
