package wd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageJenkinsPage {
    private WebDriverWait wait;
    private final WebDriver driver;

    // Подготовка элементов страницы.
    By body_locator = By.xpath("//body");

    By dt_locator = By.xpath("//div[@class='manage-option']/a[@title='Manage Users']/dl/dt");
    By dd_locator = By.xpath("//div[@class='manage-option']/a[@title='Manage Users']/dl/dd");

    By link_Manage_Users_locator = By.xpath("//div[@class='manage-option']/a[@title='Manage Users']");


    public ManageJenkinsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);

        // Проверка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Manage Jenkins [Jenkins]")) ||
                (!driver.getCurrentUrl().equals("http://localhost:8080/manage"))) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    public boolean manageUsersEquals(String search_string) {
        return driver.findElement(dt_locator).getText().equals(search_string);
    }

    public boolean manageUsersMessageEquals(String search_string) {
        return driver.findElement(dd_locator).getText().equals(search_string);
    }

    public ManageJenkinsPage clickLinkManageUsers() {
        driver.findElement(link_Manage_Users_locator).click();
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
