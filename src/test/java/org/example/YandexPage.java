package org.example;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexPage {
    public WebDriver driver;
    public YandexPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //elements to select city
//    @FindBy(xpath = "//input[contains(@placeholder,'Город или район')]")
    @FindBy(css = "input[placeholder*='Город или район']")
    private WebElement inputCity;

    @FindBy(xpath = "//div[contains(concat('⦿', @class, '⦿'), '⦿mini-suggest__popup mini-suggest__popup_svg_yes⦿')]//span[contains(concat('⦿', @class, '⦿'), '⦿mini-suggest__item-title⦿')]")
    private WebElement firstCityInDropdown;

    //elements to get info

    @FindBy(xpath = "//div[contains(concat('⦿', @class, '⦿'), '⦿link__condition day-anchor i-bem⦿')]")
    private WebElement fallout;

    @FindBy(xpath = "//*[.='Текущая температура']/following-sibling::span[contains(@class, 'temp__value_with-unit')]")
    private WebElement temp;

    @FindBy(xpath = "//div[contains(concat('⦿', @class, '⦿'), '⦿term term_orient_h fact__feels-like⦿')]//span[contains(concat('⦿', @class, '⦿'), '⦿temp__value temp__value_with-unit⦿')]")
    private WebElement tempFeel;

    @FindBy(xpath = "//span[contains(concat('⦿', @class, '⦿'), '⦿wind-speed⦿')]")
    private WebElement windSpeed;

    @FindBy(xpath = "//span[contains(concat('⦿', @class, '⦿'), '⦿fact__unit⦿')]")
    private WebElement windSpeedUnits;

    @FindBy(xpath = "//span[contains(concat('⦿', @class, '⦿'), '⦿wind-speed⦿')]")
    private WebElement windDirection;

    @FindBy(xpath = "//div[contains(concat('⦿', @class, '⦿'), '⦿term__value⦿') and contains(text(),'%')]")
    private WebElement wet;

    @FindBy(xpath = "//div[contains(@aria-label, 'Давление')]")
    private WebElement pressure;

    @FindBy(xpath = "//div[contains(@aria-label, 'Давление')]/span[contains(@class, 'fact__unit')]")
    private WebElement pressureUnits;

    public void selectCity(String city){
        inputCity.clear();
        inputCity.sendKeys(city);
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        try {
            driver.manage().timeouts().wait(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        inputCity.sendKeys(Keys.DOWN, Keys.RETURN);
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public ForecastSnapshot getData(){
        ForecastSnapshot cur = new ForecastSnapshot();
        cur.setSource("https://yandex.ru/pogoda");
        cur.setFallout(fallout.getText());
        cur.setTemp(temp.getText());
        cur.setTempFeel(tempFeel.getText());
        cur.setWind(windSpeed.getText() + " " + windSpeedUnits.getText());
        cur.setWet(wet.getText());
        cur.setPressure(pressure.getText() + pressureUnits.getText());
        return cur;
    }


}
