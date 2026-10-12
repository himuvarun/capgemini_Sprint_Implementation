package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "title")
    private WebElement title;

    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterDetails(String first,String last,String zip) {

        firstName.clear();
        firstName.sendKeys(first);

        lastName.clear();
        lastName.sendKeys(last);

        postalCode.clear();
        postalCode.sendKeys(zip);
    }

    public void continuePage() {
        continueButton.click();
    }

    public String error() {
        return errorMessage.getText();
    }

    public String title() {
        return title.getText();
    }
}