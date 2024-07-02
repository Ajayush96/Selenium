package rahulshettyAcademy.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyAcademy.abstractComponent.AbstractComponent;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;
    public CartPage( WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".totalRow button")
    WebElement cheeckoutEle;
    @FindBy(css=".cartSection h3")
    List<WebElement> productTitles;

    public Boolean VerifyProductDisplay(String productName)
    {
        Boolean match=productTitles.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckOutPage goToCheckOut()
    {
        cheeckoutEle.click();
        return new CheckOutPage(driver);
    }
}
