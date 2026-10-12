package AddCart;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.CartPage;
import objRepo.CheckoutCompletePage;
import objRepo.CheckoutOverviewPage;
import objRepo.CheckoutPage;
import objRepo.LoginPage;
import objRepo.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class PurchaseIntegrationTest extends BaseClass {
    private ExcelUtility excel = new ExcelUtility();
    private void login() throws Exception {
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        new LoginPage(driver).login(username, password);
    }

    @Test(groups = "Integration")
    public void verifyProductToCartIntegration() throws Exception {
        String productName = excel.getDataByTestId("Product", "PRODUCT-INT-001", "ProductName");
        login();

        ProductPage product = new ProductPage(driver);
        product.addProduct(productName);
        product.openCart();
        Assert.assertEquals(new CartPage(driver).count(),1,"Product to Cart integration failed");
        Reporter.log("Product to Cart integration passed",true);
    }

    @Test(groups = "Integration")
    public void verifyCartDetailsTransferToCheckout() throws Exception {
    	
        String productName = excel.getDataByTestId("Product", "PRODUCT-INT-001", "ProductName");
        String firstName = excel.getDataByTestId("Checkout", "CHECKOUT-INT-001", "FirstName");
        String lastName = excel.getDataByTestId("Checkout", "CHECKOUT-INT-001", "LastName");
        String postalCode = excel.getDataByTestId("Checkout", "CHECKOUT-INT-001", "PostalCode");
        String expectedTitle = excel.getDataByTestId("Checkout", "CHECKOUT-INT-001", "ExpectedTitle");
        login();
        
        ProductPage product = new ProductPage(driver);
        product.addProduct(productName);
        product.openCart();
        
        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.contains(productName),"Product is not available in Cart");
        cart.checkout();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"),"Cart did not navigate to Checkout");
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.enterDetails(firstName,lastName,postalCode);
        checkout.continuePage();
        CheckoutOverviewPage overview =new CheckoutOverviewPage(driver);

        Assert.assertEquals(overview.title(),expectedTitle,"Cart details were not transferred to Checkout Overview");
        Assert.assertTrue(overview.contains(productName),"Cart product was not transferred to Checkout Overview");
        Reporter.log("Cart details transferred to Checkout successfully",true);
    }

    @Test(groups = "Integration")
    public void verifyCheckoutIntegratesWithOrderCompletion() throws Exception {

        String productName = excel.getDataByTestId("Product", "PRODUCT-INT-001", "ProductName");
        String firstName = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "FirstName");
        String lastName = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "LastName");
        String postalCode = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "PostalCode");
        String expectedMessage = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "ExpectedMessage");
        login();

        ProductPage product = new ProductPage(driver);
        product.addProduct(productName);
        product.openCart();

        new CartPage(driver).checkout();
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.enterDetails(firstName,lastName,postalCode);
        checkout.continuePage();

        CheckoutOverviewPage overview =new CheckoutOverviewPage(driver);
        overview.finish();
        String actualMessage =new CheckoutCompletePage(driver).message();
        Assert.assertEquals(actualMessage,expectedMessage,"Checkout did not integrate with order completion");
        Reporter.log("Checkout to order completion integration passed",true);
    }
}