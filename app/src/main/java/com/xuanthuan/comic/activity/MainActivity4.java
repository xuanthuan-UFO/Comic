package com.xuanthuan.comic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.xuanthuan.comic.adapter.Adapter_listGenres;
import com.xuanthuan.comic.object.Object_listGenres;
import com.xuanthuan.comic.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity4 extends AppCompatActivity {
    String key;
    Toolbar toolbar;
    ProgressBar progressBar;
    GridView gv;
    ArrayList<Object_listGenres> arrayList;
    Adapter_listGenres adapter_listGenres;
    String url = "http://json.mangapanda.com/list/popular";
    ArrayList<String> sentnamegenres;
    ArrayList<String> sentidgenres;
    ArrayList<String> sentimggenres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        toolbar = findViewById(R.id.toolbargenres);
        progressBar = findViewById(R.id.loadGenres);
        setbar();
        gv = findViewById(R.id.gvlistgenres);
        arrayList = new ArrayList<>();
        adapter_listGenres = new Adapter_listGenres(arrayList, this, R.layout.item_listgenres);
        gv.setAdapter(adapter_listGenres);
        nhangenres();
        createRequest();

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("name", sentnamegenres.get(i));
                bundle1.putString("id", sentidgenres.get(i));
                bundle1.putString("img", sentimggenres.get(i));
                intent.putExtra("datagenres", bundle1);
                startActivity(intent);

            }
        });



    }

    private void createRequest() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        arrayList.clear();
                        try {
                            JSONArray array = response.getJSONArray("mangas");
                            sentidgenres = new ArrayList<>();
                            sentnamegenres = new ArrayList<>();
                            sentimggenres = new ArrayList<>();

                            for (int i=0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String ktra = object.getString("genre");
                                if (ktra.toLowerCase().contains(key.toLowerCase())) {
                                    sentidgenres.add(object.getString("id"));
                                    sentnamegenres.add(object.getString("name"));
                                    sentimggenres.add(object.getString("coverimg"));
                                    arrayList.add(new Object_listGenres(object.getString("coverimg"), object.getString("name")));
                                }

                            }
                            progressBar.setVisibility(View.INVISIBLE);
                            adapter_listGenres.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    private void setbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void nhangenres() {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle = intent.getBundleExtra("data");
        key = bundle.getString("key");
        toolbar.setTitle(key);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}