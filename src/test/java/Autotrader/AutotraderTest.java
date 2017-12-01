package Autotrader;

import Utilities.DriverSelection;
import Utilities.SpreadSheetReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AutotraderTest {

    private String url = "https://www.autotrader.co.uk/";
    private WebDriver driver;
    private static ExtentReports report;
    private SpreadSheetReader reader;
    List<String> fields;
    //static Properties config = new Properties();

    @BeforeClass
    public static void setUpBeforeTestClass() {

        report = new ExtentReports();
        String fileName = "AutoTraderTesting_ExtentReport" + ".html";
        String filePath = System.getProperty("user.dir")
                + File.separatorChar + fileName;
        report.attachReporter(new ExtentHtmlReporter(filePath));

    }

    @Before
    public void setUpBeforeTestMethod() {
        reader = new SpreadSheetReader(System.getProperty("user.dir") + File.separatorChar + "AutoTraderInputs.xlsx");
        fields = reader.readRow(1, "AutotraderSearch");
        DriverSelection selDriver = new DriverSelection();
        driver = selDriver.getDriver(fields.get(0));
        driver.manage().window().maximize();
        driver.navigate().to(url);

    }

    @After
    public void tearDownAfterTestMethod() {
        driver.quit();
    }

    @AfterClass
    public static void tearDownAfterTestClass() {
        report.flush();
    }

    @Test
    public void performAllSearch() {
        ExtentTest allSearch = report.createTest("All search test");

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.populatePostcode(fields.get(1));
        allSearch.log(Status.INFO, "Postcode populated");

        homePage.clickSearchButton();
        allSearch.log(Status.INFO, "Search button clicked");

        ResultPage resultPage = PageFactory.initElements(driver, ResultPage.class);
        assertTrue(resultPage.getCountOfSearchResults() > 10000);
        allSearch.log(Status.PASS, "Postcode only search test completed successfully");
    }

    @Test
    public void performSearchNoPostcode() {
        ExtentTest noPostcode = report.createTest("All search test");

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickSearchButton();
        noPostcode.log(Status.INFO, "Search button clicked");

        assertEquals(url, driver.getCurrentUrl());
        noPostcode.log(Status.PASS, "Attempted empty search test completed successfully");
    }

    @Test
    public void performSearchPostcodeSpecifiedRangeAllCars() {
        ExtentTest specifiedRange = report.createTest("Specified range test");

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.populatePostcode(fields.get(1));
        specifiedRange.log(Status.INFO, "Postcode populated");
        homePage.populateRange(getRadiusInput());
        specifiedRange.log(Status.INFO, "Radius populated");

        homePage.clickSearchButton();
        specifiedRange.log(Status.INFO, "Search button clicked");


        ResultPage resultPage = PageFactory.initElements(driver, ResultPage.class);
        resultPage.navigateToTopResult();

        VehiclePage vehiclePage = PageFactory.initElements(driver, VehiclePage.class);
        if (fields.get(2).equals("National")){
            specifiedRange.log(Status.WARNING, "Specified range test not valid for National search. Amend data in AutoTraderInputs.xlsx.");
        }
        else {
            assertTrue(vehiclePage.getRangeOfTopResult(fields.get(1)) <= Integer.parseInt(fields.get(2)));
            specifiedRange.log(Status.PASS, "Specified range test completed successfully");
        }

    }

    private String getRadiusInput() {
        String radius = fields.get(2);
        if (radius.equals("National")) {
            return "National";
        }
        if (radius.equals("1")) {
            return "Within 1 mile";
        }
        else {
            return "Within " + radius + "miles";
        }
    }

    @Test
    public void performSearchRangeSpecificMakeAndModel() {
        ExtentTest makeAndModel = report.createTest("Make/Model test");

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.populatePostcode(fields.get(1));
        makeAndModel.log(Status.INFO, "Postcode populated");
        homePage.populateRange(getRadiusInput());
        makeAndModel.log(Status.INFO, "Radius populated");

        homePage.clickSearchButton();
        makeAndModel.log(Status.INFO, "Search button clicked");


        ResultPage resultPage = PageFactory.initElements(driver, ResultPage.class);
        resultPage.getMakeModelOfTopResult();
    }

    @Test
    public void performSearchAllFieldsSelected(){
        ExtentTest allSearchCriteria = report.createTest("Make/Model test");

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.populateAllStandardSearchFields(allSearchCriteria);
    }

    @Test
    public void performSearchExtendedOptionsPopulated() {

    }
}
