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
    ArrayList<String> list=new ArrayList();

    public Rv_rangking_list_adapter(ArrayList<String> list) {
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
        ((MyViewHolder)holder).getDay().setText(list.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView week;
        TextView day;
        TextView month;
        TextView two_month;
        TextView three_month;
        TextView six_month;
        TextView year;
        TextView two_year;
        TextView max;

        public MyViewHolder(View itemView) {
            super(itemView);
            day=itemView.findViewById(R.id.tv_day);
            week=itemView.findViewById(R.id.tv_week);
            month=itemView.findViewById(R.id.tv_month);
            two_month=itemView.findViewById(R.id.tv_two_month);
            three_month=itemView.findViewById(R.id.tv_three_month);
            six_month=itemView.findViewById(R.id.tv_six_month);
            year=itemView.findViewById(R.id.tv_year);
            two_year=itemView.findViewById(R.id.tv_two_year);
            max=itemView.findViewById(R.id.tv_max);

        }

        public TextView getWeek() {
            return week;
        }

        public TextView getDay() {
            return day;
        }

        public TextView getMonth() {
            return month;
        }

        public TextView getTwo_month() {
            return two_month;
        }

        public TextView getThree_month() {
            return three_month;
        }

        public TextView getSix_month() {
            return six_month;
        }

        public TextView getYear() {
            return year;
        }

        public TextView getTwo_year() {
            return two_year;
        }

        public TextView getMax() {
            return max;
        }
    }
}
