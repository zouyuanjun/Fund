package com.zou.fund.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zou.fund.R;
import com.zou.fund.data.My_fund_bean;

import java.util.ArrayList;

/**
 * Created by 邹远君 on 2018/1/18 0018.
 */

public class Rv_myfund_adapter extends RecyclerView.Adapter {
    ArrayList<My_fund_bean> arrayList = new ArrayList<>();

    public Rv_myfund_adapter(ArrayList<My_fund_bean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_myfund_item, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).getMyfund_name().setText(arrayList.get(position).getMyfund_name()+"55");
        ((MyViewHolder)holder).getMyfund_type().setText(arrayList.get(position).getMyfund_type());
        ((MyViewHolder)holder).getMyfund_code().setText(arrayList.get(position).getMyfund_code());
        ((MyViewHolder)holder).getMyfund_chicang().setText(arrayList.get(position).getMyfund_chicang()+"份");
        Uri uri = Uri.parse(arrayList.get(position).getMyfund_imurl());
       // ((MyViewHolder)holder).getSd_gxjj().setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myfund_name;             //持仓基金名称
        TextView myfund_type;          //类型
        TextView myfund_code;          //基金代码
        TextView myfund_chicang;       //持仓数量
        SimpleDraweeView sd_gxjj;

        public MyViewHolder(View itemView) {
            super(itemView);
            myfund_name = itemView.findViewById(R.id.tv_myfund_name);
            myfund_type = itemView.findViewById(R.id.tv_myfund_type);
            myfund_code = itemView.findViewById(R.id.tv_myfund_code);
            myfund_chicang = itemView.findViewById(R.id.tv_myfund_chicang);
            sd_gxjj = itemView.findViewById(R.id.sd_gxjj);
        }

        public TextView getMyfund_name() {
            return myfund_name;
        }

        public TextView getMyfund_type() {
            return myfund_type;
        }

        public TextView getMyfund_code() {
            return myfund_code;
        }

        public TextView getMyfund_chicang() {
            return myfund_chicang;
        }

        public SimpleDraweeView getSd_gxjj() {
            return sd_gxjj;
        }
    }
}
