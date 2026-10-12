package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String value) {
        username.clear();
        username.sendKeys(value);
    }

    public void enterPassword(String value) {
        password.clear();
        password.sendKeys(value);
    }

    public void login() {
        loginButton.click();
    }

    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        login();
    }

    public boolean passwordMasked() {
        return password.getAttribute("type").equals("password");
    }

    public String error() {
        return errorMessage.getText();
    }

    public String url(WebDriver driver) {
        return driver.getCurrentUrl();
    }
}