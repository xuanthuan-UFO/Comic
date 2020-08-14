package com.xuanthuan.comic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.xuanthuan.comic.adapter.AdaptervPagerActivity2;
import com.xuanthuan.comic.fragment.Fragment_Chapter;
import com.xuanthuan.comic.fragment.Fragment_FirstChapter;
import com.xuanthuan.comic.fragment.Fragment_Library;
import com.xuanthuan.comic.fragment.Fragment_Summary;
import com.xuanthuan.comic.R;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity2 extends AppCompatActivity {
String nameManga, des,img, idTruyen;
TextView tvName, tvDes;
ImageView imgDetail;
ListView lv;
CheckBox cb;
RadioButton btnChapter, btn2, btn3;
RadioGroup radioGroup;
ViewPager viewPager;
Toolbar toolbar;
Fragment_Chapter fragment1;
Fragment_Summary fragment2;
Fragment_Library fragment3;
Cursor cursor;

    String idtruyendatabase,tendatabase,urlimgdatabase;
    int iddatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        init();
        customtoolbar();
        nhanData(); // nhận data từ mh1
        nhan(); //mhsearch
        nhanGenres(); //mh genres
        nhanLibrary(); //mh library
        luuthuoctinh(); // set txtten txtmota img
        addFragment(); // thêm fragment cho viewpager
        sentdata();// gửi tên truyện đến fragment chapter
        CustomRadioGroup();

        kiemtracheckbox();

        viewPager.setOffscreenPageLimit(3);

        //saveSQL();


        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    MainActivity.dbLibrary.insertDATA(nameManga, idTruyen, img);
                    Log.d("sql", "saveSQL:  OKkkkkkkk" + nameManga + idTruyen + img);
                } else {
                    ktraTontai();
                }
            }
        });

    }

    private void ktraTontai() {
        cursor = MainActivity.dbLibrary.getDATA("SELECT * FROM LIBRARY");
        while (cursor.moveToNext()){
            String kt = cursor.getString(1);
            if (nameManga.equals(kt)) {
                MainActivity.dbLibrary.queryDATA("DELETE FROM LIBRARY WHERE Name = '"+ nameManga + "'");
                Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setcheckbox() {
        cursor = MainActivity.dbLibrary.getDATA("SELECT * FROM LIBRARY");
        while (cursor.moveToNext()){
            String ktcheckbox = cursor.getString(1);
            if (nameManga.equals(ktcheckbox)) {
                cb.setChecked(true);
                break;
            }
        }

    }

    private void kiemtracheckbox() {

    }

    private void customtoolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //toolbar.setNavigationIcon(R.drawable.back);
    }


    private void addFragment() {
         fragment1 = new Fragment_Chapter();
        fragment2 = new Fragment_Summary();
        Fragment_FirstChapter fragment_firstChapter = new Fragment_FirstChapter();

        AdaptervPagerActivity2 pagerActivity2 = new AdaptervPagerActivity2(getSupportFragmentManager(), 0);
        pagerActivity2.addFragment(fragment1);
        pagerActivity2.addFragment(fragment2);
        pagerActivity2.addFragment(fragment_firstChapter);

        viewPager.setAdapter(pagerActivity2);
    }

    private void CustomRadioGroup() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetric = new DisplayMetrics();
        display.getMetrics(displayMetric);
        float rong = displayMetric.widthPixels / 3;

        btnChapter.setLayoutParams(new RadioGroup.LayoutParams((int) rong, RadioGroup.LayoutParams.MATCH_PARENT));
        btn2.setLayoutParams(new RadioGroup.LayoutParams((int) rong, RadioGroup.LayoutParams.MATCH_PARENT));
        btn3.setLayoutParams(new RadioGroup.LayoutParams((int) rong, RadioGroup.LayoutParams.MATCH_PARENT));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.btn1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.btn2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.btn3:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radioGroup.check(radioGroup.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    private void nhanGenres(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("datagenres");
        if (bundle != null) {
            nameManga = bundle.getString("name");
            img = bundle.getString("img");
            idTruyen = bundle.getString("id");
            toolbar.setTitle(nameManga);
            setcheckbox();
        }

    }

    private void nhanData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if (bundle != null){
            nameManga = bundle.getString("ten");
            img = bundle.getString("hinh");
            idTruyen = bundle.getString("id");
            toolbar.setTitle(bundle.getString("ten"));
            setcheckbox();;
        }

    }

    private void nhan(){
        Intent intent1 = getIntent();
        Bundle bundle1 = intent1.getBundleExtra("datasearch");
        if (bundle1 != null){
            nameManga = bundle1.getString("ten");
            img = bundle1.getString("hinh");
            idTruyen = bundle1.getString("id");
            toolbar.setTitle(bundle1.getString("ten"));
            setcheckbox();

        }
    }

    private void nhanLibrary(){
        Intent intent1 = getIntent();
        Bundle bundle1 = intent1.getBundleExtra("datalibrary");
        if (bundle1 != null){
            nameManga = bundle1.getString("ten");
            img = bundle1.getString("hinh");
            idTruyen = bundle1.getString("id");
            toolbar.setTitle(bundle1.getString("ten"));
            setcheckbox();

        }
    }

    private void sentdata() {
        Bundle bundle = new Bundle();
        bundle.putString("key", nameManga);
        bundle.putString("id", idTruyen);
        fragment1.setArguments(bundle);
    }

    private void luuthuoctinh() {
        tvName.setText("" + nameManga);
        Glide.with(this).load(img).into(imgDetail);

        AsyncHttpClient client1 = new AsyncHttpClient();
        client1.get("http://json.mangapanda.com/manga/" + idTruyen, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject object = response.getJSONObject("manga");
                    des = object.getString("summary");
                    Log.d("a", "onSuccess: " + des);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                tvDes.setText(des);
                fragment2.txtsummary.setText(des);
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void init() {
        tvName = findViewById(R.id.ten);
        tvDes = findViewById(R.id.mota);
        cb = findViewById(R.id.checkbox);
        imgDetail = findViewById(R.id.img);
        lv = findViewById(R.id.lv);
        radioGroup = findViewById(R.id.group);
        btnChapter = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        viewPager = findViewById(R.id.viewPager);
        toolbar = findViewById(R.id.toolbar);
    }
}