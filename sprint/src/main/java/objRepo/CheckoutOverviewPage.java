package objRepo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {

    @FindBy(id = "finish")
    private WebElement finish;

    @FindBy(className = "title")
    private WebElement title;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> products;

    public CheckoutOverviewPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String title() {
        return title.getText();
    }

    public boolean contains(String name) {
        for (WebElement product : products) {
            if (product.getText().trim().equalsIgnoreCase(name.trim())) {
                return true;
            }
        }
        return false;
    }

    public void finish() {
        finish.click();
    }
}