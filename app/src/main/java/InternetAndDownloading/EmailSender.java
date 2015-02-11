package InternetAndDownloading;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by maciejgalos on 11.02.15.
 */
public final class EmailSender {
    public static void SendEmail(String email_adress, String subject , String message, Activity activity){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{email_adress});
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        i.putExtra(Intent.EXTRA_TEXT   , message);
        try {
            activity.startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(activity, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

    }
}
