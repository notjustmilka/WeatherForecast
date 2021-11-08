package org.example;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainTest {
    public static WebDriver driver1;
    public static WebDriver driver2;
    public static YandexPage yandexPage;
    public static GismeteoPage gismeteoPage;

    public static void setUp (){
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        WebDriver driver1 = new ChromeDriver();
        driver1.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        yandexPage = new YandexPage(driver1);
        WebDriver driver2 = new ChromeDriver();
        driver2.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        gismeteoPage = new GismeteoPage(driver2);

        driver1.manage().window().maximize();
        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver1.get(ConfProperties.getProperty("yandexPage"));

        driver2.manage().window().maximize();
        driver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver2.get(ConfProperties.getProperty("gismeteoPage"));
    }

    @Test
    public void mainTest() {
        setUp();
        ArrayList<String> cities = new ArrayList<>();
        Scanner sc = null;
        try {
            sc = new Scanner(new File(ConfProperties.getProperty("cities")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(sc.hasNext()){
            cities.add(sc.nextLine());
        }
        for (String city : cities) {
            yandexPage.selectCity(city);
            gismeteoPage.selectCity(city);
            new FillTable(yandexPage.getData(), gismeteoPage.getData());
        }
        driver1.get(ConfProperties.getProperty("report"));
    }

    @AfterClass
    public static void tearDown() {
        driver1.quit();
        driver2.quit();
    }
}
