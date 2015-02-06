package Converters;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import DataBase.City;
import DataBase.DayForecast;

/**
 * Created by maciejgalos on 30.01.15.
 */
public final class JSONObjectToCity {
    public static City parseJSONObject(JSONObject obj, Context context){
        City city = new City();
        try{
            if(obj.has("city")) {
                JSONObject Jcity = obj.getJSONObject("city");
                if(Jcity.has("name")){
                    city.setName(Jcity.getString("name"));
                }
                if(Jcity.has("country")){
                    city.setCountry(Jcity.getString("country"));
                }
                if(Jcity.has("population")){
                    city.setPopulation(Jcity.getInt("population"));
                }
                if(Jcity.has("coord")){
                    JSONObject Jcoord = Jcity.getJSONObject("coord");
                    if(Jcoord.has("lon")){
                        city.setLon(Jcoord.getDouble("lon"));
                    }
                    if(Jcoord.has("lat")){
                        city.setLat(Jcoord.getDouble("lat"));
                    }
                }
            }
            if(obj.has("list")){
                JSONArray array = obj.getJSONArray("list");
                ArrayList<DayForecast> finalList = new ArrayList<DayForecast>();
                for(int i=0;i<array.length();i++){
                    DayForecast Dtmp = new DayForecast();
                    JSONObject Jtmp = array.getJSONObject(i);
                    if(Jtmp.has("dt_txt")){
                        Dtmp.setDate_time_text(Jtmp.getString("dt_txt"));
                    }
                    if(Jtmp.has("main")){
                        JSONObject main = Jtmp.getJSONObject("main");
                        if(main.has("temp")){
                            Dtmp.setTemp(main.getDouble("temp"));
                        }
                        if(main.has("temp_min")){
                            Dtmp.setTemp_min(main.getDouble("temp_min"));
                        }
                        if(main.has("temp_max")){
                            Dtmp.setTemp_max(main.getDouble("temp_max"));
                        }
                        if(main.has("pressure")){
                            Dtmp.setPressure(main.getDouble("pressure"));
                        }
                        if(main.has("sea_level")){
                            Dtmp.setSea_level(main.getDouble("sea_level"));
                        }
                        if(main.has("humidity")){
                            Dtmp.setHumidity(main.getDouble("humidity"));
                        }
                        if(main.has("temp_kf")){
                            Dtmp.setTemp_kf(main.getDouble("temp_kf"));
                        }
                    }
                    if(Jtmp.has("weather")){
                        JSONArray jsonArray = Jtmp.getJSONArray("weather");
                        JSONObject weather = jsonArray.getJSONObject(0);
                        if(weather.has("main")){
                            Dtmp.setWeather_type(weather.getString("main"));
                        }
                        if(weather.has("description")){
                            Dtmp.setWeather_description(weather.getString("description"));
                        }
                    }
                    if(Jtmp.has("wind")){
                        JSONObject wind = Jtmp.getJSONObject("wind");
                        if(wind.has("speed")){
                            Dtmp.setWind_speed(wind.getDouble("speed"));
                        }
                    }
                    if(Jtmp.has("snow")){
                        JSONObject snow = Jtmp.getJSONObject("snow");
                        if(snow.has("3h")){
                            Dtmp.setSnow_3h(snow.getDouble("3h"));
                        }
                    }

                    city.addNewForecast(Dtmp);
                }

            }
            return city;
           }
        catch (Exception e){

        }
        return  city;





    }
}
