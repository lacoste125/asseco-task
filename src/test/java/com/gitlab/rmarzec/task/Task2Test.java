package com.gitlab.rmarzec.task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class Task2Test extends Hook {

    @Test
    public void Task2Test() {
        By languageLink = By.cssSelector(".interlanguage-link>a");

        driver.get("https://pl.wikipedia.org/wiki/Wiki");

        List<WebElement> languages = driver.findElements(languageLink);
        for (WebElement language : languages) {
            String lang = language.getText();

            if (lang.equals("English")) {
                String englishHref = language.getAttribute("href");
                lang += " " + englishHref;
            }

            System.out.println(lang);
        }
    }
}