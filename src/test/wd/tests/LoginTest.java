package wd.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import wd.pages.LoginPage;

import java.util.Arrays;

public class LoginTest {
    String base_url = "http://localhost:8080";
    StringBuffer verificationErrors = new StringBuffer();
    WebDriver driver = null;

    @BeforeClass
    public void beforeClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Masha/Downloads/chromedriver_win32/chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
        driver = new ChromeDriver(capabilities);
        driver.get(base_url);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }

    @Test
    public void loginTest() {

        LoginPage page = new LoginPage(driver);

        //Assert.assertTrue(page.isFormPresentForReal(), "No suitable forms found!");
        //verificationErrors.append(page.getErrorOnTextAbsence("User:"));
        //verificationErrors.append(page.getErrorOnTextAbsence("Password:"));

        page.setName("Admin");
        Assert.assertEquals(page.getName(), "Admin", "Unable to fill 'User' field");

        page.setPassword("admin");
        Assert.assertEquals(page.getPassword(), "admin", "Unable to fill 'Password' field");

        page.submitLoginForm();

        //Assert.assertFalse(page.isFormPresentForReal(), "Form is on the page!");
        Assert.assertFalse(driver.getTitle().equals("Jenkins"), "Still on start page!");
    }
}
