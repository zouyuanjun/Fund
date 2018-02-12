package com.zou.fund.parse;

import android.util.Log;

import com.zou.fund.sqlbean.SQL_fund_cc_bean;
import com.zou.fund.sqlbean.Fund_id_bean;

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
    private ArrayList<SQL_fund_cc_bean> arrayList=new ArrayList<>();
    private ArrayList<Fund_id_bean> arrayList1=new ArrayList<>();

    public Chicang(String httpstring) throws UnsupportedEncodingException {
        this.httpstring = httpstring;
        parse(httpstring);
    }
    public ArrayList<SQL_fund_cc_bean> parse(String string){
        Document doc= Jsoup.parse(string);
        Element element=doc.getElementById("fund_sdzc_table");
        Elements elements1=element.select("tbody");
        Elements link=elements1.select("tr");
        for (Element ruslt:link){
            Elements elements=ruslt.select("td");
            name=elements.get(0).text();
            zf=elements.get(1).text();
            ratio=elements.get(3).text();
            ratio_last=elements.get(4).text();
            cgjj=elements.get(5).text();
            cgjj_last=elements.get(6).text();
            SQL_fund_cc_bean fund_cc_bean=new SQL_fund_cc_bean(name,zf,ratio,ratio_last,cgjj,cgjj_last);

            arrayList.add(fund_cc_bean);

            Log.d("String",elements.toString()+"<td class=sdzc_zxj>80.56</td>)");
            fund_cc_bean.save();
            Log.d("wangluo",name+zf+ratio+cgjj+"家"+"与上期变化"+cgjj_last);
        }

        return null;
    }


    }

