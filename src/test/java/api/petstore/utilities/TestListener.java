package api.petstore.utilities;
import api.petstore.reporting.ExtentReportManager;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "PetStoreTestReport-" + timestamp + ".html";
        String reportPath = System.getProperty("user.dir") + "//Report//" + reportName;
        ExtentReportManager.setup(reportPath);
        logger.info("Test suite started: {}", context.getName());
        logger.info("Report will be generated at: {}", reportPath);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.startTest(result.getMethod().getMethodName());
        logger.info("Test started: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test passed");
        logger.info("Test passed: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + result.getThrowable());
        logger.error("Test failed: {}", result.getMethod().getMethodName(), result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP, "Test skipped: " + result.getThrowable());
        logger.warn("Test skipped: {}", result.getMethod().getMethodName(), result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.endTest();
        logger.info("Test suite finished: {}", context.getName());
    }
}
