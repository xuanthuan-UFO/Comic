package com.xuanthuan.comic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuanthuan.comic.object.Object_hotupdate;
import com.xuanthuan.comic.R;

import java.util.List;

public class AdapterHotupdate extends BaseAdapter {
    List<Object_hotupdate> list;
    Context context;
    int layout;

    public AdapterHotupdate(List<Object_hotupdate> list, Context context, int layout) {
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

    class ViewHolder{
      ImageView imgHinh;
      TextView txtten;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_listview, null);

            holder.imgHinh = view.findViewById(R.id.imghinh);
            holder.txtten = view.findViewById(R.id.tentruyen);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        Object_hotupdate object_hotupdate = list.get(i);
        holder.txtten.setText(object_hotupdate.getTentruyen());
       // Picasso.get().load("http://json.mangapanda.com/home").into(holder.imgHinh);
        Glide.with(context).load(object_hotupdate.getHinh()).into(holder.imgHinh);
        return view;
    }
}
