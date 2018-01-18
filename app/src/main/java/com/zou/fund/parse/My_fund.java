package com.zou.fund.parse;

import com.zou.fund.data.My_fund_bean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by 邹远君 on 2018/1/18 0018.
 */

public class My_fund {
    String myfund_name;             //持仓基金名称
    String myfund_type;          //类型
    String myfund_code;          //基金代码
    Double myfund_chicang;       //持仓数量
    String myfund_imurl;        //估算净值图片url

    String result;
    ArrayList<My_fund_bean> arrayList=new ArrayList<>();

    public My_fund(String result) {
        this.result = result;
    }

    public String parse(){
        Document doc= Jsoup.parse(result);
        Elements el_imurl=doc.getElementsByClass("estimatedchart hasLoading");
        myfund_imurl=el_imurl.toString();       //获取图片链接

      return myfund_imurl;
    }
}
