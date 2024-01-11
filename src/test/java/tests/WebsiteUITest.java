package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebsiteUITest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C://automation//drivers//chromedriver.exe");

        // Optional: Set Chrome options, e.g., headless mode
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // Uncomment this line to run in headless mode

        // Create a new instance of the Chrome driver
        driver = new ChromeDriver(options);

        // Set implicit wait time to handle page loading and elements finding
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testHeaderLinks() {
        // Navigate to the website
        driver.get("https://www.90min.com");

        List<String> expectedLinks = Arrays.asList(

                "Premier League",
                "Transfers",
                "Champions League",
                "FanVoice",
                "The Switch",
                "EFL",
                "La Liga",
                "Serie A",
                "More"
        );

        // Locate the header menu
        WebElement headerMenu = driver.findElement(By.cssSelector(".fixedNav_kx15tq>.navHiddenLayer_1ing288"));

       // Get all links in the header menu
        List<WebElement> headerLinks = headerMenu.findElements(By.tagName("li"));

        // SoftAssert for multiple assertions
        SoftAssert softAssert = new SoftAssert();

        // Check if all expected links are displayed
        for (String expectedLink : expectedLinks) {
            boolean found = false;
            for (WebElement link : headerLinks) {
                if (link.getText().equalsIgnoreCase(expectedLink)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                System.out.println(expectedLink + " is displayed.");
            } else {
                System.out.println(expectedLink + " is not displayed.");
            }
            softAssert.assertTrue(found, expectedLink + " is not displayed.");
        }
        // Assert all failures
        softAssert.assertAll();
    }
    

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
