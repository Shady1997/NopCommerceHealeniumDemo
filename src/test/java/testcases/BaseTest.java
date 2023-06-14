package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.epam.healenium.SelfHealingDriver;
import listener.TestExecutionListener;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.example.driver.DriverFactory;
import org.example.util.Utility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.example.driver.DriverHolder.setDriver;

/**
 * Project Name    : Nop Commerce
 * Developer       : Shady Ahmed
 * Version         : 1.0.0
 * Date            : 02/15/2023
 * Time            : 7:27 PM
 * Description     :
 **/
@Listeners(TestExecutionListener.class)
public class BaseTest{
    // TODO: define web driver object
    public WebDriver driver;

    // extend report
    protected static ExtentSparkReporter htmlReporter;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    private String PROJECT_NAME=null;
    // define Suite Elements
    private Properties prop;
    private FileInputStream readProperty;
    protected Logger log;
    @BeforeSuite
    public void defineSuiteElements() {
        // log4j
        DOMConfigurator.configure(System.getProperty("user.dir")+"\\log4j.xml");
        log = Logger.getLogger(getClass());

        // initialize the HtmlReporter
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/testReport.html");

        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // initialize test
        test = extent.createTest(PROJECT_NAME+" Test Automation Project");

        //configuration items to change the look and fee add content, manage tests etc
        htmlReporter.config().setDocumentTitle(PROJECT_NAME+" Test Automation Report");
        htmlReporter.config().setReportName(PROJECT_NAME+" Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }
    @Parameters("browser")
    @BeforeMethod
    public void setupDriver(String browser) throws IOException {
        WebDriver delegate = DriverFactory.getNewInstance(browser);
        setDriver(delegate);
        driver = SelfHealingDriver.create(delegate);

        driver.manage().window().maximize();

        // TODO: Set Driver implicit wait
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        driver.get(getURL());
        log.info("load url");
    }
    private String getURL() throws IOException {
        // TODO: Step1: define object of properties file
        readProperty = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/properties/generalProperties.properties");
        prop = new Properties();
        prop.load(readProperty);

        // define project name from properties file
        PROJECT_NAME=prop.getProperty("projectName");

        return prop.getProperty("url");
    }
    @AfterSuite
    public void tearDown() throws IOException {
        extent.flush();
        //start html report after test end
        Utility.startHtmlReport(System.getProperty("user.dir"), "testReport.html");
    }
    @AfterTest
    public void quit() {
        if(driver!=null)
        driver.quit();
    }
    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, result.getName()+" failed with the following error: "+result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getName());
        } else
            test.log(Status.SKIP, result.getName());
    }
}

