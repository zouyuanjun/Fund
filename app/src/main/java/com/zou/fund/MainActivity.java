package com.zou.fund;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.widget.Toolbar;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zou.fund.bar.ChildFragment;
import com.zou.fund.bar.NavitationScrollLayout;
import com.zou.fund.bar.ViewPagerAdapter;
import com.zou.fund.fragment.Fr_contrast;
import com.zou.fund.fragment.Fr_myfund;
import com.zou.fund.fragment.Fr_rangking_list;
import com.zou.fund.fragment.Fr_test;

import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NavitationScrollLayout navitationScrollLayout;
    private ViewPager viewPager1;
    private String[] titles1 = new String[]{"我的基金", "基金排行", "持仓对比", "资讯"};
    private ViewPagerAdapter viewPagerAdapter1;
    private List<Fragment> fragments1;

    Toolbar toolbar;

    static public Context getContext() {
        return context;
    }

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this.getApplicationContext());
        Log.d("5555","初始化图片完成");
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.YELLOW);
        toolbar.setSubtitleTextColor(Color.parseColor("#80ff0000"));
        //侧边栏的按钮
        toolbar.setNavigationIcon(R.color.color_282d31);
        //取代原本的actionbar
        setSupportActionBar(toolbar);
        //设置NavigationIcon的点击事件,需要放在setSupportActionBar之后设置才会生效,
        viewPager1 = (ViewPager) findViewById(R.id.viewpager1);
        SQLiteDatabase db= Connector.getDatabase();
        navitationScrollLayout = (NavitationScrollLayout) findViewById(R.id.bar1);
        context=this.getContext();

        fragments1 =  new ArrayList<>();
        fragments1.add(new Fr_myfund());
        fragments1.add(new Fr_rangking_list());
        fragments1.add(new Fr_contrast());
        fragments1.add(new Fr_test());
        viewPagerAdapter1 = new ViewPagerAdapter(getSupportFragmentManager(), fragments1);
        viewPager1.setAdapter(viewPagerAdapter1);
        navitationScrollLayout.setViewPager(this, titles1, viewPager1, R.color.color_333333, R.color.color_2581ff, 16, 20, 5, true,R.color.color_333333,1, 15f, 15f, 100);
        navitationScrollLayout.setBgLine(this, 1, R.color.color_333333);
        navitationScrollLayout.setNavLine(this, 3, R.color.colorPrimary);


    }
}

