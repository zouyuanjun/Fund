package com.zou.fund.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zou.fund.R;

import java.util.ArrayList;

/**
 * Created by 邹远君 on 2018/2/12 0012.
 */

public class Rv_rangking_list_adapter extends RecyclerView.Adapter{
    ArrayList list=new ArrayList();

    public Rv_rangking_list_adapter(ArrayList list) {
        Log.d("5555","适配器数据大小"+list.size());
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_rangkiing_list_item, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("5555",list.get(position).toString());
        ((MyViewHolder)holder).getTextView().setText(list.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_rangking_list);
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
