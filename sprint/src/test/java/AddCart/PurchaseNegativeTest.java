package AddCart;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.CartPage;
import objRepo.CheckoutPage;
import objRepo.LoginPage;
import objRepo.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class PurchaseNegativeTest extends BaseClass {

    @Test(groups = "Negative")
    public void verifyCheckoutRequiresMandatoryData() throws Exception {

        ExcelUtility excel =new ExcelUtility();
        String username =excel.getDataByTestId("Login","LOGIN-SMOKE-001","Username");
        String password =excel.getDataByTestId("Login","LOGIN-SMOKE-001","Password");
        String productName =excel.getDataByTestId("Product","PRODUCT-FUN-001","ProductName");
        String expectedError =excel.getDataByTestId("Checkout","CHECKOUT-NEG-001","ExpectedErrorPart");
        new LoginPage(driver).login(username,password);
        ProductPage product =new ProductPage(driver);
        product.addProduct(productName);
        product.openCart();

        new CartPage(driver).checkout();
        CheckoutPage checkout =new CheckoutPage(driver);
        checkout.continuePage();
        Assert.assertTrue(checkout.error().contains(expectedError),"Mandatory field error not displayed");
        Reporter.log("Mandatory checkout validation passed",true);
    }
}