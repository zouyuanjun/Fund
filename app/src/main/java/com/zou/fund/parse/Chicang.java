package com.zou.fund.parse;

import android.util.Log;

import com.zou.fund.data.Fund_cc_bean;
import com.zou.fund.data.Fund_position_arraylist;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by 邹远君 on 2017/12/24.
 */

public class Chicang {
    private String name;
    private String zf;
    private String ratio;
    private String ratio_last;
    private String cgjj;
    private String cgjj_last;
    private String httpstring;

    public Chicang(String httpstring) {
        this.httpstring = httpstring;
        parse(httpstring);
    }
    public ArrayList<Fund_cc_bean> parse(String string){
        Document doc= Jsoup.parse(string);
        Element element=doc.getElementById("fund_sdzc_table");
        Elements elements1=element.select("tbody");
        Elements link=elements1.select("tr");
        for (Element ruslt:link){
            Elements elements=ruslt.select("td");
            name=elements.get(0).text();
            zf=elements.get(2).text();
            ratio=elements.get(3).text();


            Log.d("wangluo",name+"text");
        }
        Log.d("String",string+"string");
        return null;
    }
}
