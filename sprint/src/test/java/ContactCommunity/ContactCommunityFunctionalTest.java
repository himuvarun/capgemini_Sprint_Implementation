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

public class ContactCommunityFunctionalTest extends BaseClass {

    private ContactPage openContactCommunityArea() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        new LoginPage(driver).login(username, password);
        return new ContactPage(driver);
    }

    @Test(groups = "Functional")
    public void verifySauceLabsCommunityLink() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String socialLink1 = excel.getDataByTestId("ContactCommunity", "CONTACT-FUN-002", "SocialLink1");
        String socialLink2 = excel.getDataByTestId("ContactCommunity", "CONTACT-FUN-002", "SocialLink2");
        String socialLink3 = excel.getDataByTestId("ContactCommunity", "CONTACT-FUN-002", "SocialLink3");

        ContactPage page = openContactCommunityArea();
        List<WebElement> links = page.links();
        boolean available = false;
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href != null && (href.contains(socialLink1) || href.contains(socialLink2) || href.contains(socialLink3))) 
            	{
                	available = true;
                	break;
            	}
        	}

        Assert.assertTrue(available,"No community/social link found");
        Reporter.log("Community/social navigation is accessible",true);
    }

    @Test(groups = "Functional")
    public void verifySupportHelpNavigation() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        int minimumLinks = Integer.parseInt(excel.getDataByTestId("ContactCommunity","CONTACT-FUN-003","MinFooterLinks"));
        ContactPage page = openContactCommunityArea();

        Assert.assertTrue(page.linkCount() >= minimumLinks,"Footer navigation is unavailable");
        Reporter.log("Footer support/community navigation verified",true);
    }

    @Test(groups = "Functional")
    public void verifyExternalLink() throws Exception {

        ContactPage page = openContactCommunityArea();
        List<WebElement> links = page.links();
        Assert.assertFalse(links.isEmpty(),"No footer link is available");
        String href = links.get(0).getAttribute("href");
        Assert.assertNotNull(href,"Footer link has no destination");
        Assert.assertFalse(href.isBlank(),"Footer link destination is blank");
        Reporter.log("Selected footer destination verified",true);
    }

    @Test(groups = "Functional")
    public void verifyBrowserNavigation() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String expectedUrl = excel.getDataByTestId("ContactCommunity","CONTACT-FUN-005","ExpectedApplicationUrl");
        ContactPage page = openContactCommunityArea();
        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl,"Application URL is unexpected");
        driver.navigate().refresh();
        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl,"Application URL changed after refresh");
        Assert.assertTrue(page.footer(),"Footer disappeared after refresh");
        Reporter.log("Browser navigation behavior verified",true);
    }
}