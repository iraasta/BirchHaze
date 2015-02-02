package gwr.com.birchhaze;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
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
        final TransitionDrawable transition = (TransitionDrawable) view.getBackground();
        transition.startTransition(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                BackgroundManager.loadBackground("warszawa", MainActivity.this, new BackgroundManager.OnLoadListener() {
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
