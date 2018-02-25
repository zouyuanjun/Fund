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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zou.fund.R;
import com.zou.fund.adapter.Rv_rangking_list_adapter;
import com.zou.fund.data.Fund_rankingdata_bean;
import com.zou.fund.parse.P_ranking_list;
import com.zou.fund.util.Network;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by 邹远君 on 2018/2/12 0012.
 */

public class Fr_rangking_list extends Fragment {
    Context context;
    View rootView;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Fund_rankingdata_bean> arrayList=new ArrayList();
    RecyclerView recyclerView;
    String url="http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=kf&ft=all&rs=&gs=0&sc=qjzf&st=desc&pi=1&pn=10000&dx=1";
    Network network;
    int what=1;
    Rv_rangking_list_adapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fr_rangking_list, container, false);
        recyclerView=rootView.findViewById(R.id.rv_rangking_list);
        context=this.getActivity();
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
                ArrayList<Fund_rankingdata_bean> arrayList1=new ArrayList<Fund_rankingdata_bean>();
                arrayList1=p_ranking_list.p_rangking_list(result);
                for (Fund_rankingdata_bean Fs:arrayList1){
                arrayList.add(Fs);}

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
        }
    };
    public void initdata(){

        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        adapter=new Rv_rangking_list_adapter(arrayList);
        recyclerView.setAdapter(adapter);
        network=new Network();
        network.Loadhtpp(handler,url,what);
    }
}
