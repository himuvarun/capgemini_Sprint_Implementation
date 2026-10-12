package objRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

    WebDriver driver;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean footer() {
        return !driver.findElements(By.cssSelector("footer.footer")).isEmpty();
    }

    public List<WebElement> links() {
        return driver.findElements(By.cssSelector("footer.footer a"));
    }

    public int linkCount() {
        return links().size();
    }

    public void clickFirst() {
        links().get(0).click();
    }

    public void clickLast() {
        List<WebElement> list = links();
        list.get(list.size() - 1).click();
    }

    public void clickLink(String text) {
        for (WebElement link : links()) {
            if (link.getText().contains(text)) {
                link.click();
                return;
            }
        }
    }

    public boolean sauceUrl() {
        return driver.getCurrentUrl()
                .contains("saucedemo.com");
    }
}