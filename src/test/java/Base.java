import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Base {

    public static WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

//        Login to the website
        driver.get("http://hrm.pragmatictestlabs.com");

        driver.findElement(By.id("txtUsername")).sendKeys("admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");

        driver.findElement(By.id("btnLogin")).click();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.findElement(By.id("menu_pim_viewPimModule")).click();
        driver.findElement(By.id("menu_pim_addEmployee")).click();
    }

//    Clear user details
    @AfterMethod
    public void afterMethod() {
        driver.findElement(By.id("menu_pim_viewPimModule")).click();
        driver.findElement(By.id("empsearch_employee_name_empName")).sendKeys("Anuththara");
        driver.findElement(By.id("searchBtn")).click();
        driver.findElement(By.id("ohrmList_chkSelectAll")).click();
        driver.findElement(By.id("btnDelete")).click();

//        Confirm deletion in alert
        driver.findElement(By.id("dialogDeleteBtn")).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
