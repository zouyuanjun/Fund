package com.zou.fund.parse;

import android.util.Log;

import com.zou.fund.data.Fund;
import com.zou.fund.data.Fund_chicang;
import com.zou.fund.data.My_fund_bean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by 邹远君 on 2018/3/8 0008.
 */

public class P_fund {
    String result;
    String title;
    String type;
    String fund_code;
    String zuixjz;
    String zhangfu;
    String gsjz;
    String gszhangfu;
    String gstime;
    String gsimageurl;
    Fund_chicang fund_chicang;
    String gup_name;
    String gup_zhangfu;
    String gup_zhanbi;
    String chicangsun;
    Fund fund;
    ArrayList<Fund> arrayList=new ArrayList<>();
    ArrayList<Fund_chicang> chicanglist=new ArrayList<>();
    Document doc;

    public P_fund(String result) {
        this.result = result;
    }

    public ArrayList<Fund> parse(){

        if (result.length()>5000) {
            doc = Jsoup.parse(result);
            getname();
            gettype();
            getcode();
            getimageurl();
            getzxjz();
            getgsjz();
            getgstime();
            getchicang();
            getchicangsun();
            fund=new Fund(title,type,fund_code,zuixjz,zhangfu,gsjz,gszhangfu,gstime,gsimageurl,chicanglist,chicangsun);
           arrayList.add(fund);
            Log.d("P_fund",title+type+fund_code+"最新净值"+zuixjz+"涨幅"+zhangfu+"估算净值"+gsjz+"估算涨幅"+gszhangfu+gstime);
        }else {

        }
        return arrayList;
    }
    public void getname(){
        //获取基金名称
        Elements els_myfundname=doc.getElementsByClass("SitePath");
        Elements elements=els_myfundname.select("a");
        try {
            title=elements.get(2).text();
        }catch (IndexOutOfBoundsException ex){
            title="代码错误";
        }

    }

    public void gettype(){
        Elements els=doc.getElementsByClass("infoOfFund");
        Elements elements1=els.select("td");
        Element element=elements1.get(0);
        type=element.text();
        type=type.substring(5);

    }
    public void getcode(){
        Elements els=doc.getElementsByClass("fundDetail-tit");
        Elements elements=els.select("span");
        Element elements1=elements.get(1);
        fund_code=elements1.text();
        if (elements1.text().length()>16){  //040008基金特殊处理
            fund_code=elements.text().substring(5,11);
        }
    }
    public void getimageurl(){
        gsimageurl="http://j4.dfcfw.com/charts/pic6/"+fund_code+".png";
    }
    public void getgsjz(){
        Elements elements=doc.getElementsByClass("floatleft");
        gsjz =elements.get(0).text();
        Elements elements1=doc.getElementsByClass("floatleft fundZdf");
        Elements s=elements1.select("span");
        gszhangfu=s.get(1).text();

    }
    public void getzxjz(){
        Elements elements=doc.getElementsByClass("dataNums");
        Element element=elements.get(1);
        Elements s=element.select("span");
        zuixjz=s.get(0).text();
        zhangfu=s.get(1).text();
    }
    public void getgstime(){
        Elements elements=doc.getElementsByClass("dataItem01");
        Elements s=elements.select("span");
        gstime=s.get(2).text();
        gstime=gstime.substring(4);
    }
    public void getchicang(){
        Elements elements=doc.getElementsByClass("poptableWrap");
        Elements elements1=elements.select("tbody");
        Elements elements2=elements1.select("tr");
        for (int i=1;i<11;i++){
            Element elements3=elements2.get(i);
            Elements elements4=elements3.select("td");
            gup_name=elements4.get(0).text();
            gup_zhangfu=elements4.get(1).text();
            gup_zhanbi=elements4.get(2).text();
            fund_chicang=new Fund_chicang(gup_name,gup_zhangfu,gup_zhanbi);
            chicanglist.add(fund_chicang);
            Log.d("持仓",gup_zhangfu+gup_name+gup_zhanbi+"第"+i);
        }
        Log.d("持仓","持仓解析完毕"+elements2.size());
    }
    public void getchicangsun(){
        Elements elements=doc.getElementsByClass("sum");
        chicangsun=elements.text();
    }
}
