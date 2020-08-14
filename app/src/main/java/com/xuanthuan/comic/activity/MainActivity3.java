package com.xuanthuan.comic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.xuanthuan.comic.adapter.Adapter_doctruyen;
import com.xuanthuan.comic.object.Object_item_chap;
import com.xuanthuan.comic.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity3 extends AppCompatActivity {
    String idchap, idtruyen, title;
    Toolbar toolbar1;
    ArrayList<Object_item_chap> arrayList = new ArrayList<>();
    Adapter_doctruyen adapter_doctruyen;
    ViewPager viewPager;
    ProgressBar progressBar;
    AppBarLayout bar;
    TextView txtpage, txtnextpage;
    int sizepage;
    int idnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        bar = findViewById(R.id.bar);
        txtpage = findViewById(R.id.page);
        txtnextpage = findViewById(R.id.nextpage);
        progressBar = findViewById(R.id.loadingtruyen);
        viewPager = findViewById(R.id.viewPagerdoc);

        adapter_doctruyen = new Adapter_doctruyen(arrayList, this, R.layout.item_doctruyen);

        nhandata();
        loadtruyen(idchap);

        toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar1.setTitle(title + idchap+"// - Read" + title + " Chapter" + idchap+ "//");
        viewPager.setAdapter(adapter_doctruyen);
        scrollViewpager();

        txtnextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                viewPager.removeAllViewsInLayout();
                arrayList.clear();
                progressBar.setVisibility(View.VISIBLE);
                idnext += 1;
                Log.d("mhd3", "onClick: " + idnext);
                toolbar1.setTitle(title + idnext +"// - Read" + title + " Chapter" + idnext + "//");
                loadtruyen(String.valueOf(idnext));
                txtpage.setText("Page " + viewPager.getCurrentItem() + "/" + sizepage);
                scrollViewpager();
                viewPager.setAdapter(adapter_doctruyen);
            }
        });

        viewPager.setOffscreenPageLimit(10);
    }

    private void scrollViewpager() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                txtpage.setText("Page " + viewPager.getCurrentItem() + "/" + sizepage);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void loadtruyen(String idchap) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://json.mangapanda.com/chapter/" + idtruyen + "/" + idchap, new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            JSONArray array = response.getJSONArray("chapters");
                            progressBar.setVisibility(View.INVISIBLE);
                            bar.setVisibility(View.VISIBLE);
                            for (int i=0; i< array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                              //  Log.d("thuan1", "onSuccess: "+ object.getString("imgurl"));
                                arrayList.add(new Object_item_chap(object.getString("imgurl")));
                            }
                            sizepage = arrayList.size() -1;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter_doctruyen.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                       // Log.d("thuan1", "Lỗi"+ errorResponse);
                    }
                }
        );
    }

    private void nhandata() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        idtruyen = bundle.getString("idtruyen");
        idchap = bundle.getString("idchap");
        title = bundle.getString("tentruyen");
        idnext = Integer.parseInt(idchap);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}