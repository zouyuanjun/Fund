package com.zou.fund.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zou.fund.R;
import com.zou.fund.activity.Activity_fund;
import com.zou.fund.adapter.Rv_rangking_list_adapter;
import com.zou.fund.customview.ScrollRecyclerView;
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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

    int fundallnum;
    int fundsznum;
    int fundxdnum;
    TextView fundall;
    TextView fundsz;
    TextView fundxd;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fr_rangking_list, container, false);
        recyclerView=rootView.findViewById(R.id.rv_rangking_list);
        context=this.getActivity();
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            initview();
            initdata();
        }
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
            progressBar.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
            order("day");
            getnum("day");
            settext();
        }
    };

    public void initdata() {

        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        adapter=new Rv_rangking_list_adapter(arrayList);
        sqldata();
        network=Network.getnetwork();
        network.Loadhtpp(handler,url,what);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new Rv_rangking_list_adapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String fundcode=arrayList.get(position).getRangking_fundcode();
                Log.d("ddd","跳转ACtivity"+fundcode);
                Intent intent=new Intent(getActivity(),Activity_fund.class);
                intent.putExtra("code",fundcode);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
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
        progressBar=rootView.findViewById(R.id.fund_ranking_progressBar);

         fundall=rootView.findViewById(R.id.tv_fundall);
         fundsz=rootView.findViewById(R.id.tv_fundsz);
         fundxd=rootView.findViewById(R.id.tv_fundxd);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                     case R.id.tv_hday:
                         order("day");
                         getnum("day");
                         settext();
                         break;
                    case R.id.tv_hweek:
                        order("week");
                        getnum("week");
                        settext();
                        break;
                    case R.id.tv_hmonth:
                        order("month");
                        getnum("month");
                        settext();
                        break;
                    case R.id.tv_hthree_month:
                        order("three_month");
                        getnum("three_month");
                        settext();
                        break;
                    case R.id.tv_hsix_month:
                        order("six_month");
                        getnum("six_month");
                        settext();
                        break;
                    case R.id.tv_hthis_year:
                        order("this_year");
                        getnum("this_year");
                        settext();
                        break;
                    case R.id.tv_hyear:
                        order("year");
                        getnum("year");
                        settext();
                        break;
                    case R.id.tv_htwo_year:
                        order("two_year");
                        getnum("two_year");
                        settext();
                        break;
                    case R.id.tv_hmax:
                        order("max");
                        getnum("max");
                        settext();
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
    public  void order(final String column){
//        quarylist= DataSupport.order(column+" desc").find(Fund_rankingdata_bean.class);
//        Log.d("Frrangking","查询到的排序数据大小"+quarylist.size());
//        arrayList.clear();
//        for (Fund_rankingdata_bean Fs:quarylist){
//            arrayList.add(Fs);}

        Collections.sort(arrayList, new Comparator<Fund_rankingdata_bean>() {
            @Override
            public int compare(Fund_rankingdata_bean o1, Fund_rankingdata_bean o2) {
                switch (column){
                    case "day":
                        return o2.getDay().compareTo(o1.getDay());

                    case "week":
                        return o2.getWeek().compareTo(o1.getWeek());
                    case "month":
                        return o2.getMonth().compareTo(o1.getMonth());
                    case "three_month":
                        return o2.getThree_month().compareTo(o1.getThree_month());
                    case "six_month":
                        return o2.getSix_month().compareTo(o1.getSix_month());
                    case "this_year":
                        return o2.getThis_year().compareTo(o1.getThis_year());
                    case "year":
                        return o2.getYear().compareTo(o1.getYear());
                    case "two_year":
                        return o2.getTwo_year().compareTo(o1.getTwo_year());
                    case "max":
                        return o2.getMax().compareTo(o1.getMax());
                }
                return o2.getYear().compareTo(o1.getYear());
            }
        });

            adapter.notifyDataSetChanged();
      //  Toast.makeText(context, "排序", Toast.LENGTH_LONG).show();
    }

    public void getnum(String column){
        fundallnum=arrayList.size();
        switch (column){
            case "day":
                fundsznum=0;
                fundxdnum=0;
                for (Fund_rankingdata_bean f:arrayList){
                    if (f.getDay()>0){
                        fundsznum++;
                    }
                    if (f.getDay()<0){
                        fundxdnum++;
                        Log.d("ddd",fundxdnum+"");
                    }
                }
                break;
            case "week":
                fundsznum=0;
                fundxdnum=0;
                for (Fund_rankingdata_bean f:arrayList){
                    if (f.getWeek()>0){
                        fundsznum++;
                    }
                    if (f.getWeek()<0){
                        fundxdnum++;
                    }
                }
                break;
            case "month":
                fundsznum=0;
                fundxdnum=0;
                for (Fund_rankingdata_bean f:arrayList){
                    if (f.getMonth()>0){
                        fundsznum++;
                    }
                    if (f.getMonth()<0){
                        fundxdnum++;
                    }
                }
                break;

            case "three_month":
                fundsznum=0;
                fundxdnum=0;
                for (Fund_rankingdata_bean f:arrayList){
                    if (f.getThree_month()>0){
                        fundsznum++;
                    }
                    if (f.getThree_month()<0){
                        fundxdnum++;
                    }
                }
                break;
            case "six_month":
                fundsznum=0;
                fundxdnum=0;
                for (Fund_rankingdata_bean f:arrayList){
                    if (f.getSix_month()>0){
                        fundsznum++;
                    }
                    if (f.getSix_month()<0){
                        fundxdnum++;
                    }
                }
                break;
            case "this_year":
                fundsznum=0;
                fundxdnum=0;
                for (Fund_rankingdata_bean f:arrayList){
                    if (f.getThis_year()>0){
                        fundsznum++;
                    }
                    if (f.getThis_year()<0){
                        fundxdnum++;
                    }
                }
                break;
            case "year":
                fundsznum=0;
                fundxdnum=0;
                for (Fund_rankingdata_bean f:arrayList){
                    if (f.getYear()>0){
                        fundsznum++;
                    }
                    if (f.getYear()<0){
                        fundxdnum++;
                    }
                }
                break;
            case "two_year":
                fundsznum=0;
                fundxdnum=0;
                for (Fund_rankingdata_bean f:arrayList){
                    if (f.getTwo_year()>0){
                        fundsznum++;
                    }
                    if (f.getTwo_year()<0){
                        fundxdnum++;
                    }
                }
                break;
            case "max":
                fundsznum=0;
                fundxdnum=0;
                for (Fund_rankingdata_bean f:arrayList){
                    if (f.getMax()>0){
                        fundsznum++;
                    }
                    if (f.getMax()<0){
                        fundxdnum++;
                    }
                }
                break;
        }
    }
    public void settext(){
        fundall.setText(fundallnum+"个");
        fundsz.setText(fundsznum+"个");
        fundxd.setText(fundxdnum+"个");
        Log.d("ddd","设置下跌数"+fundxdnum+"");
    }
}
