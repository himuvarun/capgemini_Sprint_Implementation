package Product;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.CartPage;
import objRepo.LoginPage;
import objRepo.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ProductIntegrationTest extends BaseClass {

    @Test(groups = "Integration")
    public void verifyProductIntegratesWithCart() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        String productName = excel.getDataByTestId("Product", "PRODUCT-INT-001", "ProductName");

        new LoginPage(driver).login(username, password);
        ProductPage page = new ProductPage(driver);
        page.addProduct(productName);
        page.openCart();
        Assert.assertEquals(new CartPage(driver).count(),1,"Product was not transferred to cart");
        Reporter.log("Product to Cart integration verified", true);
    }
}
