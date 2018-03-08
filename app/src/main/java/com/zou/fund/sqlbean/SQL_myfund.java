package com.zou.fund.sqlbean;

import org.litepal.crud.DataSupport;

/**
 * Created by 邹远君 on 2018/1/28 0028.
 */

public class SQL_myfund extends DataSupport {
    String myfund_code;
    double myfund_num;          //分数
    double myfund_cost;         //单价
    double myfund_charge;

    public SQL_myfund(String myfund_code, double myfund_num, double myfund_cost, double myfund_charge) {
        this.myfund_code = myfund_code;
        this.myfund_num = myfund_num;
        this.myfund_cost = myfund_cost;
        this.myfund_charge = myfund_charge;
    }


    public double getMyfund_charge() {
        return myfund_charge;
    }

    public void setMyfund_charge(double myfund_charge) {
        this.myfund_charge = myfund_charge;
    }

    public String getMyfund_code() {
        return myfund_code;
    }

    public void setMyfund_code(String myfund_code) {
        this.myfund_code = myfund_code;
    }

    public double getMyfund_num() {
        return myfund_num;
    }

    public void setMyfund_num(double myfund_num) {
        this.myfund_num = myfund_num;
    }

    public double getMyfund_cost() {
        return myfund_cost;
    }

    public void setMyfund_cost(double myfund_cost) {
        this.myfund_cost = myfund_cost;
    }
}
