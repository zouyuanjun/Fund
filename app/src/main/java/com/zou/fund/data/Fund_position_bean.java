package com.zou.fund.data;

/**
 * Created by 邹远君 on 2017/12/18.
 */

public class Fund_position_bean {
    public Fund_position_bean(String stock, String ratio) {
        this.stock = stock;
        this.ratio = ratio;
    }

    public String getStock() {
        return stock;
    }

    public String getRatio() {
        return ratio;
    }

    String stock;
    String ratio;
}
