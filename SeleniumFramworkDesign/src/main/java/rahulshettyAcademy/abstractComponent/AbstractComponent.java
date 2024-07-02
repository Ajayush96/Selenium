package rahulshettyAcademy.abstractComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyAcademy.pageObject.CartPage;
import rahulshettyAcademy.pageObject.OrderPage;

import java.time.Duration;

public class AbstractComponent {

WebDriver driver;
    public AbstractComponent(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement cartHeader;

    @FindBy(css=".btn.btn-custom[routerlink='/dashboard/myorders']")
    WebElement orderHeader;
    public CartPage goTocartPage()
    {
        cartHeader.click();
        //CartPage cartPage = new CartPage(driver);
        return new CartPage(driver);
    }
    public OrderPage goToOrderPage()
    {
        orderHeader.click();
        //OrderPage orderPage = new OrderPage(driver);
        return new OrderPage(driver);
    }
    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForWebElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(findBy));
    }

    public void waitForElementToDisAppear(WebElement ele) throws InterruptedException {
        Thread.sleep(1000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.invisibilityOf(ele));
    }
}
