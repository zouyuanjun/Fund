package com.zou.fund.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by li on 2018/3/15.
 */

public class P_lsjz {
    String string;
    Document doc;

    public P_lsjz(String string) {
        this.string = string;
        doc= Jsoup.parse(string);
    }
    public String getpage(){
        Elements elements=doc.getElementsByClass("pagination-inner");
        String s=elements.text();
        String str = s;
        String pattern = "[0-9]+";             //正则匹配
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        m.find();
        String pagenum=m.group(1);
        return pagenum;
    }
}
