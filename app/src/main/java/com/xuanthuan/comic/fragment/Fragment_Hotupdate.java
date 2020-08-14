package com.xuanthuan.comic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.xuanthuan.comic.adapter.AdapterHotupdate;
import com.xuanthuan.comic.object.Object_hotupdate;
import com.xuanthuan.comic.R;
import com.xuanthuan.comic.activity.MainActivity2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class Fragment_Hotupdate extends Fragment {
    ArrayList<Object_hotupdate> arrayList;
    AdapterHotupdate adapterHotupdate;
    GridView gv;
    public int vitri;
    ProgressBar load;
    int REQUEST_CODE = 123;
    ArrayList<String> idhinh;
    List<String> listCheck=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotupdate, container,false);
        load = view.findViewById(R.id.loadhotupdate);
        gv = view.findViewById(R.id.lvhotupdate);
        arrayList = new ArrayList<>();
        adapterHotupdate = new AdapterHotupdate(arrayList, getActivity(), R.layout.item_listview);
        gv.setAdapter(adapterHotupdate);
        Asynclien();
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vitri = i;
                Intent intent = new Intent(getActivity(), MainActivity2.class);

                Bundle bundle = new Bundle();
                bundle.putString("ten", arrayList.get(i).getTentruyen());
                bundle.putString("hinh",arrayList.get(i).getHinh());
                bundle.putString("id", idhinh.get(i));
                intent.putExtra("data", bundle);
//                startActivityForResult(intent, REQUEST_CODE);
                startActivity(intent);
            }
        });

        return view;
    }


    private void Asynclien(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://json.mangapanda.com/home", new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        idhinh = new ArrayList<>();
                        load.setVisibility(View.INVISIBLE);
                        for (int i =0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                String name=object.getString("manganame");
                                if (!listCheck.contains(name)) {
                                    idhinh.add(object.getString("mangaid"));
                                    arrayList.add(new Object_hotupdate(object.getString("coverimg"), name));
                                    listCheck.add(name);
                                }

                                Log.d("aa", "onSuccess: " + arrayList.get(0));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        adapterHotupdate.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });
    }

}
