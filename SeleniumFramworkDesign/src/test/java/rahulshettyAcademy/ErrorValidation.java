package rahulshettyAcademy;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import rahulshettyAcademy.TestComponent.BaseTest;
import rahulshettyAcademy.TestComponent.Retry;
import rahulshettyAcademy.pageObject.*;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class ErrorValidation extends BaseTest {

    @Test(groups={"ErrorHandling"},retryAnalyzer = Retry.class)
    public void LoginErrorValiadtion() throws IOException, InterruptedException {
        String productName="ZARA COAT 3";
        ProductCatelog productCatelog=landingPage.LoginApplication("ajayush96@gmail.com","Test@1234");
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());

    }

    @Test(retryAnalyzer = Retry.class)
    public void ProductErrorValidation() throws IOException, InterruptedException {
        String productName="ZARA COAT 3";
        ProductCatelog productCatelog=landingPage.LoginApplication("test@12.com","Test@123");
        List<WebElement> products= productCatelog.getProductList();
        productCatelog.addProductToCart(productName);
        CartPage cartPage=productCatelog.goTocartPage();
        Boolean match=cartPage.VerifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);

    }
}
