package com.zou.fund.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zou.fastlibrary.utils.EditTextUtil;
import com.zou.fund.R;
import com.zou.fund.persenter.MyFund;
import com.zou.fund.view.activity.Activity_fund;
import com.zou.fund.adapter.Rv_myfund_adapter;
import com.zou.fund.databean.My_fund_bean;
import com.zou.fund.parse.P_fund;
import com.zou.fund.parse.P_myfund;
import com.zou.fund.sqlbean.SQL_myfund;
import com.zou.fund.util.Network;
import com.zou.fund.util.SqlHelper;
import com.zou.fund.view.activity.MainActivity;
import com.zou.fund.viewinterfaces.MyFundview;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouyu on 2018/1/16 0016.
 */

public class Fr_myfund extends Fragment implements MyFundview{
    ArrayList<My_fund_bean> adapterarrayList = new ArrayList<>();
    Context context;
    View rootView;
    RecyclerView recyclerView;
    EditText et_myfund_code;
    EditText et_myfund_num;
    EditText et_myfund_price;
    EditText et_myfund_charge;
    Button ib_myfund_save;
    Button imb_add_myfund;
    Activity activity;
    Network network;
    SqlHelper sqlHelper;
    Rv_myfund_adapter adapter;
    List<String> jjjz_list=new ArrayList<>();
    List<SQL_myfund> querydatalist =new ArrayList<>(); //查询结果\
    PopupWindow window;
    MyFund myFund;

    EditText editText;   //搜索代码输入框
    ImageButton imageButton;  //搜索图标

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fr_myfund, container, false);
        context = this.getActivity();
        activity = getActivity();
        editText=rootView.findViewById(R.id.ed_main_seach);
        imb_add_myfund = rootView.findViewById(R.id.imb_add_myfund);
        imb_add_myfund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_fund_popwindows();
            }
        });
        network = Network.getnetwork();
        myFund=new MyFund(this);
        myFund.getdata();
        adapterarrayList.clear();
        Fresco.getImagePipeline().clearCaches();
        imageButton=rootView.findViewById(R.id.ib_main_seach);
        editText.clearFocus();
        InputMethodManager imm = (InputMethodManager)context. getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH){
                    Intent intent=new Intent(getActivity(),Activity_fund.class);
                    String fundcode=editText.getText().toString();
                    intent.putExtra("code",fundcode);
                    startActivity(intent);
                }
                return false;
            }
        });
        initview();
        return rootView;

    }
    public void initview() {

        recyclerView = rootView.findViewById(R.id.rv_my_fund);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
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
                        myFund.getdata();
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
                                myFund.getdata();       //刷新数据
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

    /**
     * 添加基金弹窗
     */
    public void add_fund_popwindows() {
        final WindowManager.LayoutParams params=getActivity().getWindow().getAttributes();
        params.alpha=0.7f;
        getActivity().getWindow().setAttributes(params);
        View contentView = LayoutInflater.from(context).inflate(R.layout.popwindows_addfund, null, false);
        window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setAnimationStyle(R.style.translucentAnimation);
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, -300);
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
                String inputcode;
                double inputnum = 0;
                double inputcost = 0;
                double inputcharge=0;

                inputcode = et_myfund_code.getText().toString();
                int i=0;
                for (i=inputcode.length();inputcode.length()<6;i++){//补充代码前面的0
                    inputcode="0"+inputcode;
                }
                try {
                    inputnum = Double.parseDouble(et_myfund_num.getText().toString());
                    inputcost = Double.parseDouble(et_myfund_price.getText().toString());
                    inputcharge=Double.parseDouble(et_myfund_charge.getText().toString());
                    myFund.savefund(inputcode,inputnum,inputcost,inputcharge);
                }catch (NumberFormatException e){
                    Toast.makeText(getContext(),"信息填写不完整",Toast.LENGTH_SHORT).show();
                }
                params.alpha=1f;
                getActivity().getWindow().setAttributes(params);
                window.dismiss();
            }
        });
    }
    @Override
    public void refreshdate(My_fund_bean my_fund_bean) {
        adapterarrayList.add(my_fund_bean);
        adapter.notifyDataSetChanged();
    }
}
