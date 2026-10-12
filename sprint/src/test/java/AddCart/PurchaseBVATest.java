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

public class PurchaseBVATest extends BaseClass 
{

    @Test(groups = "BVA")
    public void verifyPostalCodeBoundary() throws Exception {

        ExcelUtility excel = new ExcelUtility();

        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        String productName = excel.getDataByTestId("Product", "PRODUCT-FUN-001", "ProductName");
        String firstName = excel.getDataByTestId("Checkout", "CHECKOUT-BVA-001", "FirstName");
        String lastName = excel.getDataByTestId("Checkout", "CHECKOUT-BVA-001", "LastName");
        String postalCode = excel.getDataByTestId("Checkout", "CHECKOUT-BVA-001", "PostalCode");
        String expectedUrlPart = excel.getDataByTestId("Checkout", "CHECKOUT-BVA-001", "ExpectedUrlPart");

        new LoginPage(driver).login(username, password);
        ProductPage product = new ProductPage(driver);
        product.addProduct(productName);
        product.openCart();

        new CartPage(driver).checkout();
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.enterDetails(firstName, lastName, postalCode);
        checkout.continuePage();

        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlPart),"Postal code boundary was rejected unexpectedly");
        Reporter.log("Postal code boundary value accepted successfully: " + postalCode,true);
    }
}