package com.xuanthuan.comic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuanthuan.comic.object.Object_Library;
import com.xuanthuan.comic.R;

import java.util.List;

public class Adapter_Library extends BaseAdapter {
    List<Object_Library> list;
    Context context;
    int layout;

    public Adapter_Library(List<Object_Library> list, Context context, int layout) {
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

    class  ViewholderLibrary{
        TextView txtten;
        ImageView img;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewholderLibrary holder = new ViewholderLibrary();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_library, null);

            holder.txtten = view.findViewById(R.id.item_ten_library);
            holder.img = view.findViewById(R.id.item_hinh_library);

            view.setTag(holder);
        } else {
            holder = (ViewholderLibrary) view.getTag();
        }

        Object_Library library = list.get(i);

        holder.txtten.setText(library.getTen());
        Glide.with(context).load(library.getUrrlimg()).into(holder.img);

        //context.fragment_library.xacnhanXoa(library.getTen(), library.getId());

        return view;
    }
}
