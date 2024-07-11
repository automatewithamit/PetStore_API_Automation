package api.petstore.utilities;
import api.petstore.reporting.ExtentReportManager;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat(("yyyy.mm.dd.hh.mm.ss")).format(new Date());
        String reportName = "PetStoreTestReport-"+timestamp+".html";
        String reportPath =System.getProperty("user.dir")+ "//report//"+reportName;
        ExtentReportManager.setup(reportPath);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP, "Test skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.endTest();
    }
}
