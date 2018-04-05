package wd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageUsersJenkinsPage {
    private WebDriverWait wait;
    private final WebDriver driver;

    // Подготовка элементов страницы.
    By body_locator = By.xpath("//body");

    By link_Create_User_locator = By.xpath("//div[@class='task']/a[@href='addUser'][@class='task-link']");
    By td_someuser_locator = By.xpath("//tr/td[@text='someuser']");
    By delete_someuser_locator = By.xpath("//a[@href='user/someuser/delete']");
    By yes_button_locator = By.xpath("//button[@type='button'][@id='yui-gen2-button'][@text='Yes']");
    By delete_admin_locator = By.xpath("//a[@href='user/admin/delete']");



    public ManageUsersJenkinsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);

        // Проверка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Users [Jenkins]")) ||
                (!driver.getCurrentUrl().equals("http://localhost:8080/securityRealm/"))) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    public ManageUsersJenkinsPage clickCreateUser() {
        driver.findElement(link_Create_User_locator).click();
        return this;
    }

    public boolean createUserEquals(String search_string) {
        return driver.findElement(link_Create_User_locator).getText().equals(search_string);
    }

    public boolean someuserEquals(String search_string) {
        return driver.findElement(td_someuser_locator).getText().equals(search_string);
    }

    public ManageUsersJenkinsPage clickDeleteUser() {
        driver.findElement(delete_someuser_locator).click();
        return this;
    }

    public ManageUsersJenkinsPage clickYesButton() {
        driver.findElement(yes_button_locator).click();
        return this;
    }

    public boolean deleteSomeuserHrefEquals(String search_string) {
        return driver.findElement(delete_someuser_locator).getAttribute("href").equals(search_string);
    }

    public boolean deleteAdminHrefEquals(String search_string) {
        return driver.findElement(delete_admin_locator).getAttribute("href").equals(search_string);
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
