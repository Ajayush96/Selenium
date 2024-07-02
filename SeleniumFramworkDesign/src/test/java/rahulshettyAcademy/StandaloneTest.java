package rahulshettyAcademy;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyAcademy.pageObject.LandingPage;

import java.time.Duration;
import java.util.List;

public class StandaloneTest {
    public static void main(String[] args) throws InterruptedException {


        String productName="ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        LandingPage landingPage = new LandingPage(driver);
        driver.findElement(By.id("userEmail")).sendKeys("ajayush96@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Test@123");
        driver.findElement(By.id("login")).click();

       List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));

       WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName))
               .findFirst().orElse(null);

       prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
       // Thread.sleep(2000);
        driver.findElement(By.xpath(" //button[@routerlink='/dashboard/cart']")).click();

       List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
       Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"India").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        driver.findElement(By.xpath("(//button[contains(.,'India')])[2]")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();

        String ConfirmationMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals(" THANKYOU FOR THE ORDER.", ConfirmationMessage);
        driver.quit();


    }
}
