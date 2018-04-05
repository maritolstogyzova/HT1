package wd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.Iterator;

public class LoginPage {
    private WebDriverWait wait;
    private final WebDriver driver;

    // Подготовка элементов страницы.
    By body_locator = By.name("body");
    By username_locator = By.id("j_username");
    By password_locator = By.name("j_password");
    By login_button_locator = By.xpath("//button[@type='button'][@id='yui-gen1-button']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);

        // Проверка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Jenkins")) ||
                (!driver.getCurrentUrl().contains("localhost:8080"))) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    // Заполнение имени.
    public LoginPage setName(String name) {
        driver.findElement(username_locator).clear();
        driver.findElement(username_locator).sendKeys(name);
        return this;
    }

    // Получение значения имени.
    public String getName() {
        return driver.findElement(username_locator).getAttribute("value");
    }


    // Заполнение пароля.
    public LoginPage setPassword(String password) {
        driver.findElement(password_locator).clear();
        driver.findElement(password_locator).sendKeys(password);
        return this;
    }

    // Получение значения пароля.
    public String getPassword() {
        return driver.findElement(password_locator).getAttribute("value");
    }

    // Отправка данных из формы.
    public LoginPage submitLoginForm() {
        driver.findElement(login_button_locator).click();
        return this;
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
            if ((form.findElement(By.id("j_username")).getAttribute("type").equals("text")) &&
                    (form.findElement(By.name("j_password")).getAttribute("type").equalsIgnoreCase("text")) &&
                    (form.findElement(By.xpath("//input[@text='log in']")).getAttribute("id").equalsIgnoreCase("yui-gen1-button"))) {
                form_found = true;
                break;
            }
        }

        return form_found;
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
