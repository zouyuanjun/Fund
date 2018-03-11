package com.zou.fund.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zou.fund.R;
import com.zou.fund.adapter.Lv_fundchicang_adapter;
import com.zou.fund.data.Fund;
import com.zou.fund.data.Fund_chicang;
import com.zou.fund.parse.P_fund;
import com.zou.fund.util.Network;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by 邹远君 on 2018/3/8 0008.
 */

public class Activity_fund extends AppCompatActivity {
    TextView title;
    TextView type;
    TextView fund_code;
    TextView zuixjz;
    TextView zhangfu;
    TextView gsjz;
    TextView gszhangfu;
    TextView gstime;
    ArrayList<Fund_chicang> fund_chicang=new ArrayList<>();
    TextView chicangsun;
    ListView listView;
    SimpleDraweeView img_gsjz;
    Network network;
    Lv_fundchicang_adapter lv_fundchicang_adapter;

    ArrayList<Fund> arrayList=new ArrayList<>();
    Fund fund;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this.getApplicationContext());
        setContentView(R.layout.activity_fund);
        initdata();
        initview();
    }
    private void initview(){
        zuixjz=findViewById(R.id.tv_zuixjz);
        zhangfu=findViewById(R.id.tv_zhangfu);
        type=findViewById(R.id.tv_fundtype);
        gsjz=findViewById(R.id.tv_gsjz);
        gszhangfu=findViewById(R.id.tv_gszhangfu);
        gstime=findViewById(R.id.tv_gstime);
        img_gsjz=findViewById(R.id.img_gszhangfu);
        listView=findViewById(R.id.fund_cc_listview);
        chicangsun=findViewById(R.id.tv_chicangsum);
        lv_fundchicang_adapter=new Lv_fundchicang_adapter(fund_chicang);
        listView.setAdapter(lv_fundchicang_adapter);
    }
    private void initdata(){
        String code=getIntent().getStringExtra("code");
        network=new Network();
        network.Loadhtpp(handler,"http://fund.eastmoney.com/" + code + ".html",1);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s1 = (String) msg.obj;
            P_fund p_fund=new P_fund(s1);
            fund=p_fund.parse().get(0);
            zuixjz.setText(fund.getZuixjz());
            zhangfu.setText(fund.getZhangfu());
            type.setText(fund.getType());
            gsjz.setText(fund.getGsjz());
            gszhangfu.setText(fund.getGszhangfu());
            gstime.setText(fund.getGstime());
            img_gsjz.setImageURI(Uri.parse(fund.getGsimageurl()));
            Log.d("图片地址",fund.getGsimageurl());
            chicangsun.setText(fund.getChicangsun());
            fund_chicang.clear();
            for (Fund_chicang f:fund.getArrayList()){
                fund_chicang.add(f);
            };
            lv_fundchicang_adapter.notifyDataSetChanged();
        }
    };
}
