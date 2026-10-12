package ContactCommunity;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.ContactPage;
import objRepo.LoginPage;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.List;

public class ContactCommunityBVATest extends BaseClass {

    private ContactPage openContactCommunityArea() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        new LoginPage(driver).login(username, password);
        return new ContactPage(driver);
    }

    @Test(groups = "BVA")
    public void verifyFirstAvailableNavigationOptionBoundary() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        int expectedMinimum = Integer.parseInt(excel.getDataByTestId("ContactCommunity", "CONTACT-BVA-001", "MinFooterLinks"));
        ContactPage page = openContactCommunityArea();
        List<WebElement> links = page.links();
        Assert.assertTrue(links.size() >= expectedMinimum, "First navigation boundary is unavailable");
        Assert.assertNotNull(links.get(0), "First footer navigation link is unavailable");
        Reporter.log("First footer navigation boundary verified", true);
    }

    @Test(groups = "BVA")
    public void verifyBoundaryConditionScenario15() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        int expectedMinimum = Integer.parseInt(excel.getDataByTestId("ContactCommunity", "CONTACT-BVA-002", "MinFooterLinks"));
        ContactPage page = openContactCommunityArea();
        List<WebElement> links = page.links();
        Assert.assertTrue(links.size() >= expectedMinimum, "Footer navigation boundary is unavailable");
        Assert.assertNotNull(links.get(links.size() - 1), "Last footer navigation link is unavailable");
        Reporter.log("Last footer navigation boundary verified", true);
    }
}
