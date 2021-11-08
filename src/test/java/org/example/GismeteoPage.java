package org.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GismeteoPage {
    public WebDriver driver;
    public GismeteoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //elements to select city
//    @FindBy(xpath = "//input[contains(@type,'search')]")
    @FindBy(css = "input[type*='search']")
    private WebElement inputCity;

    @FindBy(xpath = "//a[contains(text(),'Изменить пункт')]")
    private WebElement changeCity;

    @FindBy(xpath = "//input[contains(@type, 'radio') and contains(@data-type, 'cityUS')]")
    private WebElement radiobuttonInputCity;

    @FindBy(xpath = "//div[contains(@class, 'founditem__center')]")
    private WebElement firstCityInDropdown;

    //elements to get info

    @FindBy(xpath = "//div[contains(@class, 'weather_current')]//div[contains(@class, 'description ')]")
    private WebElement fallout;

    @FindBy(xpath = "//span[contains(@class, 'unit_temperature_c')]")
    private WebElement temp;

    @FindBy(xpath = "//div[contains(@class, 'info_value')]/span[contains(@class, 'unit_temperature_c')]")
    private WebElement tempFeel;

    @FindBy(xpath = "//span[contains(@class, 'unit_wind_m_s')]/span[contains(@class, 'value')]")
    private WebElement windSpeed;

    @FindBy(xpath = "//span[contains(@class, 'unit_wind_m_s')]")
    private WebElement windSpeedUnitsAndDirection;

    @FindBy(xpath = "//div[contains(text(), 'Влажность')]/following-sibling::div[contains(@class, info_value)]/span[contains(@class, 'value')]")
    private WebElement wet;

    @FindBy(xpath = "//span[contains(@class, 'unit_pressure')]/span[contains(@class, 'value')]")
    private WebElement pressure;

    @FindBy(xpath = "//span[contains(@class, 'unit_pressure')]")
    private WebElement pressureUnits;

    public void selectCity(String city){
        changeCity.click();
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        try {
            radiobuttonInputCity.click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", radiobuttonInputCity);
        }
        inputCity.clear();
        inputCity.sendKeys(city);
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        firstCityInDropdown.click();
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public ForecastSnapshot getData(){
        ForecastSnapshot cur = new ForecastSnapshot();
        cur.setSource("https://www.gismeteo.ru/");
        cur.setFallout(fallout.getText());
        cur.setTemp(temp.getText());
        cur.setTempFeel(tempFeel.getText());
        cur.setWind(windSpeedUnitsAndDirection.getText());
        cur.setWet(wet.getText() + "%");
        cur.setPressure(pressureUnits.getText());
        return cur;
    }

}
