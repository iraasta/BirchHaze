package gwr.com.birchhaze;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.speech.RecognizerIntent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.graphics.Bitmap;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DataBase.City;
import DataBase.DayForecast;
import InternetAndDownloading.InternetConnectionChecker;
import InternetAndDownloading.JSONAsyncTask;
import InternetAndDownloading.JSONToDataBaseLoader;
import Thermometer.Thermometer;
import gwr.com.birchhaze.STT.SpeechToText;
import gwr.com.birchhaze.background.BackgroundManager;


public class MainActivity extends ActionBarActivity {


    ViewPager viewPager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager= (ViewPager) findViewById(R.id.pager); // Retrieve the view pager
        viewPager.setAdapter(new ViewGroupPagerAdapter((ViewGroup) findViewById(R.id.pager)));

    }

    ArrayList<DayForecast> forecasts = null;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //check speech recognition result
        if (requestCode == SpeechToText.VR_REQUEST && resultCode == RESULT_OK)
        {
            //store the returned word list as an ArrayList
            ArrayList<String> suggestedWords = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            for (int i = 0; i < suggestedWords.size(); i++) {
                Log.v(SpeechToText.LOG_TAG, suggestedWords.get(i));
            }
            final String cityName = (suggestedWords.get(0).charAt(0) + "").toUpperCase() + suggestedWords.get(0).substring(1);
            handleSpeech(suggestedWords.get(0));
            JSONToDataBaseLoader.Load(cityName.replace(" ", "%20"), this, new JSONToDataBaseLoader.OnFinishedListener() {
                @Override
                public void onFinished(City city) {

                    forecasts = city.getForecasts();

                }
            });
            ((TextView) findViewById(R.id.CityView)).setText(cityName);

        }

        //tss code here

        //call superclass method
        super.onActivityResult(requestCode, resultCode, data);
    }

    void toast(String text)
    {
        Toast.makeText(this,text,Toast.LENGTH_LONG);
    }


    public class ViewGroupPagerAdapter extends PagerAdapter {
        public ViewGroupPagerAdapter(ViewGroup viewGroup) {
            while (viewGroup.getChildCount() > 0) {
                views.add(viewGroup.getChildAt(0));
                viewGroup.removeViewAt(0);
            }
        }


        int itr = -1;
        int pos = -1;
        int del = -1;
        int position = 1;

        void getposition()
        {
                 if(del == 3) {
                     //Log.w("pozycja : ", 1 + "");
                     position = 1;
                     del = -1;
                 }
            else if(del == 4) {
                     //Log.w("pozycja : ", 2 + "");
                     position = 2;
                     del = -1;
                 }
            else if(del == 5) {
                     //Log.w("pozycja : ", 9 + "");
                     position = 3;
                     del = -1;
                 }
            else if(del == 6) {
                     //Log.w("pozycja : ", 10 + "");
                     position = 4;
                     del = -1;
                 }
            else if(itr<pos) {
                     //Log.w("pozycja : ", (pos - 1) + "");
                     position = pos - 1;
                 }
            else if(itr>pos) {
                     //Log.w("pozycja : ", (pos + 3) + "");
                     position = pos + 3;
                 }

        }

        private List<View> views = new ArrayList<View>();

        private void refresh()
        {
            viewPager.getAdapter().notifyDataSetChanged();
        }

        @Override
        public Object instantiateItem(ViewGroup parent, int position) {
            View view = views.get(position);
            ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
            lp.width = ViewPager.LayoutParams.FILL_PARENT;
            lp.height = ViewPager.LayoutParams.FILL_PARENT;
            view.setLayoutParams(lp);
            parent.addView(view);
            make(position);
            return view;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        Calendar c = Calendar.getInstance();

        void make(int position)
        {
            int[] DayID = {R.id.Day1,R.id.Day2,R.id.Day3,R.id.Day4,R.id.Day5,R.id.Day6,R.id.Day7,R.id.Day8,R.id.Day9,R.id.Day10};
            int[] TemperatureID = {R.id.Day1Temperature,R.id.Day2Temperature,R.id.Day3Temperature,R.id.Day4Temperature,R.id.Day5Temperature,R.id.Day6Temperature,R.id.Day7Temperature,R.id.Day8Temperature,R.id.Day9Temperature,R.id.Day10Temperature};
            int[] TypeID = {R.id.Day1Type,R.id.Day2Type,R.id.Day3Type,R.id.Day4Type,R.id.Day5Type,R.id.Day6Type,R.id.Day7Type,R.id.Day8Type,R.id.Day9Type,R.id.Day10Type};
            int[] WindID = {R.id.Day1Wind,R.id.Day2Wind,R.id.Day3Wind,R.id.Day4Wind,R.id.Day5Wind,R.id.Day6Wind,R.id.Day7Wind,R.id.Day8Wind,R.id.Day9Wind,R.id.Day10Wind};
            int[] TermometrID = {R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView8,R.id.imageView9,R.id.imageView10};

            String[] week = {"NULL","Niedziela","Poniedziałek","Wtorek","Środa","Czwartek","Piątek","Sobota","Niedziela","Poniedziałek","Wtorek","Środa","Czwartek","Piątek","Sobota","Niedziela","Poniedziałek","Wtorek","Środa","Czwartek","Piątek","Sobota"};
            String today = "Dzisiaj";
            String tomorrow = "Jutro";
            String celciusString ="°C";
            String windString = "m/s";

            EditText et = (EditText) findViewById(DayID[position]);

            if(position == 0)
            et.setText(today);
            else if(position == 1)
            et.setText(tomorrow);
            else
            et.setText(week[c.get(Calendar.DAY_OF_WEEK)+position]);

            TextView Temperature = (TextView) findViewById(TemperatureID[position]);
            TextView Type = (TextView) findViewById(TypeID[position]);
            TextView Wind = (TextView) findViewById(WindID[position]);
            ImageView Termometr = (ImageView) findViewById(TermometrID[position]);

            if(forecasts != null) {

                double temperature;
                temperature = new BigDecimal(forecasts.get(position).getTempCelsius()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                Temperature.setText(temperature + celciusString);
                Type.setText(forecasts.get(position).getWeather_description());
                Wind.setText(forecasts.get(position).getWind_speed()+windString);
                Thermometer t1 = new Thermometer(Termometr , getApplicationContext(), Color.BLACK , (int) temperature);
                t1.execute();

                //refresh();
            }else {
                Temperature.setText("");
                Type.setText("");
                Wind.setText("");
            }

        }

        @Override
        public void destroyItem(ViewGroup parent, int position, Object object) {
            View view = (View) object;
            parent.removeView(view);
            //del = position;
            //getposition();
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void speak(View view) {

        SpeechToText stt = new SpeechToText(this, (Button)findViewById(R.id.speech_button));
    }

    private void handleSpeech(final String result)
    {
        final TransitionDrawable transition = (TransitionDrawable) findViewById(R.id.speech_button).getBackground();
        transition.startTransition(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                BackgroundManager.loadBackground(result, MainActivity.this, new BackgroundManager.OnLoadListener() {
                    @Override
                    public void onLoaded(final Bitmap bmp) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((ImageView) findViewById(R.id.background)).setImageBitmap(bmp);
                                transition.reverseTransition(600);
                            }
                        });
                    }
                });


            }
        }).start();
    }
}
