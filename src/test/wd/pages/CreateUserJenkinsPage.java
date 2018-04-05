package wd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.Iterator;

public class CreateUserJenkinsPage {
    private WebDriverWait wait;
    private final WebDriver driver;

    // Подготовка элементов страницы.
    By body_locator = By.xpath("//body");

    By username_locator = By.id("username");
    By password_locator = By.name("password1");
    By confirm_password_locator = By.name("password2");
    By full_name_locator = By.name("fullname");
    By email_locator = By.name("email");
    By submit_button_locator = By.xpath("//button[@type='button'][@id='yui-gen2-button']");



    public CreateUserJenkinsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);

        // Проверка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Create User [Jenkins]")) ||
                (!driver.getCurrentUrl().equals("http://localhost:8080/securityRealm/addUser"))) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    // Надёжный поиск формы.
    public boolean isFormPresentForReal() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Collection<WebElement> forms = driver.findElements(By.tagName("form"));
        if (forms.isEmpty()) {
            return false;
        }

        Iterator<WebElement> i = forms.iterator();
        boolean form_found = false;
        WebElement form = null;

        while (i.hasNext()) {
            form = i.next();
            if ((form.findElements(By.xpath("//input[@type='text']")).size() == 3) &&
                    (form.findElements(By.xpath("//input[@type='password']")).size() == 2) ) {
                form_found = true;
                break;
            }
        }

        return form_found;
    }

    // Заполнение имени.
    public CreateUserJenkinsPage setName(String name) {
        driver.findElement(username_locator).clear();
        driver.findElement(username_locator).sendKeys(name);
        return this;
    }

    // Получение значения имени.
    public String getName() {
        return driver.findElement(username_locator).getAttribute("value");
    }


    // Заполнение пароля.
    public CreateUserJenkinsPage setPassword(String password) {
        driver.findElement(password_locator).clear();
        driver.findElement(password_locator).sendKeys(password);
        return this;
    }

    // Получение значения пароля.
    public String getPassword() {
        return driver.findElement(password_locator).getAttribute("value");
    }

    //Заполнение поля подтверждение пароля
    public CreateUserJenkinsPage setConfirmPassword(String password) {
        driver.findElement(confirm_password_locator).clear();
        driver.findElement(confirm_password_locator).sendKeys(password);
        return this;
    }

    //Получение значения поля подтверждение пароля
    public String getConfirmPassword() {
        return driver.findElement(confirm_password_locator).getAttribute("value");
    }

    public CreateUserJenkinsPage setFullName(String fullname) {
        driver.findElement(full_name_locator).clear();
        driver.findElement(full_name_locator).sendKeys(fullname);
        return this;
    }

    public String getFullName() { return driver.findElement(full_name_locator).getAttribute("value"); }

    public CreateUserJenkinsPage setEmail(String email) {
        driver.findElement(email_locator).clear();
        driver.findElement(email_locator).sendKeys(email);
        return this;
    }

    public String getEmail() {return driver.findElement(email_locator).getAttribute("value"); }

    public CreateUserJenkinsPage setFields(String username, String password, String confirmPassword, String fullName, String email) {
        setName(username);
        setPassword(password);
        setConfirmPassword(confirmPassword);
        setFullName(fullName);
        setEmail(email);
        return this;
    }

    public CreateUserJenkinsPage submitCreateUser() {
        driver.findElement(submit_button_locator).click();
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
