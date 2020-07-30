package ru.geekbrains.java.oop;


import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

public class HamcrestNavigationTest {

    ChromeDriver chromeDriver = new ChromeDriver();

    public WebDriver driver;



    @DisplayName("Страница вебинары, вводим в поиске Java")
    @BeforeEach
    public void javatext(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        chromeDriver.get("https://geekbrains.ru/events");
        chromeDriver.findElement(By.cssSelector("[class=\"show-search-form\"] " +
                "[class=\"svg-icon icon-search \"]")).click();
        chromeDriver.findElement(By.cssSelector("[class=\"search-panel__search-field\"]"))
                .sendKeys("java");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

    }



    @Test
    public void Navigation(){

        WebElement professionsNum = chromeDriver.findElement(By.xpath("//header//span[text()='3']"));
        WebElement coursesNum = chromeDriver.findElement(By.xpath("//header//span[text()='19']"));
        WebElement eventsNum = chromeDriver.findElement(By.xpath("//header//span[text()='223']"));
        WebElement eventsSearch = chromeDriver.findElement(By.xpath("//a[@class='event__title h3 search_text' and " +
                "@href=\"/events/6\"]"));
        WebElement blogsNum = chromeDriver.findElement(By.xpath("//header//span[text()='346']"));
        WebElement forumsNum = chromeDriver.findElement(By.xpath("//header//span[text()='454']"));
        WebElement testsNum = chromeDriver.findElement(By.xpath("//header//span[text()='1']"));
        WebElement forCompany = chromeDriver.findElement(By.xpath("//div[@class='company-items']//h3/" +
                "a[contains(text(),'GeekBrains')]"));




        MatcherAssert.assertThat((Integer.parseInt(professionsNum.getText())),
                greaterThanOrEqualTo(2) );

        MatcherAssert.assertThat((Integer.parseInt(coursesNum.getText())),
                greaterThan(15) );

        MatcherAssert.assertThat((Integer.parseInt(eventsNum.getText())),
                greaterThan(180) );
        MatcherAssert.assertThat((Integer.parseInt(eventsNum.getText())),
                lessThan(300 ));

        MatcherAssert.assertThat(eventsSearch.getText(),
                equalToIgnoringCase ("Java Junior. " +
                        "Что нужно знать для успешного собеседования?"));

        MatcherAssert.assertThat((Integer.parseInt(blogsNum.getText())),
                greaterThan(300 ));

        MatcherAssert.assertThat((Integer.parseInt(forumsNum.getText())),
                greaterThan(350 ));
        MatcherAssert.assertThat((Integer.parseInt(forumsNum.getText())),
                lessThan(350 ));

        MatcherAssert.assertThat((Integer.parseInt(testsNum.getText())),
                notNullValue());

        MatcherAssert.assertThat(forCompany.getText(),
                equalToIgnoringCase ("GeekBrains"));







    }





}
