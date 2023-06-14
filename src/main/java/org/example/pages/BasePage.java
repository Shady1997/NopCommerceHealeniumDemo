package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class BasePage {
    // TODO: Scroll to view Element
    public static void scrollToViewElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
    // TODO: hover over web element
    public void hoverWebElement(WebDriver driver, WebElement element) {
        // Creating object of an Actions class
        Actions action = new Actions(driver);
        // Performing the mouse hover action on the target element.
        action.moveToElement(element).perform();
    }
    // TODO: Scroll with specific amount over web page
    public static void scrollWithSpecificSize(WebDriver driver, int sizeX, int sizeY) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsCommand = String.format("window.scrollBy('%s','%s')", sizeX, sizeY);
        js.executeScript(jsCommand, "");
    }

    // TODO: Execute javascript script to
    public static void executeJavaScript(String jsCommand, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(jsCommand);
    }

    // TODO: Assertion to object exists after specific action with getPageSource() method
    public static void assertToObjectExistWithGetText(WebDriver driver, String expected) {
        Assert.assertEquals(driver.getPageSource().contains(expected), true);
    }
    // TODO: handle wait
    public static void waitForPageLoad(WebDriver driver) {
        System.out.println("Waiting for ready state complete");
        (new WebDriverWait(driver, 90)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                String readyState = js.executeScript("return document.readyState").toString();
                System.out.println("Ready State: " + readyState);
                return (Boolean) readyState.equals("complete");
            }
        });
    }
    // TODO: explicit wait until web element visibility
    public static void explicitWait(WebDriver driver, String webElementXPATH) {
        // explicit wait - to wait for the compose button to be click-able
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(webElementXPATH)));
    }

}
