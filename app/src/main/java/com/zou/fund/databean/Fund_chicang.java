package com.zou.fund.databean;

/**
 * Created by 邹远君 on 2018/3/8 0008.
 */

public class Fund_chicang {
    String gup_name;
    String gup_zhangfu;
    String gup_zhanbi;

    public Fund_chicang(String gup_name, String gup_zhangfu, String gup_zhanbi) {
        this.gup_name = gup_name;
        this.gup_zhangfu = gup_zhangfu;
        this.gup_zhanbi = gup_zhanbi;
    }

    public String getGup_name() {
        return gup_name;
    }

    public void setGup_name(String gup_name) {
        this.gup_name = gup_name;
    }

    public String getGup_zhangfu() {
        return gup_zhangfu;
    }

    public void setGup_zhangfu(String gup_zhangfu) {
        this.gup_zhangfu = gup_zhangfu;
    }

    public String getGup_zhanbi() {
        return gup_zhanbi;
    }

    public void setGup_zhanbi(String gup_zhanbi) {
        this.gup_zhanbi = gup_zhanbi;
    }
}
