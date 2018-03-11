package com.zou.fund.adapter;

import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zou.fund.R;
import com.zou.fund.data.Fund;
import com.zou.fund.data.Fund_chicang;

import java.util.ArrayList;

/**
 * Created by 邹远君 on 2018/3/11 0011.
 */

public class Lv_fundchicang_adapter extends BaseAdapter{
    ArrayList<Fund_chicang> arrayList=new ArrayList<>();

    public Lv_fundchicang_adapter(ArrayList<Fund_chicang> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_fundchicang_item,null);
        TextView gup_name=view.findViewById(R.id.tv_gupname);
        TextView gup_zhangfu=view.findViewById(R.id.tv_gupzhangfu);
        TextView gup_zhanbi=view.findViewById(R.id.tv_gup_zhanbi);
        gup_name.setText(arrayList.get(position).getGup_name());
        gup_zhangfu.setText(arrayList.get(position).getGup_zhangfu());
        gup_zhanbi.setText(arrayList.get(position).getGup_zhanbi());
        return view;
    }
}
