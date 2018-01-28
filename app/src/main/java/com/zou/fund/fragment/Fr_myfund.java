package com.zou.fund.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zou.fund.MainActivity;
import com.zou.fund.R;
import com.zou.fund.adapter.Rv_myfund_adapter;
import com.zou.fund.data.My_fund_bean;
import com.zou.fund.parse.P_myfund;
import com.zou.fund.sqlbean.SQL_myfund;
import com.zou.fund.util.Network;

import org.litepal.LitePal;
import org.litepal.LitePalDB;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouyu on 2018/1/16 0016.
 */

public class Fr_myfund extends Fragment {
    ArrayList<My_fund_bean> adapterarrayList=new ArrayList<>();
    Context context;
    View rootView;
    RecyclerView recyclerView;
    EditText et_myfund_code;
    EditText et_myfund_num;
    EditText et_myfund_price;
    Button ib_myfund_save;

    ImageButton imb_add_myfund;
    private RecyclerView.LayoutManager mLayoutManager;

    int inputcode;
    double inputnum=0;
    double inputprice=0;
    Activity activity;
    Network network;
    LitePalDB litePalDB;
    Rv_myfund_adapter adapter;
    List<SQL_myfund> list; //查询结果
    int what;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fr_myfund, container, false);
        context= MainActivity.getContext();
        context=this.getActivity();
        activity=getActivity();
        imb_add_myfund=rootView.findViewById(R.id.imb_add_myfund);
        imb_add_myfund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popwindows();
            }
        });
        if (network==null){
            network=new Network();
        }
        initdata();
        initview();
        return rootView;

    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s1= (String) msg.obj;
            Log.d("55555","返回结果"+s1);
            P_myfund my_fund=new P_myfund(s1);
            ArrayList<My_fund_bean> arrayList =my_fund.parse();
            My_fund_bean my_fund_bean=arrayList.get(0);
//            for (SQL_myfund myfund:list){
//                while (myfund.getMyfund_code()==my_fund_bean.getMyfund_code()){
//                my_fund_bean.setMyfund_num(myfund.getMyfund_num());
//                my_fund_bean.setMyfund_price(myfund.getMyfund_price());
//                }
//            }
            adapterarrayList.add(my_fund_bean);
            Log.d("55555","基金名称"+my_fund_bean.getMyfund_name()+my_fund_bean.getMyfund_code()+my_fund_bean.getMyfund_num()+
            my_fund_bean.getMyfund_price()+my_fund_bean.getMyfund_imurl()+my_fund_bean.getMyfund_type());
            adapter.notifyDataSetChanged();
            }

    };
    public void initview(){
        recyclerView=rootView.findViewById(R.id.rv_my_fund);
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));
       // my_fund_bean=new My_fund_bean("招商中证白酒指数","股票型","161725",111.5,imurl);
       // arrayList.add(my_fund_bean);
        adapter=new Rv_myfund_adapter(adapterarrayList);
        recyclerView.setAdapter(adapter);


    }

    public void popwindows() {
        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(context).inflate(R.layout.popwindows_myfund, null, false);
        PopupWindow window = new PopupWindow(contentView, 1000, 600,true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(0xff2581ff));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);

         window.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
         et_myfund_code=contentView.findViewById(R.id.et_myfund_code);
         et_myfund_num=contentView.findViewById(R.id.et_myfund_num);
         et_myfund_num.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
         et_myfund_price=contentView.findViewById(R.id.et_myfund_price);
         et_myfund_price.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
         ib_myfund_save=contentView.findViewById(R.id.ib_myfund_save);


         ib_myfund_save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 inputcode=Integer.parseInt(et_myfund_code.getText().toString());
                 inputnum=Double.parseDouble(et_myfund_num.getText().toString());
                 inputprice=Double.parseDouble(et_myfund_price.getText().toString());
                 SQL_myfund sql_myfund=new SQL_myfund(inputcode ,inputnum,inputprice);
                 initdata();
                 if (sql_myfund.save()){
                     Toast.makeText(context, "保存成功", Toast.LENGTH_LONG).show();
                 }
                 else Toast.makeText(context, "保存失败", Toast.LENGTH_LONG).show();
             }
         });

    }
    public void initdata(){
        Fresco.getImagePipeline().clearCaches();
       // arrayList.clear();
        creatdatabase();
        querydata();
    }
    public void creatdatabase(){
        litePalDB=new LitePalDB("myfund",1);              //创建数据库
        litePalDB.addClassName(com.zou.fund.sqlbean.SQL_myfund.class.getName());
        LitePal.use(litePalDB);
    }
    public void querydata() {
        list = DataSupport.findAll(SQL_myfund.class);
        for (int i = 0; i < list.size(); i++) {
            String url = "http://fund.eastmoney.com/" + list.get(i).getMyfund_code() + ".html";

            network.Loadhtpp(handler, url, what);

        }
    }
}
