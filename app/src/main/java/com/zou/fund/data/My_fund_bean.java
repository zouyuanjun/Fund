package com.zou.fund.data;

import android.widget.TextView;

/**
 * Created by 邹远君 on 2018/1/18 0018.
 */

public class My_fund_bean {
    String myfund_name;             //持仓基金名称
    String myfund_type;          //类型
    String myfund_code;          //基金代码
    Double myfund_num;       //持仓数量
    Double myfund_price;       //持仓数量
    String myfund_imurl;        //估算净值图片url

    public My_fund_bean(String myfund_name, String myfund_type, String myfund_code, Double myfund_num, Double myfund_price, String myfund_imurl) {
        this.myfund_name = myfund_name;
        this.myfund_type = myfund_type;
        this.myfund_code = myfund_code;
        this.myfund_num = myfund_num;
        this.myfund_price = myfund_price;
        this.myfund_imurl = myfund_imurl;
    }

    public String getMyfund_name() {
        return myfund_name;
    }

    public void setMyfund_name(String myfund_name) {
        this.myfund_name = myfund_name;
    }

    public String getMyfund_type() {
        return myfund_type;
    }

    public void setMyfund_type(String myfund_type) {
        this.myfund_type = myfund_type;
    }

    public String getMyfund_code() {
        return myfund_code;
    }

    public void setMyfund_code(String myfund_code) {
        this.myfund_code = myfund_code;
    }

    public Double getMyfund_num() {
        return myfund_num;
    }

    public void setMyfund_num(Double myfund_num) {
        this.myfund_num = myfund_num;
    }

    public Double getMyfund_price() {
        return myfund_price;
    }

    public void setMyfund_price(Double myfund_price) {
        this.myfund_price = myfund_price;
    }

    public String getMyfund_imurl() {
        return myfund_imurl;
    }

    public void setMyfund_imurl(String myfund_imurl) {
        this.myfund_imurl = myfund_imurl;
    }
}