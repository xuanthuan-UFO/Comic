package com.xuanthuan.comic.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.xuanthuan.comic.adapter.Adapter_Library;
import com.xuanthuan.comic.object.Object_Library;
import com.xuanthuan.comic.R;
import com.xuanthuan.comic.activity.MainActivity;
import com.xuanthuan.comic.activity.MainActivity2;

import java.util.ArrayList;


public class Fragment_Library extends Fragment   {
    GridView gv;
    public static ArrayList<Object_Library> arrayListLIBRARY;
    Adapter_Library adapter_library;
    MainActivity activity;
    String idtruyendatabase,tendatabase,urlimgdatabase;
    int iddatabase;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    Cursor cursor;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container,false);
        arrayListLIBRARY = new ArrayList<>();
        gv = view.findViewById(R.id.gvlibrary);
        adapter_library = new Adapter_Library(arrayListLIBRARY, getActivity(), R.layout.item_library);
        gv.setAdapter(adapter_library);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.container);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter_library.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
                cursorload();
            }
        });
                cursorload();

                gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        xacnhanXoa(arrayListLIBRARY.get(i).getId());
                        return true;
                    }
                });

                gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), MainActivity2.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("id",arrayListLIBRARY.get(i).getIdtruyen());
                        bundle.putString("ten",arrayListLIBRARY.get(i).getTen());
                        bundle.putString("hinh",arrayListLIBRARY.get(i).getUrrlimg());
                        intent.putExtra("datalibrary",bundle);
                        startActivity(intent);
                    }
                });

        return view;
    }

    public void cursorload() {
        arrayListLIBRARY.clear();
        cursor = MainActivity.dbLibrary.getDATA("SELECT * FROM LIBRARY");

        while (cursor.moveToNext()) {
            iddatabase = cursor.getInt(0);
            tendatabase = cursor.getString(1);
            idtruyendatabase = cursor.getString(2);
            urlimgdatabase = cursor.getString(3);

            arrayListLIBRARY.add(new Object_Library(tendatabase, urlimgdatabase, idtruyendatabase, iddatabase));

            Log.d("sql", "onClick: " + idtruyendatabase + iddatabase + tendatabase + urlimgdatabase);
            adapter_library.notifyDataSetChanged();
        }
    }

    public void xacnhanXoa(final int id) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông báo!");
        builder.setMessage("bạn có muốn xóa truyện này?");


        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.dbLibrary.queryDATA("DELETE FROM LIBRARY WHERE Id = '"+ id +"'");
                cursorload();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
    }




}
