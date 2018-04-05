package wd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.Iterator;

public class DashboardJenkinsPage {
    private WebDriverWait wait;
    private final WebDriver driver;

    // Подготовка элементов страницы.
    By body_locator = By.xpath("//body");

    By link_Manage_Jenkins_locator = By.xpath("//div[@class='task']/a[@href='/manage'][@class='task-link']");
    By dt_locator = By.xpath("//div[@class='manage-option']/a[@title='Manage Users']/dl/dt");
    By dd_locator = By.xpath("//div[@class='manage-option']/a[@title='Manage Users']/dl/dd");

    By link_Manage_Users_locator = By.xpath("//div[@class='manage-option']/a[@title='Manage Users']");
    By link_Create_User_locator = By.xpath("//div[@class='task']/a[@href='addUser'][@class='task-link']");



    public DashboardJenkinsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);

        // Проверка того факта, что мы на верной странице.
        if (!driver.getTitle().equals("Dashboard [Jenkins]")) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    public DashboardJenkinsPage clickLinkManageJenkins() {
        driver.findElement(link_Manage_Jenkins_locator).click();
        return this;
    }

    // Проверка вхождения подстроки в текст страницы.
    public boolean pageTextContains(String search_string) {
        return driver.findElement(body_locator).getText().contains(search_string);
    }

    public String getErrorOnTextAbsence(String search_string) {
        if (!pageTextContains(search_string)) {
            return "No '" + search_string + "' is found inside page text!\n";
        } else {
            return "";
        }
    }
}
