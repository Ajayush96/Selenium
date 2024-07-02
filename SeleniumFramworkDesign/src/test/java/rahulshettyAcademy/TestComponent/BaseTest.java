package rahulshettyAcademy.TestComponent;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import rahulshettyAcademy.pageObject.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/GlobalData.properties");
        prop.load(fis);
        String broswerName=prop.getProperty("browser");

        if(broswerName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver();
        }
       else if (broswerName.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
             driver = new FirefoxDriver();
        }
       else if(broswerName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
             driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
        TakesScreenshot ts=(TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        File file=new File("target"+testCaseName+ ".png");
        FileUtils.copyFile(source,file);
        return "target"+testCaseName+ ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver=initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        driver.close();
    }
}
