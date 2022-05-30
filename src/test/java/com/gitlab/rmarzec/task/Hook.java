package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Hook {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() {
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.initDriver();
    }

    @AfterTest
    public void exit() {
        driver.quit();
    }

    protected void click(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
}