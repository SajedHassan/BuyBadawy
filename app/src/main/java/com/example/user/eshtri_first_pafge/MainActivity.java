package com.example.user.eshtri_first_pafge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;


public class MainActivity extends AppCompatActivity {

    static int activeUser = -1;


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,ChoosingLang.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent caller = getIntent();

        //TODO do not forget to get that btngam back to -1 after logging out
        if (activeUser == -1) {
            try {
                activeUser = caller.getIntExtra("id", -1);
            } catch (Exception e) {
                //TODO
            }
        }
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.main_activity_slider);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);

        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    Toast.makeText(getBaseContext(),
                            "My products", Toast.LENGTH_SHORT).show();

                }
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

        //toolbar = (Toolbar) findViewById(R.id.toolbar);





    }


}
