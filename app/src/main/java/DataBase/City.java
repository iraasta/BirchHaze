package DataBase;

import java.util.ArrayList;

/**
 * Created by maciejgalos on 30.01.15.
 */
public class City {
    private String name;
    private double lon,lat;
    private String country;
    private int population;
    private ArrayList<DayForecast> forecasts;

    public City(){
        forecasts = new ArrayList<DayForecast>();
    }

    public City(String name, double lon , double lat, String country , int population){
        forecasts = new ArrayList<DayForecast>();
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.country = country;
        this.population = population;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setLon(double lon){
        this.lon = lon;
    }
    public void setLat(double lat){
        this.lat = lat;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public void setPopulation(int population){
        this.population = population;
    }
    public void addNewForecast(DayForecast dayForecast){
        forecasts.add(dayForecast);
    }

    public ArrayList<DayForecast> getForecasts(){
        return forecasts;
    }
    public String getName(){
        return name;
    }
    public String getCountry(){
        return country;
    }
    public double getLon(){
        return lon;
    }
    public double getLat(){
        return lat;
    }
    public int getPopulation(){
        return population;
    }
}
