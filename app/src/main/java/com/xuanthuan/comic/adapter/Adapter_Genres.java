package com.xuanthuan.comic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xuanthuan.comic.object.Object_fragmentGenres;
import com.xuanthuan.comic.R;

import java.util.List;

public class Adapter_Genres extends BaseAdapter {
    List<Object_fragmentGenres> list;
    Context context;
    int layout;

    public Adapter_Genres(List list, Context context, int layout) {
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

    class ViewHoldergenres{
        TextView txt;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoldergenres holder = new ViewHoldergenres();
        if (view==null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_fragmentgenres, null);

            holder.txt = view.findViewById(R.id.txtgenres);
            view.setTag(holder);
        }else {
            holder = (ViewHoldergenres) view.getTag();
        }
        Object_fragmentGenres object_fragmentGenres = list.get(i);
        holder.txt.setText(object_fragmentGenres.getGenres());

        return view;
    }
}
