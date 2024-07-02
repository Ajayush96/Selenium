package rahulshettyAcademy;

import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyAcademy.TestComponent.BaseTest;
import rahulshettyAcademy.TestComponent.Retry;
import rahulshettyAcademy.pageObject.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData",groups = {"Purchase"},retryAnalyzer = Retry.class)
    public void submitOrder(String Email,String pass,String productName) throws IOException, InterruptedException {
        ProductCatelog productCatelog = landingPage.LoginApplication(Email,pass);
        //ProductCatelog productCatelog = new ProductCatelog(driver);
        List<WebElement> products = productCatelog.getProductList();
        productCatelog.addProductToCart(productName);
        CartPage cartPage = productCatelog.goTocartPage();
        //CartPage cartPage = new CartPage(driver);
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckOutPage checkoutPage = cartPage.goToCheckOut();
        checkoutPage.selectCountry("India");
        ConfirmationPage cp = checkoutPage.submitOrder();
        String ConfirmationMessage = cp.getConfirmationMessage();
        Assert.assertEquals("THANKYOU FOR THE ORDER.", ConfirmationMessage);
    }

    //To verify the Zara coat 3 is displaying in order page
    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest() {
        ProductCatelog productCatelog = landingPage.LoginApplication("ajayush96@gmail.com", "Test@123");
        OrderPage orderPage = productCatelog.goToOrderPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData()
    {
      return new Object[][]  {{"ajayush96@gmail.com","Test@123","ZARA COAT 3"},{"test@12.com","Test@123","ZARA COAT 3"}};
    }
}
