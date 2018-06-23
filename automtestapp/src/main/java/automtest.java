/**
 * Created by thanos-imac on 23/6/18.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class automtest {

    static WebDriver driver;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/Users/thanos-imac/IdeaProjects/automtestapp/src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://www.swtestacademy.com");
        driver.manage().window().maximize();
        // Wait for loading page
        waitForLoad(driver);
    }

    @Test
    public void automtest() throws InterruptedException {

        //TCO1
        //verify Home Page
        assertTrue(isElementPresent(By.xpath(("//h1[contains(., 'Software Testing Tutorials by Software Test Academy')]"))));
        driver.findElement(By.className("fusion-text"));
        waitForLoad(driver);
        //verify menu content
        driver.findElement(By.xpath("//a[contains(.,'About')]")).click();
        driver.findElement(By.xpath("//a[contains(.,'Home')]")).click();
        driver.findElement(By.xpath("//a[contains(.,'Contact')]")).click();
        waitForLoad(driver);
        //verify contact page
        driver.findElement(By.className("wpcf7-form"));
        // set some values (clear and sendkeys)
        driver.findElement(By.name("your-name")).sendKeys("Athanasios");
        driver.findElement(By.name("your-email")).sendKeys("Automation@Test.com");
        driver.findElement(By.name("your-subject")).sendKeys("TC for testing purpose");
        driver.findElement(By.name("your-message")).sendKeys("SW Test Academy News");
        driver.findElement(By.name("your-email")).clear();
        driver.findElement(By.name("your-email")).sendKeys("updatedEmail@Test.com");
        // click on button "Send"
        driver.findElement(By.xpath("//*[@id='wpcf7-f11-p18-o1']/form/p[5]/input")).click();
        // wait for page to load
        waitForLoad(driver);

        //TC02
        driver.findElement(By.xpath("//a[contains(.,'Contact')]")).click();
        driver.findElement(By.xpath("//*[@id='wpcf7-f11-p18-o1']/form/p[5]/input")).click();
        Thread.sleep(8000);

        //Verify errors
        assertTrue(isElementPresent(By.xpath("//*[@id='wpcf7-f11-p18-o1']/form/div[2]")));
        assertTrue(isElementPresent(By.xpath("//*[@id='wpcf7-f11-p18-o1']/form/p[1]/span/span")));
    }

    @AfterTest
    public void afterTest() {
        driver.close();
        driver.quit();
    }

    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(pageLoadCondition);
    }
    public Boolean isElementPresent(By by) {
        if (driver.findElements(by).size() > 0)
            return true;
        else
            return false;
    }

}

