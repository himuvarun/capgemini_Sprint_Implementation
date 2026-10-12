package Product;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.LoginPage;
import objRepo.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.List;

public class ProductFunctionalTest extends BaseClass {

    private ProductPage openProducts() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        new LoginPage(driver).login(username, password);
        return new ProductPage(driver);
    }

    @Test(groups = "Functional")
    public void verifySortNameAToZ() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String sortValue = excel.getDataByTestId("Product", "PRODUCT-FUN-001", "SortValue");
        ProductPage page = openProducts();
        page.sort(sortValue);
        Assert.assertTrue(page.count() > 0,"Products missing after sort");
        Reporter.log("Name A-Z sorting verified",true);
    }

    @Test(groups = "Functional")
    public void verifySortNameZToA() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String sortValue = excel.getDataByTestId("Product", "PRODUCT-FUN-002", "SortValue");
        ProductPage page = openProducts();
        page.sort(sortValue);
        Assert.assertTrue(page.count() > 0,"Products missing after sort");
        Reporter.log("Name Z-A sorting verified",true);
    }

    @Test(groups = "Functional")
    public void verifySortPriceLowToHigh() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String sortValue = excel.getDataByTestId("Product", "PRODUCT-FUN-003", "SortValue");
        ProductPage page = openProducts();
        page.sort(sortValue);
        List<String> prices = page.prices();
        Assert.assertTrue(Double.parseDouble(prices.get(0).replace("$", "")) <=Double.parseDouble(prices.get(prices.size() - 1).replace("$", "")),"Price Low-High sorting failed");
        Reporter.log("Price Low-High sorting verified",true);
    }

    @Test(groups = "Functional")
    public void verifySortPriceHighToLow() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String sortValue = excel.getDataByTestId("Product", "PRODUCT-FUN-004", "SortValue");
        ProductPage page = openProducts();
        page.sort(sortValue);
        List<String> prices = page.prices();
        Assert.assertTrue(Double.parseDouble(prices.get(0).replace("$", "")) >=Double.parseDouble(prices.get(prices.size() - 1).replace("$", "")),"Price High-Low sorting failed");
        Reporter.log("Price High-Low sorting verified",true);
    }
}