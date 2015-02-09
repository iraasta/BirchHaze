package InternetAndDownloading;

import android.app.ProgressDialog;
import android.content.Context;

import Converters.JSONObjectToCity;
import Converters.StringToJSONObject;
import DataBase.DataBase;

/**
 * Created by maciejgalos on 28.01.15.
 */
public final class LoadJSONStringToDataBase {
    private static DataBase dataBase = DataBase.getInstance();
    public static void Load(String url, final Context context){
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        JSONAsyncTask task = new JSONAsyncTask(new JSONAsyncTask.OnFinishedListener() {
            @Override
            public void onFinished(String json) {
                dataBase.setJSONString(json);
                dataBase.setCurrentCity(JSONObjectToCity.parseJSONObject(StringToJSONObject.getJSONObjectFromString(json,context),context));
                progressDialog.dismiss();
            }
        });
        task.execute(url);
    }
}
