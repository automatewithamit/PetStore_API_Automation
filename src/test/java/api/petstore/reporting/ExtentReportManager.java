package api.petstore.reporting;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static void setup(String reportPath) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("PetStore Test Report");
        sparkReporter.config().setReportName("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    public static void startTest(String testName) {
        test = extent.createTest(testName);
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void endTest() {
        if (extent != null) {
            extent.flush();
        }
    }
}
