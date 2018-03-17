package com.zou.fund.sqlbean;

import org.litepal.crud.DataSupport;

/**
 * Created by 邹远君 on 2018/3/17 0017.
 */

public class SQL_lsjz extends DataSupport {
    String date;
    String lsjz;
    String zhangfu;

    public SQL_lsjz(String date, String ljjz, String zhangfu) {
        this.date = date;
        this.lsjz = ljjz;
        this.zhangfu = zhangfu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLsjz() {
        return lsjz;
    }

    public void setLsjz(String lsjz) {
        this.lsjz = lsjz;
    }

    public String getZhangfu() {
        return zhangfu;
    }

    public void setZhangfu(String zhangfu) {
        this.zhangfu = zhangfu;
    }
}
