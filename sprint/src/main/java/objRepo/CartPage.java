package objRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    WebDriver driver;

    @FindBy(className = "title")
    private WebElement title;

    @FindBy(id = "checkout")
    private WebElement checkout;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String title() {
        return title.getText();
    }

    public int count() {
        return driver.findElements(By.className("cart_item")).size();
    }

    public void checkout() {
        checkout.click();
    }

    public boolean contains(String name) {

        List<WebElement> products =driver.findElements(By.className("inventory_item_name"));
        for (WebElement product : products) {
            if (product.getText().trim().equalsIgnoreCase(name.trim())) {
                return true;
            }
        }
        return false;
    }

    public void remove(String name) {
        String xpath ="//div[text()='" + name+ "']/ancestor::div[@class='cart_item']//button";

        driver.findElement(By.xpath(xpath)).click();
    }
}