package gwr.com.birchhaze.background;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by root on 28.01.15.
 */
public class BitmapLoader {
    public static Bitmap loadBitmap(String url) {
        URL newurl = null;
        try {
            newurl = new URL(url);
            return BitmapFactory.decodeStream(newurl.openConnection().getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
