package objRepo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

    WebDriver driver;

    @FindBy(className = "title")
    private WebElement title;

    @FindBy(className = "shopping_cart_link")
    private WebElement cart;

    @FindBy(className = "product_sort_container")
    private WebElement sort;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String title() {
        return title.getText();
    }

    public int count() {
        return driver.findElements(By.className("inventory_item")).size();
    }
    public void addProduct(String name) {
        String xpath ="//div[text()='" + name + "']/ancestor::div[@class='inventory_item']//button";
        driver.findElement(By.xpath(xpath)).click();
    }
    
    public void openCart() {
        cart.click();
    }
    
    public void sort(String value) {
        new Select(sort).selectByValue(value);
    }
    
    public List<String> prices() {
        List<String> list = new ArrayList<>();
        for (WebElement price :
                driver.findElements(By.className("inventory_item_price"))) {
            list.add(price.getText());
        }
        return list;
    }
    
    public List<String> names() {
        List<String> list = new ArrayList<>();
        for (WebElement name :
                driver.findElements(By.className("inventory_item_name"))) {
            list.add(name.getText());
        }
        return list;
    }
}