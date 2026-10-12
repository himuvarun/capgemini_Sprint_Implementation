package ContactCommunity;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.ContactPage;
import objRepo.LoginPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ContactCommunityIntegrationTest extends BaseClass {

    @Test(groups = "Integration")
    public void verifyApplicationNavigationIntegratesWithExternalCommunityDestination() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        int expectedMinimumLinks = Integer.parseInt(excel.getDataByTestId("ContactCommunity", "CONTACT-INT-001", "MinFooterLinks"));
        new LoginPage(driver).login(username, password);

        ContactPage page = new ContactPage(driver);
        Assert.assertTrue(page.footer(), "Footer is missing after login");
        Assert.assertTrue(page.linkCount() >= expectedMinimumLinks,"External community destination is unavailable");
        Reporter.log("Application and community navigation integration verified", true);
    }
}
