package Login;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.LoginPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginNegativeTest extends BaseClass {

    @Test(groups = "Negative")
    public void verifyInvalidCredentialsShowError() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String username =excel.getDataByTestId("Login","LOGIN-NEG-001","Username");
        String password =excel.getDataByTestId("Login","LOGIN-NEG-001","Password");
        String expectedError =excel.getDataByTestId("Login","LOGIN-NEG-001","ExpectedErrorPart");
        
        LoginPage page = new LoginPage(driver);
        page.login(username, password);
        Assert.assertTrue(page.error().contains(expectedError),"Invalid login error is missing");
        Reporter.log("Invalid credential validation completed",true);
    }

    @Test(groups = "Negative")
    public void verifyLockedUserCannotLogin() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String username =excel.getDataByTestId("Login","LOGIN-NEG-002","Username");
        String password =excel.getDataByTestId("Login","LOGIN-NEG-002","Password");
        String expectedError =excel.getDataByTestId("Login","LOGIN-NEG-002","ExpectedErrorPart");
        
        LoginPage page = new LoginPage(driver);
        page.login(username, password);
        Assert.assertTrue(page.error().contains(expectedError),"Locked user error is missing");
        Reporter.log("Locked user validation completed",true);
    }
}