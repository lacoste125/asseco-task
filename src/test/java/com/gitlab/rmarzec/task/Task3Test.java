package com.gitlab.rmarzec.task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Task3Test extends Hook {

    @Test
    public void Task3Test() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        String expectedURL = "https://www.w3schools.com/tags/tag_select.asp";
        By acceptGoogleCookieBtn = By.cssSelector("button#L2AGLb");
        By searchTf = By.name("q");
        By supriseMeBtn = By.cssSelector("li#YMXe+div input[name='btnI']");
        By acceptW3CookieBtn = By.id("accept-choices");
        By firstTryBtn = By.cssSelector("a[href$='select']");
        By resultFrame = By.name("iframeResult");
        By resultHeader = By.cssSelector("h1");
        By carsDropdown = By.id("cars");

        driver.get("https://www.google.com/");

        String mainWindow = driver.getWindowHandle();
        String slaveWindow;

        click(acceptGoogleCookieBtn);
        driver.findElement(searchTf).sendKeys("HTML select tag - W3Schools");
        click(supriseMeBtn);

        String actualUrl = driver.getCurrentUrl();
        if (!actualUrl.equals(expectedURL)) {
            System.out.println("Actual url:  " + actualUrl);
            driver.navigate().to(expectedURL);
        }

        click(acceptW3CookieBtn);
        click(firstTryBtn);

        Set<String> windowsList = driver.getWindowHandles();
        windowsList.remove(mainWindow);
        slaveWindow = windowsList.iterator().next();
        driver.switchTo().window(slaveWindow);

        WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(resultFrame));
        driver.switchTo().frame(frame);
        String header = driver.findElement(resultHeader).getText();
        System.out.println(header);

        WebElement dropdown = driver.findElement(carsDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText("Opel");
        WebElement opel = select.getFirstSelectedOption();

        String text = opel.getText();
        String value = opel.getAttribute("value");
        System.out.println("(" + text + ", " + value + ")");
    }
}