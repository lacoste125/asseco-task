package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.model.YTTile;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Task4Test extends Hook {

    @Test
    public void Task4Test() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Lista kafelkow
        List<YTTile> ytTileList = new ArrayList<>();
        String live = "live";
        By acceptCookieBtn = By.cssSelector(".style-primary:nth-of-type(2)>a");
        By startNowBtn = By.cssSelector("#action-button");
        By contentField = By.cssSelector(".style-scope ytd-rich-item-renderer");
        By filmTitle = By.id("video-title");
        By channelName = By.id("channel-name");
        By duration = By.cssSelector("span.ytd-thumbnail-overlay-time-status-renderer");

        driver.navigate().to("https://www.youtube.com/");
        click(acceptCookieBtn);
        wait.until(ExpectedConditions.presenceOfElementLocated(startNowBtn));

        List<WebElement> films = driver.findElements(contentField);
        int i = 1;
        for (WebElement film : films) {
            YTTile content = new YTTile();

            String filmName = film.findElement(filmTitle).getText();
            String filmChannelName = film.findElement(channelName).getText();
            String filmDuration;

            wait.until(ExpectedConditions.presenceOfElementLocated(duration));
            try {
                filmDuration = film.findElement(duration).getText();
            } catch (NoSuchElementException e) {
                filmDuration = live;
            }

            content.setTitle(filmName);
            content.setChannel(filmChannelName);
            content.setLength(filmDuration);

            ytTileList.add(content);
            i++;
            if (i == 13) break;
        }

        ytTileList.stream()
                .filter(content -> !content.getLength().equals(live))
                .forEach(YTTile::printResult);
    }
}