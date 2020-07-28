package ru.geekbrains.java.oop;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static java.lang.Thread.sleep;

public class ParametrizedSearchTest {

    ChromeDriver chromeDriver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(chromeDriver, 30);

    @AfterEach
    public void checkheaderfooter(){
        WebElement header = chromeDriver.findElementByCssSelector("[class=*\"gb-header__content\"]");
        WebElement footer = chromeDriver.findElementByCssSelector("[class=\"site-footer__content\"]");

        wait.until(ExpectedConditions.visibilityOf(header));
        wait.until(ExpectedConditions.visibilityOf(footer));
    }


    @RepeatedTest(6)
    @DisplayName("Поисковый тест. Введите ссылку на страницу, время задержки и имя страницы")
    @ParameterizedTest
    public void SuperTest(String weblink, int numofsleep, String namepage) throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        chromeDriver.get(weblink);
        sleep(numofsleep);
        Assertions.assertEquals(namepage,
                chromeDriver.findElement(By.cssSelector("[id = \"top-menu\"] h2")).getText());

    }

    @AfterEach
    public void close(){
        chromeDriver.quit();
    }




}
