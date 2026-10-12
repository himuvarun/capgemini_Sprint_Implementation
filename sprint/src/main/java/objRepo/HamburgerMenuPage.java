package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HamburgerMenuPage {

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menu;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logout;

    public HamburgerMenuPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void open() {
        menu.click();
    }

    public void logout() {
        logout.click();
    }
}