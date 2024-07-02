package rahulshettyAcademy.TestComponent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.ExtentReporterNG;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
         ExtentTest test;
         ExtentReports extent=ExtentReporterNG.getReportObject();
         ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        // This method is invoked when a test method starts execution
        test=extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);//assign a unique thread id
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // This method is invoked when a test method succeeds
       // test.log(Status.PASS,"Test Passed");
        extentTest.get().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // This method is invoked when a test method fails
        extentTest.get().fail(result.getThrowable());
        try {
            driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filePath= null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
        //Screenshot.Attached to report

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // This method is invoked when a test method is skipped
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // This method is invoked when a test method fails but within success percentage
    }

    @Override
    public void onStart(ITestContext context) {
        // This method is invoked before any test method belonging to the classes inside the <test> tag is run
    }

    @Override
    public void onFinish(ITestContext context) {
        // This method is invoked after all the test methods belonging to the classes inside the <test> tag have run
        extent.flush();
    }
}
