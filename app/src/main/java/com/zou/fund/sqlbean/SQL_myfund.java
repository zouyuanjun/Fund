package com.zou.fund.sqlbean;

import org.litepal.crud.DataSupport;

/**我的基金基本信息，保存在sqlite中
 * Created by 邹远君 on 2018/1/28 0028.
 */

public class SQL_myfund extends DataSupport {
    /**
     * 基金代码
     */
    String myfund_code;
    double myfund_num;          //分数
    double myfund_cost;
    /**
     * 费率
     */
    double myfund_charge;     //费率

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
