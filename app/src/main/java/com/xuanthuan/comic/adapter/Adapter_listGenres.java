package com.xuanthuan.comic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuanthuan.comic.object.Object_listGenres;
import com.xuanthuan.comic.R;

import java.util.List;

public class Adapter_listGenres extends BaseAdapter {
    List<Object_listGenres> list;
    Context context;
    int layout;

    public Adapter_listGenres(List<Object_listGenres> list, Context context, int layout) {
        this.list = list;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    class ViewholđerlistGenres{
        TextView txtten;
        ImageView imgHinh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewholđerlistGenres holder = new ViewholđerlistGenres();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_listgenres, null);

            holder.txtten = view.findViewById(R.id.ten_itemgenres);
            holder.imgHinh = view.findViewById(R.id.hinh_itemgenres);
            view.setTag(holder);
        } else {
            holder = (ViewholđerlistGenres) view.getTag();
        }
        Object_listGenres object_listGenres = list.get(i);
        holder.txtten.setText(object_listGenres.getTentruyenGenres());
        Glide.with(context).load(object_listGenres.getUrlHinhgenres()).into(holder.imgHinh);

        return view;
    }
}
