package com.zou.fund.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zou.fund.R;

/**
 * Created by zouyu on 2017/12/27.
 */

public class Fr_test extends Fragment {

    TextView textView;
    TextView textView2;
    String string;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fr_test, container, false);
        textView=rootView.findViewById(R.id.tv_test);
         textView2=rootView.findViewById(R.id.tv_test2);
         Test();
        return rootView;
    }
    public void Test(){
        string="[2554[asd]fasd]";
        String s="\\[.*?]";
        String[] strs=string.split(s);
        for(int i=0,len=strs.length;i<len;i++){
            Log.d("555",strs[i].toString());
        }
    }

    }
