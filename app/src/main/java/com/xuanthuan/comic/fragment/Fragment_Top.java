package com.xuanthuan.comic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.xuanthuan.comic.adapter.Adapter_Top;
import com.xuanthuan.comic.object.ObjectTop;
import com.xuanthuan.comic.R;
import com.xuanthuan.comic.activity.MainActivity2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Fragment_Top extends Fragment {
    String url = "http://json.mangapanda.com/list/popular";
    ArrayList<ObjectTop> arrayList = new ArrayList<>();
    GridView gv;
    Adapter_Top adapter_top;
    ProgressBar load;
    ArrayList<String> sentid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container,false);
        gv = view.findViewById(R.id.gvtop);
        load = view.findViewById(R.id.loadtop);
        adapter_top = new Adapter_Top(arrayList, getActivity(), R.layout.item_fragmenttop);
        request();
        gv.setAdapter(adapter_top);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putString("ten", arrayList.get(i).getTentruyen());
                bundle.putString("hinh",arrayList.get(i).getHinh());
                bundle.putString("id", sentid.get(i));
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });

        return view;
    }

    private void request() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    JSONArray array = response.getJSONArray("mangas");
                    load.setVisibility(View.INVISIBLE);
                    sentid = new ArrayList<>();
                    for (int i=0; i< array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        sentid.add(object.getString("id"));
                        arrayList.add(new ObjectTop(object.getString("coverimg"), object.getString("name")));
                    }

                    adapter_top.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }
}
