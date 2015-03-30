package Converters;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import DataBase.City;
import DataBase.DayForecast;

/**
 * Created by maciejgalos on 30.01.15.
 */
public final class JSONObjectToCity {

    public static City parseJSONObject(JSONObject obj,Context context){
        City city = new City();
        try{
            if(obj.has("city")){
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
            }if(obj.has("list")) {
                JSONArray array = obj.getJSONArray("list");
                ArrayList<DayForecast> finalList = new ArrayList<DayForecast>();
                for(int i=0;i<array.length();i++){

                    DayForecast Dtmp = new DayForecast();
                    JSONObject Jtmp = array.getJSONObject(i);
                    if(Jtmp.has("temp")){
                        JSONObject temp = Jtmp.getJSONObject("temp");
                        if(temp.has("day")){
                            Dtmp.setTemp(temp.getDouble("day"));
                        }
                        if(temp.has("min")){
                            Dtmp.setTemp_min(temp.getDouble("min"));
                        }
                        if(temp.has("max")){
                            Dtmp.setTemp_max(temp.getDouble("max"));
                        }
                    }
                    if(Jtmp.has("pressure")){
                        Dtmp.setPressure(Jtmp.getDouble("pressure"));
                    }
                    if(Jtmp.has("humidity")){
                        Dtmp.setHumidity(Jtmp.getDouble("humidity"));
                    }
                    if(Jtmp.has("speed")){
                        Dtmp.setWind_speed(Jtmp.getDouble("speed"));
                    }
                    if(Jtmp.has("weather")){
                        JSONArray ja = Jtmp.getJSONArray("weather");
                        JSONObject jt = ja.getJSONObject(0);
                        if(jt.has("main")){
                            Dtmp.setWeather_type(jt.getString("main"));
                        }
                        if(jt.has("description")){
                            Dtmp.setWeather_description(jt.getString("description"));
                        }
                    }
                    if(Jtmp.has("dt")){
                        Date date = new Date(Long.valueOf(Jtmp.getString("dt"))*1000L);
                        Dtmp.setDate_time_text(date.toString());
                    }
                    city.addNewForecast(Dtmp);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return city;
    }


//    public static City parseJSONObject(JSONObject obj, Context context){
//        City city = new City();
//        try{
//            if(obj.has("city")) {
//                JSONObject Jcity = obj.getJSONObject("city");
//                if(Jcity.has("name")){
//                    city.setName(Jcity.getString("name"));
//                }
//                if(Jcity.has("country")){
//                    city.setCountry(Jcity.getString("country"));
//                }
//                if(Jcity.has("population")){
//                    city.setPopulation(Jcity.getInt("population"));
//                }
//                if(Jcity.has("coord")){
//                    JSONObject Jcoord = Jcity.getJSONObject("coord");
//                    if(Jcoord.has("lon")){
//                        city.setLon(Jcoord.getDouble("lon"));
//                    }
//                    if(Jcoord.has("lat")){
//                        city.setLat(Jcoord.getDouble("lat"));
//                    }
//                }
//            }
//            if(obj.has("list")){
//                JSONArray array = obj.getJSONArray("list");
//                ArrayList<DayForecast> finalList = new ArrayList<DayForecast>();
//                for(int i=0;i<array.length();i++){
//                    DayForecast Dtmp = new DayForecast();
//                    JSONObject Jtmp = array.getJSONObject(i);
//                    if(Jtmp.has("dt_txt")){
//                        Dtmp.setDate_time_text(Jtmp.getString("dt_txt"));
//                    }
//
//
////                        if(Jtmp.has("temp")){
////                            Dtmp.setTemp(Jtmp.getDouble("temp"));
////                        }
////                        if(Jtmp.has("temp_min")){
////                            Dtmp.setTemp_min(Jtmp.getDouble("temp_min"));
////                        }
////                        if(Jtmp.has("temp_max")){
////                            Dtmp.setTemp_max(Jtmp.getDouble("temp_max"));
////                        }
////                        if(Jtmp.has("pressure")){
////                            Dtmp.setPressure(Jtmp.getDouble("pressure"));
////                        }
////                        if(Jtmp.has("sea_level")){
////                            Dtmp.setSea_level(Jtmp.getDouble("sea_level"));
////                        }
////                        if(Jtmp.has("humidity")){
////                            Dtmp.setHumidity(Jtmp.getDouble("humidity"));
////                        }
////                        if(Jtmp.has("temp_kf")){
////                            Dtmp.setTemp_kf(Jtmp.getDouble("temp_kf"));
////                        }
//
//                    if(Jtmp.has("weather")){
//                        JSONArray jsonArray = Jtmp.getJSONArray("weather");
//                        JSONObject weather = jsonArray.getJSONObject(0);
//                        if(weather.has("main")){
//                            Dtmp.setWeather_type(weather.getString("main"));
//                        }
//                        if(weather.has("description")){
//                            Dtmp.setWeather_description(weather.getString("description"));
//                        }
//                    }
//                    if(Jtmp.has("wind")){
//                        JSONObject wind = Jtmp.getJSONObject("wind");
//                        if(wind.has("speed")){
//                            Dtmp.setWind_speed(wind.getDouble("speed"));
//                        }
//                    }
//                    if(Jtmp.has("snow")){
//                        JSONObject snow = Jtmp.getJSONObject("snow");
//                        if(snow.has("3h")){
//                            Dtmp.setSnow_3h(snow.getDouble("3h"));
//                        }
//                    }
//
//                    city.addNewForecast(Dtmp);
//                }
//
//            }
//            return city;
//           }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return  city;
//
//
//
//
//
//    }
}
