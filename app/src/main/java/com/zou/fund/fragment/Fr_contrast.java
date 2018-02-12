package com.zou.fund.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zou.fund.MainActivity;
import com.zou.fund.R;
import com.zou.fund.adapter.Rv_contrast_adapter;

import com.zou.fund.data.Fund_position_arraylist;
import com.zou.fund.parse.Chicang;
import com.zou.fund.sqlbean.SQL_fund_cc_bean;
import com.zou.fund.util.Network;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;



/**
 * Created by 邹远君 on 2017/12/15.
 */

public class Fr_contrast extends Fragment {
    Context context;
    public RecyclerView recyclerView;
    public Rv_contrast_adapter rv_contrast_adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    View rootView;
    Network network;
    int what;
    ArrayList<SQL_fund_cc_bean> arrayList=new ArrayList();

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s1= (String) msg.obj;
            Log.d("55555","请求成功，返回数据");
            try {
                new Chicang(s1);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    };
    String url="http://fund.eastmoney.com/Data/Fund_JJJZ_Data.aspx?t=1&lx=1&letter=&gsid=&text=&sort=zdf,desc&page=1,9999&feature=|&dt=1514296528372&atfc=&onlySale=0";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fr_contrast, container, false);
        context= this.getActivity();
        initData();
        initView();
        if (network==null){
            network=new Network();
        }
       // network.Loadhtpp(handler,url,what);
        return rootView;
    }



    private void initData() {
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rv_contrast_adapter = new Rv_contrast_adapter(getData());
    }

    private void initView() {
        recyclerView = rootView.findViewById(R.id.rv_contrast);
        // 设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
       //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));
        // 设置adapter
        recyclerView.setAdapter(rv_contrast_adapter);
    }

    private ArrayList<Fund_position_arraylist> getData() {
        ArrayList<Fund_position_arraylist> data = new ArrayList<Fund_position_arraylist>();
        data.add(new Fund_position_arraylist(1,519772));
        data.add(new Fund_position_arraylist(2,519772));
        data.add(new Fund_position_arraylist(3,519772));
        return data;
    }


}
