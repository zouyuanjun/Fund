package com.zou.fund.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zou.fastlibrary.activity.BaseTabActivity;
import com.zou.fund.R;
import com.zou.fund.view.fragment.Fr_contrast;
import com.zou.fund.view.fragment.Fr_myfund;
import com.zou.fund.view.fragment.Fr_rangking_list;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class HomeActivity extends BaseTabActivity {
    List<String> textlist=new ArrayList<>();
    private List<Fragment> fragmentList=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        onCreate(savedInstanceState, R.layout.activity_main2);
        Fresco.initialize(this.getApplicationContext());
        textlist.add("我的基金");
        textlist.add("排行");
        textlist.add("对比");
        creatOnlyTextTab(textlist);
        fragmentList.add(new Fr_myfund());
        fragmentList.add(new Fr_rangking_list());
        fragmentList.add(new Fr_contrast());
        setViewPagerAdaptr(fragmentList);
    }

}
