package gwr.com.birchhaze;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import DataBase.City;
import InternetAndDownloading.InternetConnectionChecker;
import InternetAndDownloading.JSONAsyncTask;
import InternetAndDownloading.JSONToDataBaseLoader;
import gwr.com.birchhaze.STT.SpeechToText;
import gwr.com.birchhaze.background.BackgroundManager;


public class MainActivity extends ActionBarActivity {

    //ViewGroup viewGroup = getViewGroup();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager= (ViewPager) findViewById(R.id.pager); // Retrieve the view pager
        viewPager.setAdapter(new ViewGroupPagerAdapter((ViewGroup) findViewById(R.id.pager)));

    }
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
            String cityName = (suggestedWords.get(0).charAt(0) + "").toUpperCase() + suggestedWords.get(0).substring(1);
            handleSpeech(suggestedWords.get(0));
            JSONToDataBaseLoader.Load(cityName, this, new JSONToDataBaseLoader.OnFinishedListener() {
                @Override
                public void onFinished(City city) {

                    //TODO RAFAŁ

                }
            });
            ((TextView) findViewById(R.id.CityView)).setText(cityName);
        }

        //tss code here

        //call superclass method
        super.onActivityResult(requestCode, resultCode, data);
    }
    public class ViewGroupPagerAdapter extends PagerAdapter {
        public ViewGroupPagerAdapter(ViewGroup viewGroup) {
            while (viewGroup.getChildCount() > 0) {
                views.add(viewGroup.getChildAt(0));
                viewGroup.removeViewAt(0);
            }
        }
        private List<View> views = new ArrayList<View>();

        @Override
        public Object instantiateItem(ViewGroup parent, int position) {
            View view = views.get(position);
            ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
            lp.width = ViewPager.LayoutParams.FILL_PARENT;
            lp.height = ViewPager.LayoutParams.FILL_PARENT;
            view.setLayoutParams(lp);
            parent.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup parent, int position, Object object) {
            View view = (View) object;
            parent.removeView(view);
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
