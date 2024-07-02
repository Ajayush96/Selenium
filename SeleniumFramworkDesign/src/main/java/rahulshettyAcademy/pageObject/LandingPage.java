package rahulshettyAcademy.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyAcademy.abstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {

    WebDriver driver;
    public LandingPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this); //this --> courent class
    }
    //WebElement userEmail=driver.findElement(By.id("userEmail"));

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement Submit;
    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCatelog LoginApplication(String email, String pass)
    {
      userEmail.sendKeys(email);
      userPassword.sendKeys(pass);
      Submit.click();
      ProductCatelog productCatelog = new ProductCatelog(driver);
      return productCatelog;
    }
    public String getErrorMessage()
    {
        waitForWebElementToAppear(errorMessage);
        String ErrorMessage=errorMessage.getText();
        return ErrorMessage;

    }

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }
}
