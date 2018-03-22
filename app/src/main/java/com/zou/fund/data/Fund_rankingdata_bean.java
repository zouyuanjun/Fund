package com.zou.fund.data;

import org.litepal.crud.DataSupport;

/**
 * Created by 邹远君 on 2018/2/14 0014.
 * 基金排行，历史涨幅数据类
 */

public class Fund_rankingdata_bean extends DataSupport {
    String rangking_fundcode;
    String rangking_fundname;
    Double day;
    Double week;
    Double month;
    Double three_month;
    Double six_month;
    Double this_year;
    Double year;
    Double two_year;
    Double max;

    public Fund_rankingdata_bean(String rangking_fundcode, String rangking_fundname, Double day, Double week, Double month, Double three_month, Double six_month, Double this_year, Double year, Double two_year, Double max) {
        this.rangking_fundcode = rangking_fundcode;
        this.rangking_fundname = rangking_fundname;
        this.day = day;
        this.week = week;
        this.month = month;
        this.three_month = three_month;
        this.six_month = six_month;
        this.this_year = this_year;
        this.year = year;
        this.two_year = two_year;
        this.max = max;
    }

    public String getRangking_fundcode() {
        return rangking_fundcode;
    }

    public void setRangking_fundcode(String rangking_fundcode) {
        this.rangking_fundcode = rangking_fundcode;
    }

    public String getRangking_fundname() {
        return rangking_fundname;
    }

    public void setRangking_fundname(String rangking_fundname) {
        this.rangking_fundname = rangking_fundname;
    }

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public Double getWeek() {
        return week;
    }

    public void setWeek(Double week) {
        this.week = week;
    }

    public Double getMonth() {
        return month;
    }

    public void setMonth(Double month) {
        this.month = month;
    }

    public Double getThree_month() {
        return three_month;
    }

    public void setThree_month(Double three_month) {
        this.three_month = three_month;
    }

    public Double getSix_month() {
        return six_month;
    }

    public void setSix_month(Double six_month) {
        this.six_month = six_month;
    }

    public Double getThis_year() {
        return this_year;
    }

    public void setThis_year(Double this_year) {
        this.this_year = this_year;
    }

    public Double getYear() {
        return year;
    }

    public void setYear(Double year) {
        this.year = year;
    }

    public Double getTwo_year() {
        return two_year;
    }

    public void setTwo_year(Double two_year) {
        this.two_year = two_year;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}