package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    //ExtentReports extent;
    public static ExtentReports getReportObject()
    {
        ExtentSparkReporter reporter = new ExtentSparkReporter("target/Spark.html");
        reporter.config().setReportName("Web Automation Result");
        reporter.config().setDocumentTitle("Test Result");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Ashish");
        return extent;
    }
}
