package com.zou.fund.data;

import java.util.ArrayList;

/**
 * Created by 邹远君 on 2018/3/8 0008.
 */

public class Fund {
    String title;
    String type;
    String fund_code;
    String zuixjz;
    String zhangfu;
    String gsjz;
    String gszhangfu;
    String gstime;
    String gsimageurl;
    ArrayList<Fund_chicang> arrayList;
    String chicangsun;

    public Fund(String title, String type, String fund_code, String zuixjz, String zhangfu, String gsjz, String gszhangfu, String gstime, String gsimageurl, ArrayList<Fund_chicang> arrayList, String chicangsun) {
        this.title = title;
        this.type = type;
        this.fund_code = fund_code;
        this.zuixjz = zuixjz;
        this.zhangfu = zhangfu;
        this.gsjz = gsjz;
        this.gszhangfu = gszhangfu;
        this.gstime = gstime;
        this.gsimageurl = gsimageurl;
        this.arrayList = arrayList;
        this.chicangsun = chicangsun;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getZuixjz() {
        return zuixjz;
    }

    public void setZuixjz(String zuixjz) {
        this.zuixjz = zuixjz;
    }

    public String getZhangfu() {
        return zhangfu;
    }

    public void setZhangfu(String zhangfu) {
        this.zhangfu = zhangfu;
    }

    public String getGsjz() {
        return gsjz;
    }

    public void setGsjz(String gsjz) {
        this.gsjz = gsjz;
    }

    public String getGszhangfu() {
        return gszhangfu;
    }

    public void setGszhangfu(String gszhangfu) {
        this.gszhangfu = gszhangfu;
    }

    public String getGstime() {
        return gstime;
    }

    public void setGstime(String gstime) {
        this.gstime = gstime;
    }

    public String getGsimageurl() {
        return gsimageurl;
    }

    public void setGsimageurl(String gsimageurl) {
        this.gsimageurl = gsimageurl;
    }

    public ArrayList<Fund_chicang> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Fund_chicang> arrayList) {
        this.arrayList = arrayList;
    }

    public String getChicangsun() {
        return chicangsun;
    }

    public void setChicangsun(String chicangsun) {
        this.chicangsun = chicangsun;
    }
}
