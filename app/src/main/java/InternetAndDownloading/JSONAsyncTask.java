package InternetAndDownloading;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by maciejgalos on 28.01.15.
 */
public class JSONAsyncTask extends AsyncTask<String,Void,String> {

    private OnFinishedListener ofl;
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ofl.onFinished(s);
    }

    public JSONAsyncTask(OnFinishedListener ofl){
        this.ofl = ofl;
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        String json;
        InputStream is = null;

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }

            is.close();
            json = sb.toString();
            return json;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }



    }


//    public boolean isFinalJSONStringOK(){
//        if(finalJSONString!=null)
//            return true;
//        else
//            return false;
//    }

    public interface OnFinishedListener{
        public void onFinished(String json);

    }
}
