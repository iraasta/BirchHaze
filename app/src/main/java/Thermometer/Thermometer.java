package Thermometer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import gwr.com.birchhaze.R;

/**
 * Created by maciejgalos on 16.03.15.
 */
public class Thermometer extends AsyncTask<Integer,Void,Integer> {
    private ImageView imageView;
    private Context context;
    private Bitmap thermometerBitampBackground;
    private Canvas canvas;
    private Paint paint;
    private int color;
    private int temp;
    private double height;


    public Thermometer(ImageView imageView,Context context,int color,int temp){
        this.imageView = imageView;
        this.context = context;
        this.color = color;
        this.temp = temp;
        this.thermometerBitampBackground = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.term1), 300, 600, false);
        this.height = 407-(temp+4)* (5 * thermometerBitampBackground.getHeight() / 600);


        canvas = new Canvas(thermometerBitampBackground);
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(0);
        canvas.drawPoint(10, 10, paint);
        //canvas.drawRect(145, (int)height, 158, 451, paint);
        imageView.setImageBitmap(thermometerBitampBackground);
    }



    @Override
    protected Integer doInBackground(Integer... integers) {
        int tmp=451;
        double counter = 1;
        int h=(int)height;

        int max = 5*55;

        do{
            imageView.postInvalidate();
            double percentage = (double)(tmp - h) / h;
            Log.v("test", percentage + "");

            paint.setColor(Color.rgb((int) ((1.0 - percentage) * 256), 0, (int) (percentage * 256)));
            canvas.drawRect(145, tmp, 158, 451, paint);
            try {
                Thread.sleep((int)counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tmp--;
            if(counter<11.5)
                counter+=0.04;
            else
                counter+=2;





        }while(tmp>height);

        return null;

    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

    }



}
