package com.zou.fund.util;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by li on 2018/3/15.
 */

public class Dateutil {
    LocalDate localDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Dateutil() {
        localDate= LocalDate.now();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String get_monthago(){
        String monthago=null;
        LocalDate month=localDate.plus(1, ChronoUnit.MONTHS);
        monthago=month.toString();
        return  monthago;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String get_three_monthago(){
        String monthago=null;
        LocalDate month=localDate.plus(3, ChronoUnit.MONTHS);
        monthago=month.toString();
        return  monthago;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String get_six_monthago(){
        String monthago=null;
        LocalDate month=localDate.plus(6, ChronoUnit.MONTHS);
        monthago=month.toString();
        return  monthago;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String get_yearago(){
        String monthago=null;
        LocalDate month=localDate.plus(1, ChronoUnit.YEARS);
        monthago=month.toString();
        return  monthago;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String get_three_yearago(){
        String monthago=null;
        LocalDate month=localDate.plus(1, ChronoUnit.YEARS);
        monthago=month.toString();
        return  monthago;
    }
}
