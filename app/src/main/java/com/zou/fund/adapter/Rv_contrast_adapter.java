package com.zou.fund.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zou.fund.R;
import com.zou.fund.databean.Fund_position_arraylist;

import java.util.ArrayList;

/**
 * Created by 邹远君 on 2017/12/17.
 */

public class Rv_contrast_adapter extends RecyclerView.Adapter {

    ArrayList<Fund_position_arraylist> arrayList=new ArrayList<Fund_position_arraylist>();

    public Rv_contrast_adapter(ArrayList<Fund_position_arraylist> arrayList){
        this.arrayList=arrayList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_contrast_item,null);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).getListView().setAdapter(new Lv_contrast_adapter());
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public  class MyViewHolder extends RecyclerView.ViewHolder{

        private ListView listView;
        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            listView=itemView.findViewById(R.id.re_item_contrast);
        }
        public ListView getListView() {
            return listView;
        }
        public TextView getTextView() {
            return textView;
        }
    }

}
