package com.xuanthuan.comic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.xuanthuan.comic.adapter.Adapter_lv;
import com.xuanthuan.comic.object.ObjectListview;
import com.xuanthuan.comic.R;
import com.xuanthuan.comic.activity.MainActivity3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TimeZone;

import cz.msebera.android.httpclient.Header;

public class Fragment_Chapter extends Fragment {
    ListView listView;
    ArrayList<ObjectListview> arrayList;
    ArrayList<String> chapters;
    String tentruyen, idtruyen, ago;
    Adapter_lv adapter_lv;
    ProgressBar load;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;
//    private static final int MONTH_MILLIS = 30 * DAY_MILLIS;
//    private static final int YEAR_MILLIS = 12 * MONTH_MILLIS;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chapter, container,false);
        listView = view.findViewById(R.id.lv);
        load = view.findViewById(R.id.loadchapter);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
       // loadData();
        Bundle bundle = getArguments();
        if (bundle!=null) {
            tentruyen = bundle.getString("key");
            idtruyen = bundle.getString("id");
            //Log.d("thuan", "onCreateView: " + idtruyen);
        }
        loadData();
        addList();
        guidendoctruyen();
        return view;
    }

    private void loadData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://json.mangapanda.com/manga/" + idtruyen, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray jsonArray = response.getJSONArray("chapters");
                    chapters = new ArrayList<>();
                    load.setVisibility(View.INVISIBLE);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject object2 = jsonArray.getJSONObject(i);
                        chapters.add(object2.getString("chapter"));
                        long time1 = sdf.parse(object2.getString("adddate")).getTime();
                        long now = System.currentTimeMillis();
                        Log.d("time", "onSuccess: " + now + " ----------- " + time1);
//                        String ago =
//                                (String) DateUtils.getRelativeTimeSpanString(time1, now, DateUtils.MINUTE_IN_MILLIS);
                        if (time1 < 1000000000000L) {
                            time1 *= 1000;
                        }
//

                        final long diff = now - time1;
                        if (diff < MINUTE_MILLIS) {
                            ago = "just now";
                        } else if (diff < 2 * MINUTE_MILLIS) {
                            ago = "a minute ago";
                        } else if (diff < 50 * MINUTE_MILLIS) {
                            ago = diff / MINUTE_MILLIS + " minutes ago";
                        } else if (diff < 90 * MINUTE_MILLIS) {
                            ago = "an hour ago";
                        } else if (diff < 24 * HOUR_MILLIS) {
                            ago = diff / HOUR_MILLIS + " hours ago";
                        } else if (diff < 48 * HOUR_MILLIS) {
                            ago = "yesterday";
                        } else{
                            ago =  diff / DAY_MILLIS + " days ago";
                        }

                        arrayList.add(new ObjectListview(object2.getString("chapter"), ago));
                        if (i == jsonArray.length() - 1) {
                            Collections.reverse(arrayList);
                            Collections.reverse(chapters);
                        }
                    }
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
               // Collections.reverse(arrayList);
                adapter_lv.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                //Log.d("thuan", "onFailure: " + errorResponse);
            }
        });
    }

    private void addList() {
        arrayList = new ArrayList<>();
        adapter_lv = new Adapter_lv(arrayList, getContext(), R.layout.item_listview);
        listView.setAdapter(adapter_lv);
    }

    private void guidendoctruyen(){
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), MainActivity3.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("idchap", chapters.get(i));
                        bundle.putString("idtruyen", idtruyen);
                        bundle.putString("tentruyen", tentruyen);
                        intent.putExtra("data", bundle);
                        startActivity(intent);
                    }
                }
        );
    }
}
