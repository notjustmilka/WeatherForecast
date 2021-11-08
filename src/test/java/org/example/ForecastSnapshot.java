package org.example;

public class ForecastSnapshot {
    private String source;
    private String fallout;
    private String temp;
    private String tempFeel;
    private String wind;
    private String wet;
    private String pressure;
    String [] params = new String[] {"осадки", "температура", "ощущается", "ветер", "влажность", "давление"};

    public String[] getParamsNamesList(){
        return params;
    }

    public String[] getParamsValuesList(){
        return new String[]{fallout, temp, tempFeel, wind, wet, pressure};
    }

    public String getSource() { return source; }

    public void setSource(String source) { this.source = source; }

    public String getFallout() {
        return fallout;
    }

    public void setFallout(String fallout) {
        this.fallout = fallout;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTempFeel() {
        return tempFeel;
    }

    public void setTempFeel(String tempFeel) {
        this.tempFeel = tempFeel;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) { this.wind = wind; }

    public String getWet() {
        return wet;
    }

    public void setWet(String wet) {
        this.wet = wet;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
