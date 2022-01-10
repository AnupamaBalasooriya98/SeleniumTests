import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAddEmployee extends Base {

    String firstName = "Anuththara";
    String middleName = "Maduwanthi";
    String lastName = "Kariyawasam";
    String jpegImg = "C:\\Users\\Anupama\\Desktop\\TestData\\Jpeg_image.png";
    String pngImg = "C:\\Users\\Anupama\\Desktop\\TestData\\Png_image.png";

    /**
     * Add employee with all details filled with login
     * @throws InterruptedException
     */
    @Test (priority = 1)
    public void testAddEmployeeSuccessCase() throws InterruptedException {
//        Enter first name
        driver.findElement(By.id("firstName")).sendKeys(firstName);
//        Enter middle name
        driver.findElement(By.id("middleName")).sendKeys(middleName);
//        Enter last name
        driver.findElement(By.id("lastName")).sendKeys(lastName);
//        Upload photo
        driver.findElement(By.id("photofile")).sendKeys(pngImg);

//        Check the checkbox for create login credentials
        driver.findElement(By.id("chkLogin")).click();

//        Create login details
        driver.findElement(By.id("user_name")).sendKeys(firstName + " " + lastName);
        driver.findElement(By.id("user_password")).sendKeys(firstName + "#321");
        driver.findElement(By.id("re_password")).sendKeys(firstName + "#321");

//        Click save button
        driver.findElement(By.id("btnSave")).click();

//        Wait till page load
        Thread.sleep(2000);

        String actual = driver.findElement(By.id("profile-pic")).getText().toString();
        Assert.assertEquals(actual, firstName + " " + middleName + " " + lastName);
    }

    /**
     * Add employee with filling only required fields with login
     */
    @Test (priority = 2)
    public void testAddEmployeeOnlyFillingRequiredFields() throws InterruptedException {
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);

//        Create login details
        driver.findElement(By.id("chkLogin")).click();

        driver.findElement(By.id("user_name")).sendKeys(firstName + " " + lastName);
        driver.findElement(By.id("user_password")).sendKeys(firstName + "#321");
        driver.findElement(By.id("re_password")).sendKeys(firstName + "#321");

//        Click save button
        driver.findElement(By.id("btnSave")).click();

//        Wait till page load
        Thread.sleep(2000);

        String actual = driver.findElement(By.id("profile-pic")).getText().toString();
        Assert.assertEquals(actual, firstName + " " + lastName);
    }

    /**
     * Add employee with filling all the fields except First Name
     */
    @Test (priority = 3)
    public void testAddEmployeeWithoutFirstName() {
        driver.findElement(By.id("middleName")).sendKeys(middleName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("photofile")).sendKeys(pngImg);

//        Create login details
        driver.findElement(By.id("chkLogin")).click();

        String actual = driver.findElement(By.className("validation-error")).getText();
        Assert.assertEquals(actual, "Required");
    }

}
