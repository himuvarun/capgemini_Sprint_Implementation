package ContactCommunity;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.ContactPage;
import objRepo.LoginPage;
import objRepo.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ContactCommunitySystemTest extends BaseClass {

    @Test(groups = "System")
    public void verifyCompleteApplicationToContactCommunityFlow() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        int minimumLinks = Integer.parseInt(excel.getDataByTestId("ContactCommunity", "CONTACT-SYS-001", "MinFooterLinks"));
        new LoginPage(driver).login(username, password);
        ContactPage page = new ContactPage(driver);
        Assert.assertTrue(page.footer(), "Footer is missing in complete flow");
        Assert.assertTrue(page.linkCount() >= minimumLinks, "ContactCommunity navigation is unavailable");
        Reporter.log("Complete application to ContactCommunity flow verified", true);
    }

    @Test(groups = "System")
    public void verifyEndToEndContactCommunityScenario10() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        int minimumLinks = Integer.parseInt(excel.getDataByTestId("ContactCommunity", "CONTACT-SYS-002", "MinFooterLinks"));
        new LoginPage(driver).login(username, password);
        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.count() > 0, "Products are missing");
        ContactPage page = new ContactPage(driver);
        Assert.assertTrue(page.linkCount() >= minimumLinks, "ContactCommunity navigation is unavailable");
        Reporter.log("End-to-end ContactCommunity scenario 10 verified", true);
    }
}
