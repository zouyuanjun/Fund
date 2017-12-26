package com.zou.fund.parse;

import android.util.Log;

import com.zou.fund.sqlbean.Fund_cc_bean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
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

    public Chicang(String httpstring) throws UnsupportedEncodingException {
        this.httpstring = httpstring;
        p(httpstring);
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
    public void p(String HTML) throws UnsupportedEncodingException {
        Document doc=Jsoup.parse(HTML);
        Elements elements=doc.getElementsByClass("main_tab2");
        Elements elements1=elements.select("tbody");
        Elements link=elements1.select("tr");
        for (Element ruslt:link){
            Elements elements2=ruslt.select("td");
            if(elements2.size()>5) {
                name = elements2.get(0).text();
                zf = elements2.get(1).text();
                ratio = elements2.get(2).text();


                Log.d("wangluo", name + " 代码_" + zf + ratio);
            }
        }
    }
}
