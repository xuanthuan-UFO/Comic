package com.xuanthuan.comic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.xuanthuan.comic.object.Object_item_chap;
import com.xuanthuan.comic.R;

import java.util.ArrayList;

public class Adapter_doctruyen extends PagerAdapter {
    ArrayList<Object_item_chap> listtruyen;
    Context context;
    int layout;

    public Adapter_doctruyen(ArrayList<Object_item_chap> listtruyen, Context context, int layout) {
        this.listtruyen = listtruyen;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return listtruyen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_doctruyen,container, false);

        ImageView imgView = view.findViewById(R.id.img_chap);

        Object_item_chap object_item_chap = listtruyen.get(position);

        Glide.with(context).load(object_item_chap.getHinh_truyen()).placeholder(R.drawable.image)
                .error(R.drawable.image).into(imgView);
        Log.d("thuan", "hinh them: " + object_item_chap.getHinh_truyen());

        container.addView(view);  //ra là thế
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
