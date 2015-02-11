package DataBase;

import android.app.Activity;

import java.util.ArrayList;

import InternetAndDownloading.EmailSender;
import Settings.Settings;

/**
 * Created by maciejgalos on 11.02.15.
 */
public final class SendForecastByEmail {
    private static DataBase dataBase = DataBase.getInstance();
    public static void SendForeCast(Activity activity){
        String message;
        message="City: " + dataBase.getCity().getName() +"\n\n";
        ArrayList<DayForecast> forecasts = dataBase.getCity().getForecasts();
        for(int i=0;i<forecasts.size();i++){
            message+="****************************************** \n";
            message+=forecasts.get(i).getDate_time_text()+"\n";
            message+="Weather type: "+forecasts.get(i).getWeather_type()+"\n";
            message+="Weather description: "+forecasts.get(i).getWeather_description()+"\n";
            message+="Temp: " + round(forecasts.get(i).getTempCelsius(),1)+" °C"+"\n";
            message+="Temp Max: " + round(forecasts.get(i).getTemp_maxCelsius(),1)+" °C"+"\n";
            message+="Temp Min: " + round(forecasts.get(i).getTemp_minCelsius(),1)+" °C"+"\n";
            message+="Pressure: "+ forecasts.get(i).getPressure()+" hPa"+"\n";
            message+="Wind speed: "+ forecasts.get(i).getWind_speed()+" m/s"+"\n";


        }

        EmailSender.SendEmail("",Settings.EMAIL_SUBJECT+dataBase.getCity().getName()+".",message,activity);


    }
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
