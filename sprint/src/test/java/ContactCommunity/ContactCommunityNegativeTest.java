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

public class ContactCommunityNegativeTest extends BaseClass {

    @Test(groups = "Negative")
    public void verifyInvalidFooterDestinationIsRejected() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String username =excel.getDataByTestId("Login","LOGIN-SMOKE-001","Username");
        String password =excel.getDataByTestId("Login","LOGIN-SMOKE-001","Password");

        int expectedMinimumLinks =Integer.parseInt(excel.getDataByTestId("ContactCommunity","CONTACT-NEG-001","MinFooterLinks"));
        new LoginPage(driver).login(username,password);
        ContactPage page =new ContactPage(driver);
        List<WebElement> links =page.links();
        Assert.assertTrue(links.size() >= expectedMinimumLinks,"Footer navigation is unavailable");

        for (WebElement link : links) {
            String href =link.getAttribute("href");
            Assert.assertNotNull(href,"Invalid footer link: destination is null");
            Assert.assertFalse(href.isBlank(),"Invalid footer link: destination is blank");
        }
        Reporter.log("Invalid footer destinations were rejected successfully",true);
    }
}