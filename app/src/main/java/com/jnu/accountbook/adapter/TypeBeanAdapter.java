package com.jnu.accountbook.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jnu.accountbook.R;
import com.jnu.accountbook.bean.TypeBean;

import java.util.List;

public class TypeBeanAdapter extends BaseAdapter {

    Context context;
    List<TypeBean> typeBeanList;
    int position = 0;

    public void setPosition(int position) {
        this.position = position;
    }

    public TypeBeanAdapter(Context context, List<TypeBean> typeList) {
        this.context = context;
        this.typeBeanList = typeList;
    }

    @Override
    public int getCount() {
        return typeBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return typeBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_gridview, viewGroup, false);
        ImageView imageView = view.findViewById(R.id.item_gridview_image);
        TextView textView = view.findViewById(R.id.item_gridview_text);
        TypeBean typeBean = typeBeanList.get(i);
        if (i == position) {
            imageView.setImageResource(typeBean.getsImageId());
        } else {
            imageView.setImageResource(typeBean.getImageId());
        }

        textView.setText(typeBean.getTypename());
        return view;
    }


}
