package com.zou.fund.parse;

import android.database.sqlite.SQLiteDatabase;

import com.zou.fund.sqlbean.SQL_lsjz;

import org.litepal.LitePal;
import org.litepal.LitePalDB;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by li on 2018/3/15.
 */

public class P_lsjz {
    String date;
    String lsjz;
    String zhangfu;
    String string;
    private List<String> mPointValues = new ArrayList<String>();
    private List<String> mAxisXValues = new ArrayList<String>();

    public List<String> getmPointValues() {
        return mPointValues;
    }

    public List<String> getmAxisXValues() {
        return mAxisXValues;
    }
    ArrayList<String> arrayList=new ArrayList();
    public P_lsjz(String string) {
        LitePalDB litePal=new LitePalDB("lsjz",1);
        litePal.addClassName(com.zou.fund.sqlbean.SQL_lsjz.class.getName());
        LitePal.use(litePal);
        SQLiteDatabase db= Connector.getDatabase();
        DataSupport.deleteAll(com.zou.fund.sqlbean.SQL_lsjz.class);
        this.string = string;
        getpage();
    }

    public void getpage() {
        String pattern = "\\{.*?\\}";             //正则匹配
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(string);
        while (m.find()) {
            arrayList.add(m.group());  //{.......}
        }
        for (String s : arrayList) {
            if (s.length() > 50) {
                getdata(s);
                getzhangfu(s);
                SQL_lsjz sql_lsjz = new SQL_lsjz(date, lsjz, zhangfu);
                sql_lsjz.save();
            }

        }
    }
    public void getdata(String s){
        String pattern1 = "accum_net.*?,.enddate.*?,";//正则匹配
        Pattern r1 = Pattern.compile(pattern1);
        Matcher m1 = r1.matcher(s);
        while(m1.find()) {
            String a[]=m1.group().split(",");
            lsjz=a[0].substring(12, 18);
            date=a[1].substring(13, 21);
            mAxisXValues.add(date);
            mPointValues.add(lsjz);
            System.out.printf("净值"+lsjz+"时间"+date+a.length+"\n");
        }
    }
    public void getzhangfu(String s){
        System.out.printf(s+"\n");
        String a[]=s.split(",");
        String pattern2 = "-?[0-9].[0-9]*";//正则匹配
        Pattern r2 = Pattern.compile(pattern2);
        Matcher m2 = r2.matcher(a[7]);
        while(m2.find()) {
            System.out.printf("z涨幅"+m2.group()+"\n");
            zhangfu=m2.group();
        }
    }

}
