package com.zou.fund.sqlbean;

import org.litepal.crud.DataSupport;

/**
 * Created by 邹远君 on 2017/12/26.
 */

public class Fund_id_bean extends DataSupport {
    String fund_name;
    String code;

    public Fund_id_bean(String fund_name, String code) {
        this.fund_name = fund_name;
        this.code = code;
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
}
