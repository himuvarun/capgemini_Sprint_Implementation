package ContactCommunity;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.ContactPage;
import objRepo.LoginPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ContactCommunitySmokeTest extends BaseClass {

	@Test(groups = {"Smoke", "Functional"})
	public void verifyCommunitySupportNavigationIsAvailable() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        int expectedMinimumLinks = Integer.parseInt(excel.getDataByTestId("ContactCommunity", "CONTACT-SMO-001", "MinFooterLinks"));

        new LoginPage(driver).login(username, password);
        ContactPage page = new ContactPage(driver);

        Assert.assertTrue(page.footer(), "Footer is not displayed");
        Assert.assertTrue(page.linkCount() >= expectedMinimumLinks, "Community navigation links are unavailable");
        Reporter.log("Community/support navigation area is available", true);
    }
}
