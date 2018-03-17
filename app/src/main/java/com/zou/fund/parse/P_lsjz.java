package com.zou.fund.parse;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.PointValue;


/**
 * Created by li on 2018/3/15.
 */

public class P_lsjz {
    String date;
    String lsjz;
    String zhangfu;

    String string;
    Document doc;

    private List<String> mPointValues = new ArrayList<String>();
    private List<String> mAxisXValues = new ArrayList<String>();

    public List<String> getmPointValues() {
        return mPointValues;
    }

    public List<String> getmAxisXValues() {
        return mAxisXValues;
    }

    ArrayList<String> arrayList=new ArrayList();
    ArrayList<String> arrayList1=new ArrayList();
    public P_lsjz(String string) {
        this.string = string;
        doc= Jsoup.parse(string);
        getpage();
    }
    public void getpage(){

        String pattern = "\\{.*?\\}";             //正则匹配
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(string);
        while(m.find()) {
            arrayList.add(m.group());  //{.......}
        }
        for (String s:arrayList){
            String pattern1 = "accum_net.*?,.enddate.*?,";//正则匹配
            Pattern r1 = Pattern.compile(pattern1);
            Matcher m1 = r1.matcher(s);
            while(m1.find()) {
                arrayList1.add(m1.group());
            }
            for (String s1:arrayList1){
                System.out.printf(s1+"\n");
                String a[]=s1.split(",");
                lsjz=a[0].substring(12, 18);
                //date=a[1].substring(11, 21);
                date="1";
                mAxisXValues.add(date);
                mPointValues.add(lsjz);
                System.out.printf("净值"+lsjz+"时间"+date+a.length+"\n");
            }

            String a[]=s.split(",");
            String pattern2 = "-?[0-9].[0-9]*";//正则匹配
            Pattern r2 = Pattern.compile(pattern2);
            System.out.printf(a[7]+"\n");
            Matcher m2 = r2.matcher(a[7]);

            while(m2.find()) {
                System.out.printf("z涨幅"+m2.group()+"\n");
                zhangfu=m2.group();
            }
        }
    }

}
