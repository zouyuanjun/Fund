package com.zou.fund.sqlbean;

import org.litepal.crud.DataSupport;

/**
 * Created by 邹远君 on 2017/12/24.
 * 基金持仓类
 */

public class SQL_fund_cc_bean extends DataSupport {
    private String name;
    private String zf;
    private String ratio;
    private String ratio_last;
    private String cgjj;
    private String cgjj_last;

    public SQL_fund_cc_bean(String name, String zf, String ratio, String ratio_last, String cgjj, String cgjj_last) {
        this.name = name;
        this.zf = zf;
        this.ratio = ratio;
        this.ratio_last = ratio_last;
        this.cgjj = cgjj;
        this.cgjj_last = cgjj_last;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setZf(String zf) {
        this.zf = zf;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public void setRatio_last(String ratio_last) {
        this.ratio_last = ratio_last;
    }

    public void setCgjj(String cgjj) {
        this.cgjj = cgjj;
    }

    public void setCgjj_last(String cgjj_last) {
        this.cgjj_last = cgjj_last;
    }

    public String getName() {
        return name;
    }

    public String getZf() {
        return zf;
    }

    public String getRatio() {
        return ratio;
    }

    public String getRatio_last() {
        return ratio_last;
    }

    public String getCgjj() {
        return cgjj;
    }

    public String getCgjj_last() {
        return cgjj_last;
    }
}
