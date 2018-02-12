package com.zou.fund.parse;

import android.database.sqlite.SQLiteDatabase;

import org.litepal.LitePal;
import org.litepal.LitePalDB;
import org.litepal.tablemanager.Connector;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 邹远君 on 2018/2/12 0012.
 */

public class P_ranking_list {

    public P_ranking_list() {
    }
    ArrayList arrayList=new ArrayList();
    public ArrayList p_rangking_list(String HTML) throws UnsupportedEncodingException {
        LitePalDB litePal=new LitePalDB("fund_id",1);
        litePal.addClassName(com.zou.fund.sqlbean.Fund_id_bean.class.getName());
        LitePal.use(litePal);
        SQLiteDatabase db= Connector.getDatabase();
        String str = HTML;
        String pattern = "\".*?\"";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        while(m.find()) {
            arrayList.add(m.group());
            //System.out.println(m.group());
        }
return arrayList;


    }
}
