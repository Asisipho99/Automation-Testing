package datadriventesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DataDrivenTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test(dataProvider = "LoginData")
    public void loginTest(String user, String pwd, String exp) {

        driver.get("https://admin-demo.nopcommerce.com/login");

        WebElement webElementEmail = driver.findElement(By.id("Email"));
        webElementEmail.clear();
        webElementEmail.sendKeys(user);

        WebElement webElementPassword = driver.findElement(By.id("Password"));
        webElementPassword.clear();
        webElementPassword.sendKeys(pwd);

        driver.findElement(By.xpath("//input[@value='Log in']")).click();

        String exp_title = "Dashboard / napCommerce administration";
        String act_title = driver.getTitle();

        if (exp.equals("Valid")) {

            if (exp_title.equals(act_title)) {

                driver.findElement(By.linkText("Logout")).click();
                Assert.assertTrue(true);
            } else {

                Assert.assertTrue(false);
            }
        } else if (exp.equals("Invalid")) {

            if (exp_title.equals(act_title)) {

                driver.findElement(By.linkText("Logout")).click();
                Assert.assertTrue(false);
            } else {

                Assert.assertTrue(true);
            }
        }
    }

    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        /**String[][] loginData = {
                {"admin@yourstore.com", "admin", "Valid"},
                {"admin@yourstore.com", "adm", "Invalid"},
                {"admin@yourstore.com", "admin", "Invalid"},
                {"admin@yourstore.com", "adm", "Invalid"}
        };**/

        String path = "loginData.xlsx";
        XLUtility xlUtility = new XLUtility(path);

        int totalrows = xlUtility.getRowCount("Sheet1");
        int totalcols = xlUtility.getCellCount("Sheet1", 1);

        String loginData[][] = new String[totalrows][totalcols];

        for (int i=1; i<totalrows; i++) {

            for (int j = 0; j < totalcols; j++) {
                loginData[i-1][j] = xlUtility.getCellData("Sheet1", i, j);
            }
        }
        return loginData;
    }

    @AfterClass
    void tearDown() {
        driver.close();
    }
}