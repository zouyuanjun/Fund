package com.zou.fund.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zou.fund.R;
import com.zou.fund.customview.RateTextView;
import com.zou.fund.data.Fund_rankingdata_bean;

import java.util.ArrayList;

/**
 * Created by 邹远君 on 2018/2/12 0012.
 */

public class Rv_rangking_list_adapter extends RecyclerView.Adapter{
    ArrayList<Fund_rankingdata_bean> list=new ArrayList();

    public Rv_rangking_list_adapter(ArrayList<Fund_rankingdata_bean> list) {
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
        ((MyViewHolder)holder).getRangking_fundcode().setText(list.get(position).getRangking_fundcode());
        ((MyViewHolder)holder).getRangking_fundname().setText(list.get(position).getRangking_fundname());
        ((MyViewHolder)holder).getDay().setdata(list.get(position).getDay());
        ((MyViewHolder)holder).getWeek().setdata(list.get(position).getWeek());
        ((MyViewHolder)holder).getMonth().setdata(list.get(position).getMonth());
        ((MyViewHolder)holder).getThree_month().setdata(list.get(position).getThree_month());
        ((MyViewHolder)holder).getSix_month().setdata(list.get(position).getSix_month());
        ((MyViewHolder)holder).getThis_year().setdata(list.get(position).getThis_year());
        ((MyViewHolder)holder).getYear().setdata(list.get(position).getYear());
        ((MyViewHolder)holder).getTwo_year().setdata(list.get(position).getTwo_year());
        ((MyViewHolder)holder).getMax().setdata(list.get(position).getMax());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView rangking_fundcode;
        TextView rangking_fundname;
        RateTextView week;
        RateTextView day;
        RateTextView month;
        RateTextView this_year;
        RateTextView three_month;
        RateTextView six_month;
        RateTextView year;
        RateTextView two_year;
        RateTextView max;

        public MyViewHolder(View itemView) {
            super(itemView);
            rangking_fundcode=itemView.findViewById(R.id.tv_rangking_fundcode);
            rangking_fundname=itemView.findViewById(R.id.tv_rangking_fundname);
            day=itemView.findViewById(R.id.tv_day);
            week=itemView.findViewById(R.id.tv_week);
            month=itemView.findViewById(R.id.tv_month);
            this_year=itemView.findViewById(R.id.tv_this_year);
            three_month=itemView.findViewById(R.id.tv_three_month);
            six_month=itemView.findViewById(R.id.tv_six_month);
            year=itemView.findViewById(R.id.tv_year);
            two_year=itemView.findViewById(R.id.tv_two_year);
            max=itemView.findViewById(R.id.tv_max);

        }

        public TextView getRangking_fundcode() {
            return rangking_fundcode;
        }

        public TextView getRangking_fundname() {
            return rangking_fundname;
        }

        public RateTextView getWeek() {
            return week;
        }

        public RateTextView getDay() {
            return day;
        }

        public RateTextView getMonth() {
            return month;
        }

        public RateTextView getThis_year() {
            return this_year;
        }

        public RateTextView getThree_month() {
            return three_month;
        }

        public RateTextView getSix_month() {
            return six_month;
        }

        public RateTextView getYear() {
            return year;
        }

        public RateTextView getTwo_year() {
            return two_year;
        }

        public RateTextView getMax() {
            return max;
        }
    }
}
