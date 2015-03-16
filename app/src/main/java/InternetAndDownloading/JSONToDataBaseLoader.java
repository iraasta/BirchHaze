package InternetAndDownloading;

import android.app.ProgressDialog;
import android.content.Context;

import Converters.JSONObjectToCity;
import Converters.StringToJSONObject;
import DataBase.City;
import DataBase.DataBase;
import Settings.Settings;
/**
 * Created by maciejgalos on 28.01.15.
 */
public class JSONToDataBaseLoader {
    private OnFinishedListener ofl;
    private static DataBase dataBase = DataBase.getInstance();

    public JSONToDataBaseLoader(OnFinishedListener ofl){
        this.ofl = ofl;
    }
    public static void Load(String city, final Context context, final OnFinishedListener ofl){
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        JSONAsyncTask task = new JSONAsyncTask(new JSONAsyncTask.OnFinishedListener() {
            @Override
            public void onFinished(String json) {


                dataBase.setJSONString(json);

                City city = dataBase.setCurrentCity(JSONObjectToCity.parseJSONObject(StringToJSONObject.getJSONObjectFromString(json, context), context));
                progressDialog.dismiss();

                ofl.onFinished(city);

            }
        });
        String url = Settings.WEATHER_API_LINK+city;
        task.execute(url);
    }

    public interface OnFinishedListener{
        public void onFinished(City city);

    }


}
