package com.zou.fund.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zou.fund.R;
import com.zou.fund.data.Fund_position_arraylist;
import com.zou.fund.data.Fund_position_bean;

import java.util.ArrayList;

/**
 * Created by 邹远君 on 2017/12/18.
 */

public class Lv_contrast_adapter extends BaseAdapter {

    public ArrayList<Fund_position_arraylist> arrayList=new ArrayList();
    public ArrayList<Fund_position_bean> arrayList_bean=new ArrayList<>();

    public Lv_contrast_adapter(){
      arrayList_bean.add(new Fund_position_bean("南昌胜利","0.85"));
        arrayList_bean.add(new Fund_position_bean("asdfasdf","0.85"));
        arrayList_bean.add(new Fund_position_bean("的风格","0.85"));
        arrayList_bean.add(new Fund_position_bean("南岁的法国","0.85"));
        arrayList_bean.add(new Fund_position_bean("阿达","0.85"));
        arrayList_bean.add(new Fund_position_bean("刚发噶似的","0.85"));
        arrayList_bean.add(new Fund_position_bean("爱的","0.85"));
        arrayList_bean.add(new Fund_position_bean("也热特热","0.85"));
        arrayList_bean.add(new Fund_position_bean("南昌胜利","0.85"));
        arrayList_bean.add(new Fund_position_bean("asdfasdf","0.85"));
        arrayList_bean.add(new Fund_position_bean("的风格","0.85"));
        arrayList_bean.add(new Fund_position_bean("南岁的法国","0.85"));
        arrayList_bean.add(new Fund_position_bean("阿达","0.85"));
        arrayList_bean.add(new Fund_position_bean("刚发噶似的","0.85"));
        arrayList_bean.add(new Fund_position_bean("爱的","0.85"));
        arrayList_bean.add(new Fund_position_bean("也热特热","0.85"));
        arrayList_bean.add(new Fund_position_bean("南昌胜利","0.85"));
        arrayList_bean.add(new Fund_position_bean("asdfasdf","0.85"));
        arrayList_bean.add(new Fund_position_bean("的风格","0.85"));
        arrayList_bean.add(new Fund_position_bean("南岁的法国","0.85"));
        arrayList_bean.add(new Fund_position_bean("阿达","0.85"));
        arrayList_bean.add(new Fund_position_bean("刚发噶似的","0.85"));
        arrayList_bean.add(new Fund_position_bean("爱的","0.85"));
        arrayList_bean.add(new Fund_position_bean("也热特热","0.85"));
        arrayList_bean.add(new Fund_position_bean("南昌胜利","0.85"));
        arrayList_bean.add(new Fund_position_bean("asdfasdf","0.85"));
        arrayList_bean.add(new Fund_position_bean("的风格","0.85"));
        arrayList_bean.add(new Fund_position_bean("南岁的法国","0.85"));
        arrayList_bean.add(new Fund_position_bean("阿达","0.85"));
        arrayList_bean.add(new Fund_position_bean("刚发噶似的","0.85"));
        arrayList_bean.add(new Fund_position_bean("爱的","0.85"));
        arrayList_bean.add(new Fund_position_bean("也热特热","0.85"));
    }
    @Override
    public int getCount() {
        return arrayList_bean.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_contrast_item,parent,false);
        TextView tv_contrast_stock=view.findViewById(R.id.tv_contrast_stock);
        TextView tv_contrast_ratio=view.findViewById(R.id.tv_contrast_fund_ratio);
        tv_contrast_stock.setText(arrayList_bean.get(position).getStock());
        tv_contrast_ratio.setText(arrayList_bean.get(position).getRatio());

        return view;
    }
}
