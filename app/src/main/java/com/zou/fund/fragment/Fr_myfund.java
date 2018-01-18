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

import com.zou.fund.MainActivity;
import com.zou.fund.R;
import com.zou.fund.adapter.Rv_myfund_adapter;
import com.zou.fund.data.My_fund_bean;
import com.zou.fund.parse.Chicang;
import com.zou.fund.parse.My_fund;
import com.zou.fund.util.Network;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by zouyu on 2018/1/16 0016.
 */

public class Fr_myfund extends Fragment {
    ArrayList<My_fund_bean> arrayList=new ArrayList<>();
    Context context;
    View rootView;
    RecyclerView recyclerView;
    My_fund_bean my_fund_bean;
    private RecyclerView.LayoutManager mLayoutManager;

    String imurl;

    Network network;
    int what;
    int i=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fr_main, container, false);
        context= MainActivity.getContext();
        context=this.getActivity();

        if (network==null){
            network=new Network();
        }
        String url="http://fund.eastmoney.com/161725.html";
        network.Loadhtpp(handler,url,what);
        return rootView;
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s1= (String) msg.obj;
            Log.d("55555","返回成功");
            My_fund my_fund=new My_fund(s1);
           imurl =my_fund.parse();
            imurl="http://j4.dfcfw.com/charts/pic6/161725.png";
            Log.d("55555","解析成功"+imurl);
            initview();
            }

    };
    public void initview(){
        recyclerView=rootView.findViewById(R.id.rv_my_fund);
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        // 设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));
        my_fund_bean=new My_fund_bean("招商中证白酒指数","股票型","161725",111.5,imurl);
        arrayList.add(my_fund_bean);

            recyclerView.setAdapter(new Rv_myfund_adapter(arrayList));


    }
}
