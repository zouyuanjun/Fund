package com.zou.fund.persenter;

import android.content.ContentValues;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.zou.fund.databean.My_fund_bean;
import com.zou.fund.parse.P_fund;
import com.zou.fund.parse.P_myfund;
import com.zou.fund.sqlbean.SQL_myfund;
import com.zou.fund.util.Network;
import com.zou.fund.util.SqlHelper;
import com.zou.fund.view.fragment.Fr_myfund;
import com.zou.fund.viewinterfaces.MyFundview;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MyFund implements MyFundview {
    SqlHelper sqlHelper;
    Fr_myfund fr_myfund;
    Network network = Network.getnetwork();
    /**
     * 获取保存在sqlite中的数据
     */
    List<SQL_myfund> sqLiteList;

    public MyFund(Fr_myfund fr_myfund) {
        sqlHelper = new SqlHelper("myfund", com.zou.fund.sqlbean.SQL_myfund.class, 2);
        sqlHelper.creatdatabase();
        sqLiteList = (List<SQL_myfund>) sqlHelper.querydata();
        this.fr_myfund = fr_myfund;
    }

    @Override
    public void refreshdate(My_fund_bean my_fund_bean) {
        fr_myfund.refreshdate(my_fund_bean);
    }

    @Override
    public void refreshalldate() {
        fr_myfund.refreshalldate();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s1 = (String) msg.obj;
            int what=msg.what;
            P_myfund my_fund = new P_myfund(s1);
            My_fund_bean my_fund_bean =my_fund.parse();//获取解析出来的基金数据
            for (SQL_myfund sql_myfund : sqLiteList) {
                while (sql_myfund.getMyfund_code().equals(my_fund_bean.getMyfund_code())) {
                    String zf = my_fund_bean.getMyfund_jjzf();
                    String jz = my_fund_bean.getMyfund_jjjz();
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
                    double zfd = Double.parseDouble(zf.substring(0, 2));
                    my_fund_bean.setMyfund_worth(myfund_worth);
                    my_fund_bean.setMyfund_cost(sql_myfund.getMyfund_cost());
                    my_fund_bean.setMyfund_yield(yieldstring);

                    refreshdate(my_fund_bean);


                    Log.d("解析结果", "基金名称" + my_fund_bean.getMyfund_name() + my_fund_bean.getMyfund_code() + my_fund_bean.getMyfund_worth() +
                            my_fund_bean.getMyfund_cost() + my_fund_bean.getMyfund_imurl() + my_fund_bean.getMyfund_type());
                    break;
                }
            }
        }

    };

    /**
     * 查询基金信息
     */
    public void getdata() {
        if (sqLiteList != null) {
            for (int i = 0; i < sqLiteList.size(); i++) {
                String url = "http://fund.eastmoney.com/" + sqLiteList.get(i).getMyfund_code() + ".html";
                network.Loadhtpp(handler, url, 1);
            }
        }
    }

    /**
     * 保存新添加的数据
     */
    public boolean savefund(String inputcode, double inputnum, double inputcost, double inputcharge) {
        boolean issave=false;
        int flg = 1;  //基金是否存在状态量
        for (int i = 0; i < sqLiteList.size(); i++) {
            if (sqLiteList.get(i).getMyfund_code().equals(inputcode)) {//判断新输入的基金是否已存在
                flg = 0;  //该基金已存在改为0
                break;
            }
        }
        if (flg == 0) {    //基金已存在，修改
            ContentValues values = new ContentValues();
            values.put("myfund_num", inputnum);
            values.put("myfund_cost", inputcost);
            sqlHelper.updata(values, "myfund_code = ?", inputcode);
            sqLiteList.clear();
            sqLiteList = (List<SQL_myfund>) sqlHelper.querydata();
            refreshalldate();   //通知activity清空list
            getdata();
        }
        if (flg == 1) {   //不存在则新建
            SQL_myfund sql_myfund = new SQL_myfund(inputcode, inputnum, inputcost, inputcharge);
            if (sql_myfund.save()) {
                sqLiteList.clear();
                sqLiteList = (List<SQL_myfund>) sqlHelper.querydata();
                String url = "http://fund.eastmoney.com/" + inputcode + ".html";
                network.Loadhtpp(handler, url, 2);
                issave=true;
            } else {
                issave=false;
            }
        }
        return issave;
    }
    public void deletefund(String fundcode){
        sqlHelper.delete("myfund_code=?",fundcode);
    }

    /**
     * 追加买入基金
     * @param fundcode  要买入的基金
     * @param inputbuy    买入金额
     */
    public void buyfund(String fundcode, double inputbuy,double jjjz){



        double charge = 0;    //手续费
        double nownum=0;
        double myfund_cost=0;
        for ( SQL_myfund sql_myfund:sqLiteList ){
            if (sql_myfund.getMyfund_code().equals(fundcode)){
                charge=sql_myfund.getMyfund_charge();
                nownum=sql_myfund.getMyfund_num();
                myfund_cost=sql_myfund.getMyfund_cost();
            }
        }
        double buy=inputbuy*(1-charge/100);  //除去手续费的购买金额
        double newnum=buy/jjjz;        //新购份数
        double allnum=newnum+nownum;
        //计算平均单价
        double pcs=myfund_cost*nownum+inputbuy/allnum;
        ContentValues values = new ContentValues();
        values.put("myfund_num", allnum);
        values.put("myfund_cost", pcs);
        Log.d("myfund","买入"+ allnum+"成本单价"+pcs);
        sqlHelper.updata(values,"myfund_code = ?",fundcode);
        sqLiteList.clear();
        sqLiteList = (List<SQL_myfund>) sqlHelper.querydata();
        refreshalldate();   //通知activity清空list
        getdata();
    }
}
