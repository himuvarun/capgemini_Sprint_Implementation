package Product;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.LoginPage;
import objRepo.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.List;

public class ProductBVATest extends BaseClass {

    @Test(groups = "BVA")
    public void verifyPriceSortingBoundary() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        String lowToHigh = excel.getDataByTestId("Product", "PRODUCT-BVA-001", "LowToHighSort");
        String highToLow = excel.getDataByTestId("Product", "PRODUCT-BVA-001", "HighToLowSort");
        double lowestPrice = Double.parseDouble(excel.getDataByTestId("Product", "PRODUCT-BVA-001", "LowestPrice"));
        double highestPrice = Double.parseDouble(excel.getDataByTestId("Product", "PRODUCT-BVA-001", "HighestPrice"));
        new LoginPage(driver).login(username, password);

        ProductPage product = new ProductPage(driver);
        product.sort(lowToHigh);
        List<String> lowToHighPrices = product.prices();
        Assert.assertEquals(Double.parseDouble(lowToHighPrices.get(0).replace("$", "")),lowestPrice,"Lowest price boundary is incorrect");
        Assert.assertEquals(Double.parseDouble(lowToHighPrices.get(lowToHighPrices.size() - 1).replace("$", "")),highestPrice,"Highest price boundary is incorrect");
        product.sort(highToLow);

        List<String> highToLowPrices = product.prices();
        Assert.assertEquals(Double.parseDouble(highToLowPrices.get(0).replace("$", "")),highestPrice,"Highest price boundary is not first");
        Assert.assertEquals(Double.parseDouble(highToLowPrices.get(highToLowPrices.size() - 1).replace("$", "")),lowestPrice,"Lowest price boundary is not last");
        Reporter.log("Product price boundary values and sorting were verified",true);
    }
}