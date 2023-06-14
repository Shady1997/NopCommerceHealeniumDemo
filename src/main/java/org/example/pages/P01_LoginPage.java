package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Project Name    : Nop Commerce
 * Developer       : Shady Ahmed
 * Version         : 1.0.0
 * Date            : 02/15/2023
 * Time            : 7:27 PM
 * Description     :
 **/
public class P01_LoginPage {
    private final By LOGIN_LINK = By.xpath("//a[text()='Log in']");
    private final By PASSWORD_TEXT_BOX = By.id("Password");
    private final By EMAIL_TEXT_BOX = By.id("Email");
    private final By LOGIN_BUTTON = By.xpath("//button[text()='Log in']");
    private final WebDriver driver;
    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public P01_LoginPage clickLoginLink() {
        driver.findElement(LOGIN_LINK).click();
        return this;
    }
    public P01_LoginPage inputEmail(String email) {
        driver.findElement(EMAIL_TEXT_BOX).sendKeys(email);
        return this;
    }

    public P01_LoginPage inputPassword(String password) {
        driver.findElement(PASSWORD_TEXT_BOX).sendKeys(password);
        return this;
    }
    public void clickLoginButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(LOGIN_BUTTON));
    }
}
