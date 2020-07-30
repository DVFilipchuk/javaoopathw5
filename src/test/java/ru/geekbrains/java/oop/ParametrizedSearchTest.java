package ru.geekbrains.java.oop;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.params.provider.Arguments;

import org.junit.jupiter.params.provider.MethodSource;



import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;



public class ParametrizedSearchTest {

    ChromeDriver chromeDriver = new ChromeDriver();
    public WebDriver driver;
    WebDriverWait wait = new WebDriverWait(chromeDriver, 30);

    @AfterEach
    public void checkheaderfooter(){
        WebElement header = chromeDriver.findElementByCssSelector("[class=*\"gb-header__content\"]");
        WebElement footer = chromeDriver.findElementByCssSelector("[class=\"site-footer__content\"]");

        wait.until(ExpectedConditions.visibilityOf(header));
        wait.until(ExpectedConditions.visibilityOf(footer));
    }

    @BeforeEach
    public void beforetest(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
    }



    @DisplayName("Поисковый тест. Введите ссылку на страницу, время задержки и имя страницы")
    @ParameterizedTest
    @MethodSource("parameters")
    public void SuperTest(String weblink, String namepage){

        chromeDriver.get(weblink);
        Assertions.assertEquals(namepage,
                chromeDriver.findElement(By.cssSelector("[id = \"top-menu\"] h2")).getText());
    }

    public static Stream<Arguments> parameters(){
        return Stream.of(
                Arguments.of("https://geekbrains.ru/events", "Вебинары"),
                Arguments.of("https://geekbrains.ru/courses", "Курсы"),
                Arguments.of("https://geekbrains.ru/topics", "Блоги"),
                Arguments.of("https://geekbrains.ru/career", "Карьера"),
                Arguments.of("https://geekbrains.ru/events", "Вебинары")); }






    @AfterEach
    public void close(){
        chromeDriver.quit();
    }




}
