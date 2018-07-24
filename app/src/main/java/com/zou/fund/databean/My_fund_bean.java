package com.zou.fund.databean;

/**
 * Created by 邹远君 on 2018/1/18 0018.
 */

public class My_fund_bean {
    String myfund_name;             //持仓基金名称
    String myfund_type;          //类型
    String myfund_code;          //基金代码
    Double myfund_worth;       //持仓总量
    Double myfund_cost;       //持仓单价
    String myfund_imurl;        //估算净值图片url
    String myfund_yield;        //收益率
    String myfund_jjjz;        //基金净值
    String myfund_jjzf;         //基金涨幅

    public My_fund_bean(String myfund_name, String myfund_type, String myfund_code, Double myfund_worth, Double myfund_cost, String myfund_imurl, String myfund_yield, String myfund_jjjz, String myfund_jjzf) {
        this.myfund_name = myfund_name;
        this.myfund_type = myfund_type;
        this.myfund_code = myfund_code;
        this.myfund_worth = myfund_worth;
        this.myfund_cost = myfund_cost;
        this.myfund_imurl = myfund_imurl;
        this.myfund_yield = myfund_yield;
        this.myfund_jjjz = myfund_jjjz;
        this.myfund_jjzf = myfund_jjzf;
    }

    public String getMyfund_jjzf() {
        return myfund_jjzf;
    }

    public void setMyfund_jjzf(String myfund_jjzf) {
        this.myfund_jjzf = myfund_jjzf;
    }

    public String getMyfund_yield() {
        return myfund_yield;
    }

    public void setMyfund_yield(String myfund_yield) {
        this.myfund_yield = myfund_yield;
    }

    public String getMyfund_jjjz() {
        return myfund_jjjz;
    }

    public void setMyfund_jjjz(String myfund_jjjz) {
        this.myfund_jjjz = myfund_jjjz;
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

    public Double getMyfund_worth() {
        return myfund_worth;
    }

    public void setMyfund_worth(Double myfund_worth) {
        this.myfund_worth = myfund_worth;
    }

    public Double getMyfund_cost() {
        return myfund_cost;
    }

    public void setMyfund_cost(Double myfund_cost) {
        this.myfund_cost = myfund_cost;
    }

    public String getMyfund_imurl() {
        return myfund_imurl;
    }

    public void setMyfund_imurl(String myfund_imurl) {
        this.myfund_imurl = myfund_imurl;
    }
}