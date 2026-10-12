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

public class PurchaseFunctionalTest extends BaseClass {

    private ExcelUtility excel = new ExcelUtility();

    private void login() throws Exception {
    	String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        new LoginPage(driver).login(username, password);
    }

    @Test(groups = "Functional")
    public void verifyProductCanBeAddedToCart() throws Exception {
        String productName = excel.getDataByTestId("Product", "PRODUCT-FUN-001", "ProductName");
        login();

        ProductPage productPage = new ProductPage(driver);
        productPage.addProduct(productName);
        productPage.openCart();

        Assert.assertEquals(new CartPage(driver).count(),1,"Product was not added to Cart");
        Reporter.log("Product added to cart successfully", true);
    }

    @Test(groups = "Functional")
    public void verifyAddedProductAppearsInCart() throws Exception {
        String productName = excel.getDataByTestId("Product", "PRODUCT-INT-001", "ProductName");
        login();

        ProductPage productPage = new ProductPage(driver);
        productPage.addProduct(productName);
        productPage.openCart();

        Assert.assertTrue(new CartPage(driver).contains(productName),"Product is missing in Cart");
        Reporter.log("Added product appears in Cart", true);
    }

    @Test(groups = "Functional")
    public void verifyProductPriceIsDisplayed() throws Exception {
        String productName = excel.getDataByTestId("Product", "PRODUCT-FUN-001", "ProductName");
        login();

        ProductPage productPage = new ProductPage(driver);
        productPage.addProduct(productName);
        productPage.openCart();

        Assert.assertTrue(driver.findElements(org.openqa.selenium.By.className("inventory_item_price")).size() > 0,"Cart price is missing");
        Reporter.log("Product price displayed successfully", true);
    }

    @Test(groups = "Functional")
    public void verifyProductCanBeRemoved() throws Exception {String productName = excel.getDataByTestId("Product", "PRODUCT-FUN-001", "ProductName");
        login();

        ProductPage productPage = new ProductPage(driver);
        productPage.addProduct(productName);
        productPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.remove(productName);

        Assert.assertEquals(cartPage.count(),0,"Product was not removed");
        Reporter.log("Product removal verified", true);
    }

    @Test(groups = "Functional")
    public void verifyCheckoutAcceptsCustomerDetails() throws Exception {

        String productName = excel.getDataByTestId("Product", "PRODUCT-FUN-001", "ProductName");
        String firstName = excel.getDataByTestId("Checkout", "CHECKOUT-FUN-001", "FirstName");
        String lastName = excel.getDataByTestId("Checkout", "CHECKOUT-FUN-001", "LastName");
        String postalCode = excel.getDataByTestId("Checkout", "CHECKOUT-FUN-001", "PostalCode");
        String expectedTitle = excel.getDataByTestId("Checkout", "CHECKOUT-FUN-001", "ExpectedTitle");
        login();

        ProductPage productPage = new ProductPage(driver);
        productPage.addProduct(productName);
        productPage.openCart();

        new CartPage(driver).checkout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterDetails(firstName,lastName,postalCode);
        checkoutPage.continuePage();

        Assert.assertEquals(new CheckoutOverviewPage(driver).title(),expectedTitle,"Checkout details were not accepted");
        Reporter.log("Customer details accepted successfully",true);
    }

    @Test(groups = "Functional")
    public void verifyUserCanCompletePurchase() throws Exception {
        String productName = excel.getDataByTestId("Product", "PRODUCT-FUN-001", "ProductName");
        String firstName = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "FirstName");
        String lastName = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "LastName");
        String postalCode = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "PostalCode");
        String expectedMessage = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "ExpectedMessage");
        login();

        ProductPage productPage = new ProductPage(driver);
        productPage.addProduct(productName);
        productPage.openCart();

        new CartPage(driver).checkout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterDetails(firstName,lastName,postalCode);
        checkoutPage.continuePage();

        new CheckoutOverviewPage(driver).finish();
        Assert.assertEquals(new CheckoutCompletePage(driver).message(),expectedMessage,"Order was not completed");
        Reporter.log("Purchase completed successfully",true);
    }

    @Test(groups = "Functional")
    public void verifyOrderConfirmationIsDisplayed() throws Exception {
        String productName = excel.getDataByTestId("Product", "PRODUCT-FUN-001", "ProductName");
        String firstName = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "FirstName");
        String lastName = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "LastName");
        String postalCode = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "PostalCode");
        String expectedMessage = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "ExpectedMessage");
        login();

        ProductPage productPage = new ProductPage(driver);
        productPage.addProduct(productName);
        productPage.openCart();

        new CartPage(driver).checkout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterDetails(firstName,lastName,postalCode);
        checkoutPage.continuePage();

        new CheckoutOverviewPage(driver).finish();
        String actualMessage =new CheckoutCompletePage(driver).message();
        Assert.assertEquals(actualMessage,expectedMessage,"Order confirmation message is incorrect");
        Reporter.log("Order confirmation displayed successfully",true);
    }
}