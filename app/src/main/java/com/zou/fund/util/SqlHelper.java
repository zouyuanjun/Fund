package com.zou.fund.util;

import android.content.ContentValues;
import android.renderscript.Sampler;
import android.util.Log;

import com.zou.fund.sqlbean.SQL_myfund;

import org.litepal.LitePal;
import org.litepal.LitePalDB;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 邹远君 on 2018/2/28 0028.
 */

public class SqlHelper {
    LitePalDB litePalDB;
    String dbname;
    List<?> querydatalist;

     Class myclass;

     int version;

    public SqlHelper(String dbname, Class myclass, int version) {
        this.dbname = dbname;
        this.myclass = myclass;
        this.version = version;
    }


    public void creatdatabase() {
        litePalDB = new LitePalDB(dbname, version);
        Log.d("sqlhelper","创建数据库");//创建数据库
        litePalDB.addClassName(myclass.getName());
        LitePal.use(litePalDB);
    }

    public List<?> querydata() {
        LitePal.use(litePalDB);
        querydatalist = DataSupport.findAll(myclass);
        Log.d("sqlhelper","数据库大小"+querydatalist.size());
        return querydatalist;
    }
    public void updata( ContentValues values,String condition,String value ){
        LitePal.use(litePalDB);
        DataSupport.updateAll(myclass, values, condition, value);
    }
    public void  delete(String condition,String value){
        LitePal.use(litePalDB);
        DataSupport.deleteAll(myclass,condition, value);
    }
}
