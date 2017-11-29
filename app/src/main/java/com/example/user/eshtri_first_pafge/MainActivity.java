package com.example.user.eshtri_first_pafge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    static int activeUser = -1;

    @Override
    public void onBackPressed() {
        final Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(homeIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Constants.closeAppRequested) {
            this.finish();
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        final Intent caller = this.getIntent();
        if (MainActivity.activeUser == -1) {
            try {
                MainActivity.activeUser = caller.getIntExtra("id", -1);
            } catch (final RuntimeException ignored) {
                // TODO
            }
        }
        // Set the content of the activity to use the activity_main.xml layout
        // file
        this.setContentView(R.layout.main_activity_slider);

        // Get the ViewPager and set it's PagerAdapter so that it can display
        // items
        final ViewPager viewPager = (ViewPager) this.findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(this.getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        final PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) this.findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);

        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(final int position) {
                if (position == 1) {
                    Toast.makeText(MainActivity.this.getBaseContext(), "My products", Toast.LENGTH_SHORT).show();

                } else if (position == 0) {
                    final MainFragment temp = new MainFragment();
                    temp.backAfterDeletion(MainActivity.this);
                }
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(final int position,
                                       final float positionOffset, final int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(final int state) {
                // Code goes here
            }
        });

        // toolbar = (Toolbar) findViewById(R.id.toolbar);

        final ImageButton logoutBtn = (ImageButton) this.findViewById(R.id.log_out_image);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final SharedPreferences sharedPref =
                        MainActivity.this.getBaseContext().getSharedPreferences("users", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = sharedPref.edit();

                editor.remove("id");
                editor.remove("name");
                editor.remove("phone");

                editor.apply();
                MainActivity.activeUser = -1;
                final Intent intent = new Intent(MainActivity.this.getBaseContext(), FirstPage.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }

    }

}
