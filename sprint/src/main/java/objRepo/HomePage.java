package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(className = "login_logo")
    private WebElement logo;

    @FindBy(id = "user-name")
    private WebElement username;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean loginPage() {
        return logo.isDisplayed() && username.isDisplayed();
    }

    public String title(WebDriver driver) {
        return driver.getTitle();
    }

    public void openProducts(WebDriver driver) {
        driver.get(driver.getCurrentUrl().replaceAll("/$", "")+ "/inventory.html");
    }
}