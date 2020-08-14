package com.xuanthuan.comic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuanthuan.comic.object.Object_timkiem;
import com.xuanthuan.comic.R;

import java.util.List;

public class Adapter_timkiem extends BaseAdapter {
    List<Object_timkiem> listsearch;
    Context context;
    int layout;

    public Adapter_timkiem(List<Object_timkiem> list, Context context, int layout) {
        this.listsearch = list;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return listsearch.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class Viewholder{
        ImageView imgssearch;
        TextView txt_search;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder = new Viewholder();
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_search, null);

            viewholder.imgssearch = view.findViewById(R.id.imgsearch);
            viewholder.txt_search = view.findViewById(R.id.tensearch);

            view.setTag(viewholder);
        }else {
            viewholder = (Viewholder) view.getTag();
        }

        Object_timkiem object_timkiem = listsearch.get(i);
        viewholder.txt_search.setText(object_timkiem.getTen_search());
        Glide.with(context).load(object_timkiem.getHinh_search()).into(viewholder.imgssearch);
        Log.d("thuan", "getView: " + object_timkiem.getTen_search() + object_timkiem.getHinh_search());
        return view;
    }
}
