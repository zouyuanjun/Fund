package com.zou.fund.parse;

import android.util.Log;

import com.zou.fund.databean.My_fund_bean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by 邹远君 on 2018/1/18 0018.
 */

public class P_myfund {
    String myfund_name;             //持仓基金名称
    String myfund_type;          //类型
    String myfund_code;          //基金代码
    String myfund_imurl;     //估算净值图片url
    String myfund_jjjz;    //基金净值
    String myfund_jjzf;
    String result;
    ArrayList<My_fund_bean> arrayList=new ArrayList<>();
    Document doc;

    public P_myfund(String result) {
        this.result = result;
    }

    public ArrayList<My_fund_bean> parse(){
        My_fund_bean my_fund_bean;
       if (result.length()>5000) {
           doc = Jsoup.parse(result);
           getname();
           gettype();
           getcode();
           getimageurl();
           getprice();
           my_fund_bean = new My_fund_bean(myfund_name, myfund_type, myfund_code, 0.0, 0.0, myfund_imurl,"0",myfund_jjjz,myfund_jjzf);
       }else {
           my_fund_bean=new My_fund_bean("代码错误","错误","000000",0.0,0.0,"","","错误","");
       }
       arrayList.add(my_fund_bean);
        return arrayList;
    }
    public void getname(){
        //获取基金名称
        Elements els_myfundname=doc.getElementsByClass("SitePath");
        Elements elements=els_myfundname.select("a");
        try {
            myfund_name=elements.get(2).text();
        }catch (IndexOutOfBoundsException ex){
            myfund_name="代码错误";
        }

        Log.d("基金名称",myfund_name);
    }

    public void gettype(){
        Elements els=doc.getElementsByClass("infoOfFund");
       // Elements elements=els.select("td");
        Elements elements1=els.select("a");
        myfund_type=elements1.get(0).text();
        Log.d("基金类型",myfund_type);

    }
    public void getcode(){
        Elements els=doc.getElementsByClass("fundDetail-tit");
        Elements elements=els.select("span");
        Element elements1=elements.get(1);
        myfund_code=elements1.text();
        if (elements1.text().length()>16){
            myfund_code=elements.text().substring(5,11);
        }
        Log.d("p基金代码",myfund_code+"");
    }
    public void getimageurl(){
        myfund_imurl="http://j4.dfcfw.com/charts/pic6/"+myfund_code+".png";
    }
    public void getprice(){
        Elements elements=doc.getElementsByClass("dataNums");
        Element element=elements.get(1);

        Elements s=element.select("span");
        myfund_jjjz=s.get(0).text();
        myfund_jjzf=s.get(1).text();
    }
}
