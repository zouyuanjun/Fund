package com.zou.fund.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zou.fund.R;
import com.zou.fund.adapter.Rv_rangking_list_adapter;
import com.zou.fund.data.Fund_rankingdata_bean;
import com.zou.fund.parse.P_ranking_list;
import com.zou.fund.util.Network;
import com.zou.fund.util.SqlHelper;

import org.jsoup.select.Evaluator;
import org.litepal.LitePal;
import org.litepal.LitePalDB;
import org.litepal.crud.DataSupport;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 邹远君 on 2018/2/12 0012.
 */

public class Fr_rangking_list extends Fragment {
    List<Fund_rankingdata_bean> quarylist=new ArrayList();
    Context context;
    View rootView;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Fund_rankingdata_bean> arrayList=new ArrayList();
    RecyclerView recyclerView;
    String url="http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=kf&ft=all&rs=&gs=0&sc=qjzf&st=desc&pi=1&pn=10000&dx=1";
    Network network;
    int what=1;
    Rv_rangking_list_adapter adapter;
    SqlHelper sqlHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fr_rangking_list, container, false);
        recyclerView=rootView.findViewById(R.id.rv_rangking_list);
        context=this.getActivity();
        initview();
        initdata();
        return rootView;
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = (String) msg.obj;
            P_ranking_list p_ranking_list = new P_ranking_list();
            arrayList.clear();
            try {
                ArrayList<Fund_rankingdata_bean> arrayList1 = new ArrayList<Fund_rankingdata_bean>();
                arrayList1 = p_ranking_list.p_rangking_list(result);
                for (Fund_rankingdata_bean Fs : arrayList1) {
                    arrayList.add(Fs);
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
        }
    };

    public void initdata() {

        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        adapter=new Rv_rangking_list_adapter(arrayList);

        network=new Network();
        network.Loadhtpp(handler,url,what);
        recyclerView.setAdapter(adapter);
    }

    public void initview(){
        TextView day=rootView.findViewById(R.id.tv_hday);
        TextView week=rootView.findViewById(R.id.tv_hweek);
        TextView mouth=rootView.findViewById(R.id.tv_hmonth);
        TextView threemouth=rootView.findViewById(R.id.tv_hthree_month);
        TextView sixmonth=rootView.findViewById(R.id.tv_hsix_month);
        TextView thisyear=rootView.findViewById(R.id.tv_hthis_year);
        TextView year=rootView.findViewById(R.id.tv_hyear);
        TextView twoyear=rootView.findViewById(R.id.tv_htwo_year);
        TextView max=rootView.findViewById(R.id.tv_hmax);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                     case R.id.tv_hday:
                         order("day");
                         break;
                    case R.id.tv_hweek:
                        order("week");
                        break;
                    case R.id.tv_hmonth:
                        order("month");
                        break;
                    case R.id.tv_hthree_month:
                        order("three_month");
                        break;
                    case R.id.tv_hsix_month:
                        order("six_month");
                        break;
                    case R.id.tv_hthis_year:
                        order("this_year");
                        break;
                    case R.id.tv_hyear:
                        order("year");
                        break;
                    case R.id.tv_htwo_year:
                        order("two_year");
                        break;
                    case R.id.tv_hmax:
                        order("max");
                        break;

                }

            }
        };
        day.setOnClickListener(onClickListener);
        week.setOnClickListener(onClickListener);
        mouth.setOnClickListener(onClickListener);
        threemouth.setOnClickListener(onClickListener);
        sixmonth.setOnClickListener(onClickListener);
        thisyear.setOnClickListener(onClickListener);
        year.setOnClickListener(onClickListener);
        twoyear.setOnClickListener(onClickListener);
        max.setOnClickListener(onClickListener);
    }
    public void sqldata(){
        LitePalDB litePal=new LitePalDB("fund_ranking",1);
        litePal.addClassName(com.zou.fund.data.Fund_rankingdata_bean.class.getName());
        LitePal.use(litePal);
    }
    public  void order(String column){
        quarylist= DataSupport.order(column+" desc").limit(200).find(Fund_rankingdata_bean.class);
        arrayList.clear();
        for (Fund_rankingdata_bean Fs:quarylist){
            arrayList.add(Fs);}
            adapter.notifyDataSetChanged();
        Toast.makeText(context, "排序", Toast.LENGTH_LONG).show();
    }
}
