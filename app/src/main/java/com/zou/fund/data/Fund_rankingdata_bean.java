package com.zou.fund.data;

/**
 * Created by 邹远君 on 2018/2/14 0014.
 */

public class Fund_rankingdata_bean {
    String rangking_fundcode;
    String rangking_fundname;
    String day;
    String week;
    String month;
    String three_month;
    String six_month;
    String this_year;
    String year;
    String two_year;
    String max;

    public Fund_rankingdata_bean(String rangking_fundcode, String rangking_fundname, String day, String week, String month, String three_month, String six_month, String this_year, String year, String two_year, String max) {
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getThis_year() {
        return this_year;
    }

    public void setThis_year(String this_year) {
        this.this_year = this_year;
    }

    public String getThree_month() {
        return three_month;
    }

    public void setThree_month(String three_month) {
        this.three_month = three_month;
    }

    public String getSix_month() {
        return six_month;
    }

    public void setSix_month(String six_month) {
        this.six_month = six_month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTwo_year() {
        return two_year;
    }

    public void setTwo_year(String two_year) {
        this.two_year = two_year;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}