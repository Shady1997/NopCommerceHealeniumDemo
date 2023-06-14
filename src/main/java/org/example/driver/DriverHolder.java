package org.example.driver;

import org.openqa.selenium.WebDriver;

/**
 * Project Name    : Nop Commerce
 * Developer       : Shady Ahmed
 * Version         : 1.0.0
 * Date            : 02/15/2023
 * Time            : 7:27 PM
 * Description     :
 **/

public class DriverHolder {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        DriverHolder.driver.set(driver);
    }
}
