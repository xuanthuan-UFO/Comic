package com.xuanthuan.comic.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xuanthuan.comic.adapter.Adapter_Genres;
import com.xuanthuan.comic.object.Object_fragmentGenres;
import com.xuanthuan.comic.R;
import com.xuanthuan.comic.activity.MainActivity4;

import java.util.ArrayList;

public class Fragment_Genres extends Fragment {
    GridView gv;
    //String[] array = getResources().getStringArray(R.array.genres);
    Adapter_Genres adapter;
    ArrayList<Object_fragmentGenres> array;
    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genres, container,false);

        gv = view.findViewById(R.id.gvgenres);
        add();
        adapter = new Adapter_Genres(array, getActivity(), R.layout.item_fragmentgenres);
        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String theloai = array.get(i).getGenres();
                Intent intent = new Intent(getActivity(), MainActivity4.class);
                Bundle bundle3 = new Bundle();
                bundle3.putString("key", theloai);
                intent.putExtra("data",bundle3);
                startActivity(intent);
            }
        });


        return view;
    }

    private void add() {
        array = new ArrayList<>();
        array.add(new Object_fragmentGenres("Action"));
        array.add(new Object_fragmentGenres("Adventure"));
        array.add(new Object_fragmentGenres("Comedy"));
        array.add(new Object_fragmentGenres("Demons"));
        array.add(new Object_fragmentGenres("Drama"));
        array.add(new Object_fragmentGenres("Ecchi"));
        array.add(new Object_fragmentGenres("Fantasy"));
        array.add(new Object_fragmentGenres("Gender Bender"));
        array.add(new Object_fragmentGenres("Harem"));
        array.add(new Object_fragmentGenres("Historical"));
        array.add(new Object_fragmentGenres("Horror"));
        array.add(new Object_fragmentGenres("Josei"));
        array.add(new Object_fragmentGenres("Magic"));
        array.add(new Object_fragmentGenres("Martial Arts"));
        array.add(new Object_fragmentGenres("Mature"));
        array.add(new Object_fragmentGenres("Mecha"));
        array.add(new Object_fragmentGenres("Military"));
        array.add(new Object_fragmentGenres("Mystery"));
        array.add(new Object_fragmentGenres("One Shot"));
        array.add(new Object_fragmentGenres("Romance"));
        array.add(new Object_fragmentGenres("School Life"));
        array.add(new Object_fragmentGenres("Sci-Fi"));
        array.add(new Object_fragmentGenres("Seinen"));
        array.add(new Object_fragmentGenres("Shoujo"));
        array.add(new Object_fragmentGenres("Shoujoai"));
        array.add(new Object_fragmentGenres("Shounen"));
        array.add(new Object_fragmentGenres("Shounenai"));
        array.add(new Object_fragmentGenres("Slice of Life"));
        array.add(new Object_fragmentGenres("Smut"));
        array.add(new Object_fragmentGenres("Sports"));
        array.add(new Object_fragmentGenres("Super Power"));
        array.add(new Object_fragmentGenres("Supernatural"));
        array.add(new Object_fragmentGenres("Tragedy"));
        array.add(new Object_fragmentGenres("Vampire"));
        array.add(new Object_fragmentGenres("Yaoi"));
        array.add(new Object_fragmentGenres("Yuri"));




    }
}
