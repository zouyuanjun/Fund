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

import com.zou.fund.R;
import com.zou.fund.adapter.Rv_contrast_adapter;
import com.zou.fund.data.Fund_cc_bean;
import com.zou.fund.data.Fund_position_arraylist;
import com.zou.fund.parse.Chicang;
import com.zou.fund.util.Network;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
    ArrayList<Fund_cc_bean> arrayList=new ArrayList();

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s1= (String) msg.obj;
            new Chicang(s1);
        }
    };
    String url="http://finance.sina.com.cn/fund/quotes/161725/bc.shtml";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         rootView= inflater.inflate(R.layout.fr_contrast, container, false);
        context=this.getActivity();
        initData();
        initView();
        if (network==null){
            network=new Network();
        }
        network.Loadhtpp(handler,url,what);
        return rootView;
    }



    private void initData() {
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rv_contrast_adapter = new Rv_contrast_adapter(getData());
    }

    private void initView() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_contrast);
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
