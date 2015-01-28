package JSON;

import android.app.ProgressDialog;
import android.content.Context;

import DataBase.DataBase;

/**
 * Created by maciejgalos on 28.01.15.
 */
public final class LoadJSONStringToDataBase {
    private static DataBase dataBase = DataBase.getInstance();
    public static void Load(String url,Context context){
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        JSONAsyncTask task = new JSONAsyncTask(new JSONAsyncTask.OnFinishedListener() {
            @Override
            public void onFinished(String json) {
                dataBase.setJSONString(json);
                progressDialog.dismiss();
            }
        });
        task.execute(url);
    }
}
