package com.xuanthuan.comic.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.xuanthuan.comic.comment.DBLibrary;
import com.xuanthuan.comic.fragment.Fragment_Home;
import com.xuanthuan.comic.fragment.Fragment_Library;
import com.xuanthuan.comic.fragment.Fragment_Search;
import com.xuanthuan.comic.fragment.Fragment_Setting;
import com.xuanthuan.comic.R;
import com.xuanthuan.comic.adapter.ViewPagerAdapterBottomNavi;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    public static BottomNavigationView bottomNavigationView;
    public static DBLibrary dbLibrary;
    public Fragment_Library fragment_library;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment_library=new Fragment_Library();
        init();
        setAdapterBottom();
        viewPager.setCurrentItem(0);
        setbottomViewPager();
        viewPager.setOffscreenPageLimit(8);

        dbLibrary = new DBLibrary(this, "LibraryDB.sqlite", null, 1);
        dbLibrary.queryDATA("CREATE TABLE IF NOT EXISTS LIBRARY(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR, IDtruyen VARCHAR, IMG VARCHAR)");
        String pathdb = getDatabasePath("LibraryDB.sqlite").getPath();
        Log.d("duongdan", "onCreate: " + pathdb);
    }



    private void setbottomViewPager() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.library:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.search:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.setting:
                        viewPager.setCurrentItem(3);
                        break;
                }

                return true;
            }
        });
        viewPager.addOnPageChangeListener (new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                bottomNavigationView.setSelectedItemId(position);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.library).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.search).setChecked(true);
                        break;

                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.setting).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setAdapterBottom() {
        ViewPagerAdapterBottomNavi adapterBottomNavi = new ViewPagerAdapterBottomNavi(getSupportFragmentManager(), 0);
        adapterBottomNavi.addFragment(new Fragment_Home());
        adapterBottomNavi.addFragment(fragment_library);
        adapterBottomNavi.addFragment(new Fragment_Search());
        adapterBottomNavi.addFragment(new Fragment_Setting());
        viewPager.setAdapter(adapterBottomNavi);
    }

    private void init() {
        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottomnavi);
    }


}