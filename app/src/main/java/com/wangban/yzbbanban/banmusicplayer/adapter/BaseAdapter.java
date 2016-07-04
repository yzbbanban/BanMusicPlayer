package com.wangban.yzbbanban.banmusicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YZBbanban on 16/6/5.
 * 父类 adapter
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    private Context context;
    private ArrayList<T> data;
    private LayoutInflater layoutInflater;

    public BaseAdapter(Context context, ArrayList<T> data) {
        setContext(context);
        setData(data);
        setLayoutInflater();
    }

    public Context getContext() {
        return context;
    }

    private final void setContext(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context 不为null");
        }
        this.context = context;
    }

    public List<T> getData() {
        if (data == null) {
            data = new ArrayList<T>();
        }
        return data;
    }

    private void setData(ArrayList<T> data) {
        if (data==null){
            data=new ArrayList<>();
        }
        this.data = data;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    private void setLayoutInflater() {
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
