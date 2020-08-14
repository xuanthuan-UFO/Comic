package com.xuanthuan.comic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xuanthuan.comic.object.ObjectListview;
import com.xuanthuan.comic.R;

import java.util.List;

public class Adapter_lv extends BaseAdapter {
    List<ObjectListview> list;
    Context context;
    int layout;

    public Adapter_lv(List<ObjectListview> list, Context context, int layout) {
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

    class ViewHolder2{
        TextView txttenchap, txtthoigian;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder2 holder2 = new ViewHolder2();
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_fmchapter, null);

            holder2.txttenchap = view.findViewById(R.id.tenchap);
            holder2.txtthoigian = view.findViewById(R.id.thoigian);

            view.setTag(holder2);
        }else {
            holder2 = (ViewHolder2) view.getTag();
        }
//        Collections.reverse(list);
        ObjectListview objectListview = list.get(i);
        holder2.txttenchap.setText("Chapter  - " + objectListview.getTenchap());
        holder2.txtthoigian.setText(objectListview.getThoigian());

        return view;
    }
}
