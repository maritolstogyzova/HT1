package wd.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import wd.pages.DashboardJenkinsPage;
import wd.pages.LoginPage;
import wd.pages.ManageJenkinsPage;
import wd.pages.ManageUsersJenkinsPage;

import java.util.Arrays;

public class Test_ManageJenkinsPage {
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
    public void manageJenkinsTest() {

        LoginPage loginPage = new LoginPage(driver);

        //Assert.assertTrue(loginPage.isFormPresentForReal(), "No suitable forms found!");
        //Authorization
        loginPage.setName("Admin");
        Assert.assertEquals(loginPage.getName(), "Admin", "Unable to fill 'User' field");
        loginPage.setPassword("admin");
        Assert.assertEquals(loginPage.getPassword(), "admin", "Unable to fill 'Password' field");
        loginPage.submitLoginForm();


        //1. После клика по ссылке «Manage Jenkins» на странице появляется элемент dt с текстом «Manage Users» и элемент dd
        // с текстом «Create/delete/modify users that can log in to this Jenkins».
        DashboardJenkinsPage dashboardJenkinsPage = new DashboardJenkinsPage(driver);
        //Checking page contains text
        Assert.assertTrue(dashboardJenkinsPage.pageTextContains("Manage Jenkins"));
        dashboardJenkinsPage.clickLinkManageJenkins();

        ManageJenkinsPage manageJenkinsPage = new ManageJenkinsPage(driver);
        Assert.assertTrue(manageJenkinsPage.manageUsersEquals("Manage users"));
        Assert.assertTrue(manageJenkinsPage.manageUsersMessageEquals("Create/delete/modify users that can log in to this Jenkins"));

        //2. После клика по ссылке, внутри которой содержится элемент dt с текстом «Manage Users», становится доступна ссылка «Create User».
        manageJenkinsPage.clickLinkManageUsers();
        ManageUsersJenkinsPage manageUsersJenkinsPage = new ManageUsersJenkinsPage(driver);
        Assert.assertTrue(manageUsersJenkinsPage.createUserEquals("Create User"));

        //3. После клика по ссылке «Create User» появляется форма с тремя полями типа text и двумя полями типа password, причём все поля должны быть пустыми.
        manageUsersJenkinsPage.clickCreateUser();



    }
}