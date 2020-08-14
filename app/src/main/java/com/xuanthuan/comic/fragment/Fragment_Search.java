package com.xuanthuan.comic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.xuanthuan.comic.adapter.Adapter_timkiem;
import com.xuanthuan.comic.object.Object_timkiem;
import com.xuanthuan.comic.R;
import com.xuanthuan.comic.activity.MainActivity2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Fragment_Search extends Fragment {
    ArrayList<Object_timkiem> arrayList;
    Adapter_timkiem adapter_timkiem;
    GridView lv;
    EditText edtSearch;
    String keySearch;
    Button btnclick;
    public String idsearch,tensearch, imgsearch;
    ProgressBar loading;
    int kt=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container,false);
        lv = view.findViewById(R.id.lvSearch);
        edtSearch = view.findViewById(R.id.edttimkiem);
        btnclick = view.findViewById(R.id.search);
        loading = view.findViewById(R.id.loading);
        arrayList = new ArrayList<>();


        adapter_timkiem = new Adapter_timkiem(arrayList, getContext(), R.layout.item_search);
        lv.setAdapter(adapter_timkiem);
        loading.setVisibility(View.INVISIBLE);
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    arrayList.clear();
                    loading.setVisibility(View.VISIBLE);
                    keySearch = edtSearch.getText().toString().trim();
                    Log.d("thuan", "key: " + keySearch);
                    performSearch();
                    return true;
                }
                return false;
            }
        });

        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.clear();
                loading.setVisibility(View.VISIBLE);
                keySearch = edtSearch.getText().toString().trim();
                Log.d("thuan", "key: " + keySearch);
                performSearch();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("id", idsearch);
                bundle1.putString("ten", tensearch);
                bundle1.putString("hinh", imgsearch);
                intent.putExtra("datasearch", bundle1);
                startActivity(intent);
            }
        });

        return view;
    }

    private void performSearch() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://json.mangapanda.com/list/popular", new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray array = response.getJSONArray("mangas");
                    for ( kt=0 ; kt<array.length(); kt++){
                            JSONObject object = array.getJSONObject(kt);
                        //Log.d("thuan", "onSuccess: "+object.getString("search") );
                            if (object.getString("search").toLowerCase().trim().contains(keySearch.toLowerCase())){
                                tensearch = object.getString("name");
                                idsearch = object.getString("id");
                                imgsearch = object.getString("coverimg");
                                arrayList.add(new Object_timkiem(object.getString("name"), object.getString("coverimg")));
                            }else {
                                kt++;
                            }

                    }
                    if (kt == array.length() && arrayList!= null) {
                        adapter_timkiem.notifyDataSetChanged();
                        loading.setVisibility(View.INVISIBLE);
                    }else if (kt == array.length() && arrayList == null){
                        loading.setVisibility(View.INVISIBLE);
                        Toast.makeText(getActivity(), "Không tìm thấy truyện! ", Toast.LENGTH_SHORT).show();
                    }


//                    if (arrayList!= null){
//                        adapter_timkiem.notifyDataSetChanged();
//                        loading.setVisibility(View.INVISIBLE);
//                    }else {
//                        Toast.makeText(getActivity(), "Không tìm thấy truyện! ", Toast.LENGTH_SHORT).show();
//                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
