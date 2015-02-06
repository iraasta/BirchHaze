package DataBase;

import Settings.Settings;

/**
 * Created by maciejgalos on 30.01.15.
 */
public class DayForecast {
    private String date_time_text,weather_type,weather_description;
    private double temp, temp_max, temp_min, pressure, sea_level, humidity, temp_kf,wind_speed,snow_3h;



    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempCelsius(){
        return temp - Settings.KELVIN_CELSIUS;
    }
    public double getTemp_maxCelsius(){
        return temp_max - Settings.KELVIN_CELSIUS;
    }
    public double getTemp_minCelsius(){
        return temp_min - Settings.KELVIN_CELSIUS;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getSea_level() {
        return sea_level;
    }

    public void setSea_level(double sea_level) {
        this.sea_level = sea_level;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemp_kf() {
        return temp_kf;
    }

    public void setTemp_kf(double temp_kf) {
        this.temp_kf = temp_kf;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public double getSnow_3h() {
        return snow_3h;
    }

    public void setSnow_3h(double snow_3h) {
        this.snow_3h = snow_3h;
    }

    public String getWeather_description() {
        return weather_description;
    }

    public void setWeather_description(String weather_description) {
        this.weather_description = weather_description;
    }

    public String getWeather_type() {
        return weather_type;
    }

    public void setWeather_type(String weather_type) {
        this.weather_type = weather_type;
    }

    public String getDate_time_text() {
        return date_time_text;
    }

    public void setDate_time_text(String date_time_text) {
        this.date_time_text = date_time_text;
    }
}
