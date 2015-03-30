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




    public void sort(){
        String tmp="";
        ArrayList<DayForecast> sortedForecasts = new ArrayList<DayForecast>();

        ArrayList<DayForecast> qwe = forecasts;
        forecasts = new ArrayList<DayForecast>();


        if(qwe.size()>0){
            forecasts.add(qwe.get(0));
            for(int i=1 ; i<qwe.size() ; i++){
                if((qwe.get(i).getDate_time_text().toCharArray())[11]=='1')
                    forecasts.add(qwe.get(i));
            }



            sortedForecasts.add(forecasts.get(0));
            tmp = forecasts.get(0).getDate_time_text();
            for(int i=1;i<forecasts.size();i++){
                if(((tmp.toCharArray())[9]!=(forecasts.get(i).getDate_time_text().toCharArray())[9]))
                        sortedForecasts.add(forecasts.get(i));

                tmp = forecasts.get(i).getDate_time_text();
            }
        }

        forecasts = sortedForecasts;

    }

    public City(){
        forecasts = new ArrayList<DayForecast>();

    }

    public ArrayList<DayForecast> getForecasts() {
        return forecasts;
    }

    public City(String name, double lon , double lat, String country , int population){
        forecasts = new ArrayList<DayForecast>();
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.country = country;
        this.population = population;
    }

    public void sort(){
        String tmp="";
        ArrayList<DayForecast> sortedForecasts = new ArrayList<DayForecast>();

        ArrayList<DayForecast> qwe = forecasts;
        forecasts = new ArrayList<DayForecast>();


        if(qwe.size()>0){
            forecasts.add(qwe.get(0));
            for(int i=1 ; i<qwe.size() ; i++){
                if((qwe.get(i).getDate_time_text().toCharArray())[11]=='1')
                    forecasts.add(qwe.get(i));
            }



            sortedForecasts.add(forecasts.get(0));
            tmp = forecasts.get(0).getDate_time_text();
            for(int i=1;i<forecasts.size();i++){
                if(((tmp.toCharArray())[9]!=(forecasts.get(i).getDate_time_text().toCharArray())[9]))
                    sortedForecasts.add(forecasts.get(i));

                tmp = forecasts.get(i).getDate_time_text();
            }
        }

        forecasts = sortedForecasts;

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
