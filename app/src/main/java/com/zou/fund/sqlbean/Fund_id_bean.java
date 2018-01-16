package com.zou.fund.sqlbean;

import org.litepal.crud.DataSupport;

/**
 * Created by 邹远君 on 2017/12/26.
 */

public class Fund_id_bean extends DataSupport {
    String fund_name;
    String code;
    int rata;

    public Fund_id_bean(String fund_name, String code, int rata) {
        this.fund_name = fund_name;
        this.code = code;
        this.rata = rata;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getRata() {
        return rata;
    }

    public void setRata(int rata) {
        this.rata = rata;
    }
}
