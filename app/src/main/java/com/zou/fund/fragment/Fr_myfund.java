package com.zou.fund.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zou.fund.MainActivity;
import com.zou.fund.R;
import com.zou.fund.activity.Activity_fund;
import com.zou.fund.adapter.Rv_myfund_adapter;
import com.zou.fund.data.My_fund_bean;
import com.zou.fund.parse.P_fund;
import com.zou.fund.parse.P_myfund;
import com.zou.fund.sqlbean.SQL_myfund;
import com.zou.fund.util.Network;
import com.zou.fund.util.SqlHelper;

import org.litepal.LitePal;
import org.litepal.LitePalDB;
import org.litepal.crud.DataSupport;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouyu on 2018/1/16 0016.
 */

public class Fr_myfund extends Fragment {
    ArrayList<My_fund_bean> adapterarrayList = new ArrayList<>();
    Context context;
    View rootView;
    RecyclerView recyclerView;
    EditText et_myfund_code;
    EditText et_myfund_num;
    EditText et_myfund_price;
    EditText et_myfund_charge;
    Button ib_myfund_save;
    ImageButton imb_add_myfund;
    private RecyclerView.LayoutManager mLayoutManager;
    String inputcode;
    double inputnum = 0;
    double inputcost = 0;
    double inputcharge=0;
    Activity activity;
    Network network;
    SqlHelper sqlHelper;
    Rv_myfund_adapter adapter;
    List<String> jjjz_list=new ArrayList<>();
    List<SQL_myfund> querydatalist; //查询结果\
    PopupWindow window;
    int what;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fr_myfund, container, false);
        context = MainActivity.getContext();
        context = this.getActivity();
        activity = getActivity();
        imb_add_myfund = rootView.findViewById(R.id.imb_add_myfund);
        imb_add_myfund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_fund_popwindows();
            }
        });
        network = Network.getnetwork();
        initdata();
        initview();
        return rootView;

    }
    public void initdata() {
        sqlHelper=new SqlHelper("myfund",com.zou.fund.sqlbean.SQL_myfund.class,2);
        Fresco.getImagePipeline().clearCaches();
        adapterarrayList.clear();
        sqlHelper.creatdatabase();
        querydatalist= (List<SQL_myfund>) sqlHelper.querydata();

        if (querydatalist != null) {
            for (int i = 0; i < querydatalist.size(); i++) {
                String url = "http://fund.eastmoney.com/" + querydatalist.get(i).getMyfund_code() + ".html";
                network.Loadhtpp(handler, url, what);
            }
        }
    }
    public void initview() {
        recyclerView = rootView.findViewById(R.id.rv_my_fund);
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        adapter = new Rv_myfund_adapter(adapterarrayList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new Rv_myfund_adapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(),Activity_fund.class);
                String fundcode=adapterarrayList.get(position).getMyfund_code();
                intent.putExtra("code",fundcode);
                startActivity(intent);
            }
            @Override
            public void onItemLongClick(View view, final int position) {      //长按删除
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("想要进行的操作？").setNeutralButton("删除",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String fundcode=adapterarrayList.get(position).getMyfund_code();//得到长按项的基金代码
                        Log.d("myfund","删除的代码"+ fundcode);
                        sqlHelper.delete("myfund_code=?",fundcode);
                        initdata();
                        Toast.makeText(context, "已删除", Toast.LENGTH_LONG).show();
                    }
                }).setPositiveButton("买入", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final EditText editText=new EditText(context);
                        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                        AlertDialog alertDialog=builder.setMessage("买入").setView(editText).setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                double inputbuy=Double.parseDouble(editText.getText().toString());
                                String fundcode=adapterarrayList.get(position).getMyfund_code();
                                double charge=querydatalist.get(position).getMyfund_charge();
                                double price=Double.parseDouble( jjjz_list.get(position));
                                double buy=inputbuy*(1-charge/100);  //除去手续费的购买金额
                                double newnum=buy/price;        //新购份数
                                double allnum=querydatalist.get(position).getMyfund_num()+newnum;
                                Log.d("myfund",querydatalist.get(position).getMyfund_num()+"原有"+ position+"购买分数"+newnum+"基金净值"+jjjz_list.get(position));
                                double pcs=(querydatalist.get(position).getMyfund_cost()*querydatalist.get(position).getMyfund_num()+buy)/allnum;
                                ContentValues values = new ContentValues();
                                values.put("myfund_num", allnum);
                                values.put("myfund_cost", pcs);
                                Log.d("myfund","买入"+ allnum+"成本单价"+pcs);
                                sqlHelper.updata(values,"myfund_code = ?",fundcode);
                                querydatalist.clear();
                                jjjz_list.clear();
                                initdata();        //刷新数据
                            }
                        }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).create();
                        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        alertDialog.show();
                    }
                }).create().show();
            }
        });
    }
    public void add_fund_popwindows() {
        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(context).inflate(R.layout.popwindows_addfund, null, false);
        window = new PopupWindow(contentView, 1000, 600, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(0xff2581ff));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        et_myfund_code = contentView.findViewById(R.id.et_myfund_code);
        et_myfund_num = contentView.findViewById(R.id.et_myfund_num);
        et_myfund_num.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        et_myfund_price = contentView.findViewById(R.id.et_myfund_price);
        et_myfund_price.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        et_myfund_charge=contentView.findViewById(R.id.et_myfund_charge);
        et_myfund_charge.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        ib_myfund_save = contentView.findViewById(R.id.ib_myfund_save);


        ib_myfund_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savefund();
            }
        });

    }           //弹出的输入框
    public void savefund(){
        try {
            inputcode = et_myfund_code.getText().toString();
            int i=0;
            for (i=inputcode.length();inputcode.length()<6;i++){//补充代码前面的0
                inputcode="0"+inputcode;
            }
            inputnum = Double.parseDouble(et_myfund_num.getText().toString());
            inputcost = Double.parseDouble(et_myfund_price.getText().toString());
            inputcharge=Double.parseDouble(et_myfund_charge.getText().toString());
        } catch (NumberFormatException ex) {
        }
        querydatalist.clear();
        querydatalist= (List<SQL_myfund>) sqlHelper.querydata();
        int flg = 1;  //基金是否存在状态量
        for (int i = 0; i < querydatalist.size(); i++) {
            if (querydatalist.get(i).getMyfund_code().equals(inputcode)) {//判断新输入的基金是否已存在
                flg = 0;  //该基金已存在改为0
                break;
            }
        }
        if (flg==0){    //基金已存在，修改
            ContentValues values = new ContentValues();
            values.put("myfund_num", inputnum);
            values.put("myfund_cost", inputcost);
            sqlHelper.updata(values,"myfund_code = ?",inputcode);
            Toast.makeText(context, "修改成功", Toast.LENGTH_LONG).show();
            initdata();        //刷新数据
            window.dismiss();

        }
        if (flg == 1) {   //不存在则新建
            SQL_myfund sql_myfund = new SQL_myfund(inputcode, inputnum, inputcost,inputcharge);
            if (sql_myfund.save()) {
                Toast.makeText(context, "保存成功", Toast.LENGTH_LONG).show();
                initdata();        //刷新数据
                window.dismiss();
            } else Toast.makeText(context, "保存失败", Toast.LENGTH_LONG).show();
        }
    }  //保存新添加的数据


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s1 = (String) msg.obj;
            P_fund p_fund=new P_fund(s1);
            p_fund.parse();
            P_myfund my_fund = new P_myfund(s1);
            ArrayList<My_fund_bean> arrayList = my_fund.parse();
            My_fund_bean my_fund_bean = arrayList.get(0);//获取解析出来的基金数据

            for (SQL_myfund sql_myfund : querydatalist) {
                Log.d("55555", "数据库代码" + sql_myfund.getMyfund_code() + "解析代码" + my_fund_bean.getMyfund_code());

                while (sql_myfund.getMyfund_code().equals(my_fund_bean.getMyfund_code())) {
                    String jzzf=my_fund_bean.getMyfund_price();
                    String s[]=jzzf.split(",");  //分离解析出来的基金净值和涨幅大小
                    String jz=s[0];
                    String zf=s[1];
                    jjjz_list.add(jz);
                    DecimalFormat df = new DecimalFormat("0.00 ");//控制小数点位数
                    Double fund_num = sql_myfund.getMyfund_num();
                    Double price = Double.parseDouble(jz);
                    Double myfund_worth = price * fund_num;     //计算持仓总量；基金净值乘分数
                    Double cost = fund_num * sql_myfund.getMyfund_cost();       //计算持仓成本
                    Double yield = (myfund_worth - cost) / cost * 100;
                    Double shouyi = myfund_worth - cost;
                    String yieldstring;
                    if (shouyi > 0) {
                        yieldstring = "<font ><big>" + df.format(shouyi) + "</big></font>" + "<font ><small>" + df.format(yield) + "%<small></font>";
                    } else {
                        yieldstring = "<font color='green'><big>" + df.format(shouyi) + "</big></font>" + "<font color='green'><small>" + df.format(yield) + "%<small></font>";
                    }
                    double zfd=Double.parseDouble(zf.substring(0,2));
                    if (zfd>0){
                        jzzf = "<font ><big>" + jz + "</big></font>" + "<font ><small>" + zf + "%<small></font>";
                    }else {
                        jzzf= "<font color='green'><big>" + jz + "</big></font>" + "<font color='green'><small>" +zf + "%<small></font>";
                    }
                    my_fund_bean.setMyfund_price(jzzf);
                    my_fund_bean.setMyfund_worth(myfund_worth);
                    my_fund_bean.setMyfund_cost(sql_myfund.getMyfund_cost());
                    my_fund_bean.setMyfund_yield(yieldstring);

                    adapterarrayList.add(my_fund_bean);
                    Log.d("解析结果", "基金名称" + my_fund_bean.getMyfund_name() + my_fund_bean.getMyfund_code() + my_fund_bean.getMyfund_worth() +
                            my_fund_bean.getMyfund_cost() + my_fund_bean.getMyfund_imurl() + my_fund_bean.getMyfund_type());
                    break;
                }
            }


            adapter.notifyDataSetChanged();
        }

    };


}
