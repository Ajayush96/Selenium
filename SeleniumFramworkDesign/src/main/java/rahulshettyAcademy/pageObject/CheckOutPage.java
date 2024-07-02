package rahulshettyAcademy.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyAcademy.abstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
    WebDriver driver;
    public CheckOutPage(WebDriver driver) {

        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement Country;
    @FindBy(xpath="//a[normalize-space()='Place Order']")
    WebElement submit;
    @FindBy(xpath="//body//app-root//button[2]")
    WebElement selectCountry;
    By results = By.cssSelector(".ta-results");

    public void selectCountry(String CountryName) throws InterruptedException {
        Actions a = new Actions(driver);
        a.sendKeys(Country,CountryName).build().perform();
       // waitForElementToAppear(results);
        Thread.sleep(2000);
        selectCountry.click();
    }


    public ConfirmationPage submitOrder()
    {
        submit.click();
       return  new ConfirmationPage(driver);
    }
}
