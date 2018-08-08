package com.zou.fund.parse;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zou.fund.databean.Fund_rankingdata_bean;

import org.litepal.LitePal;
import org.litepal.LitePalDB;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**格式化基金列表的数据
 * Created by 邹远君 on 2018/2/12 0012.
 */

public class P_ranking_list {
    ArrayList<Fund_rankingdata_bean> array_f=new ArrayList<>();

    String rangking_fundcode;
    String rangking_fundname;
    Double day;
    Double week;
    Double month;
    Double this_year;
    Double three_month;
    Double six_month;
    Double year;
    Double two_year;
    Double max;
    public P_ranking_list() {
        Log.d("Prangking","每日净值开始解析");
    }
    ArrayList<String> arrayList=new ArrayList();
    public ArrayList p_rangking_list(String HTML) throws UnsupportedEncodingException {
        LitePalDB litePal=new LitePalDB("fund_ranking",1);
        litePal.addClassName(com.zou.fund.databean.Fund_rankingdata_bean.class.getName());
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
        com.zou.fastlibrary.utils.Log.d("正则匹配完成","开始循环");
        for (String s:arrayList){
            String a[]=s.split(",");
            String code=a[0];
            rangking_fundcode=code.substring(1,7);
            rangking_fundname=a[1];
            day=fomat(a[6]);
            week=fomat(a[7]);
            month=fomat(a[8]);
            three_month=fomat(a[9]);
            six_month=fomat(a[10]);
            this_year=fomat(a[14]);
            year=fomat(a[11]);
            two_year=fomat(a[12]);
            max=fomat(a[15]);
            Fund_rankingdata_bean fund_rankingdata_bean=new Fund_rankingdata_bean(rangking_fundcode,rangking_fundname,day,week,month,three_month,six_month,this_year,year,two_year,max);
            array_f.add(fund_rankingdata_bean);

        }
        com.zou.fastlibrary.utils.Log.d("正则匹配完成","结束循环");
    DataSupport.saveAllAsync(array_f).listen(new org.litepal.crud.callback.SaveCallback() {
        @Override
        public void onFinish(boolean success) {
            Log.d("Prangking","每日净值保存成功");
        }
    });
    return array_f;
    }
    //格式化增长率百分数
    public double fomat(String data){
       try {
           Double a=Double.parseDouble(data);

        DecimalFormat df = new DecimalFormat("0.00 ");//控制小数点位数
        Double s=Double.parseDouble(df.format(a));
        return s;
    }catch (NumberFormatException e){
           return 0;
       }
    };
}
