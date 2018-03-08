package com.zou.fund.parse;

import android.database.sqlite.SQLiteDatabase;

import com.zou.fund.data.Fund_rankingdata_bean;

import org.litepal.LitePal;
import org.litepal.LitePalDB;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 邹远君 on 2018/2/12 0012.
 */

public class P_ranking_list {
    ArrayList<Fund_rankingdata_bean> array_f=new ArrayList<>();

    String rangking_fundcode;
    String rangking_fundname;
    String day;
    String week;
    String month;
    String this_year;
    String three_month;
    String six_month;
    String year;
    String two_year;
    String max;
    public P_ranking_list() {
    }
    ArrayList<String> arrayList=new ArrayList();
    public ArrayList p_rangking_list(String HTML) throws UnsupportedEncodingException {
        LitePalDB litePal=new LitePalDB("fund_ranking",1);
        litePal.addClassName(com.zou.fund.data.Fund_rankingdata_bean.class.getName());
        LitePal.use(litePal);
        SQLiteDatabase db= Connector.getDatabase();
        DataSupport.deleteAll(Fund_rankingdata_bean.class);
        String str = HTML;
        String pattern = "\".*?\"";             //正则匹配
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        while(m.find()) {
               arrayList.add(m.group());
        }
        for (String s:arrayList){
            String a[]=s.split(",");
            String code=a[0];
            rangking_fundcode=code.substring(1,7);
            rangking_fundname=a[1];
            day=a[6];
            day=fomat(day);
            week=a[7];
            week=fomat(week);
            month=a[8];
            month=fomat(month);
            three_month=a[9];
            three_month=fomat(three_month);
            six_month=a[10];
            six_month=fomat(six_month);
            this_year=a[14];
            this_year=fomat(this_year);
            year=a[11];
            year=fomat(year);
            two_year=a[12];
            two_year=fomat(two_year);
            max=a[15];
            max=fomat(max);
            Fund_rankingdata_bean fund_rankingdata_bean=new Fund_rankingdata_bean(rangking_fundcode,rangking_fundname,day,week,month,three_month,six_month,this_year,year,two_year,max);

            fund_rankingdata_bean.save();
            array_f.add(fund_rankingdata_bean);

        }

    return array_f;
    }
    //格式化增长率百分数
    public String fomat(String data){
       try {
           double a=Double.parseDouble(data);

        DecimalFormat df = new DecimalFormat("0.00 ");//控制小数点位数
        String s=df.format(a);
        return s;
    }catch (NumberFormatException e){
           return "--";
       }
    };
}
