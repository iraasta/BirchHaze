package InternetAndDownloading;

import android.content.Intent;

/**
 * Created by maciejgalos on 25.03.15.
 */
public class SmsSender {

    public static Intent send(String message){
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", "");
        smsIntent.putExtra("sms_body",message);
        return smsIntent;
    }
}