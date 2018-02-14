package com.zou.fund.data;

/**
 * Created by 邹远君 on 2018/2/14 0014.
 */

public class Fund_rankingdata_bean {
    String day;
    String week;
    String month;
    String two_month;
    String three_month;
    String six_month;
    String year;
    String two_year;
    String max;

    public Fund_rankingdata_bean(String day, String week, String month, String two_month, String three_month, String six_month, String year, String two_year, String max) {
        this.day = day;
        this.week = week;
        this.month = month;
        this.two_month = two_month;
        this.three_month = three_month;
        this.six_month = six_month;
        this.year = year;
        this.two_year = two_year;
        this.max = max;
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

    public String getTwo_month() {
        return two_month;
    }

    public void setTwo_month(String two_month) {
        this.two_month = two_month;
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
