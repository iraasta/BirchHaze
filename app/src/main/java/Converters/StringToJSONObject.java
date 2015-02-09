package Converters;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by maciejgalos on 30.01.15.
 */
public final class StringToJSONObject {
    public static JSONObject getJSONObjectFromString(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            return jsonObject;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
    public static JSONObject getJSONObjectFromString(String s, Context context){
        try {
            JSONObject jsonObject = new JSONObject(s);
            return jsonObject;
        }catch (JSONException e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
