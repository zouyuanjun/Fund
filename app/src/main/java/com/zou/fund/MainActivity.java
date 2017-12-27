package com.zou.fund;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zou.fund.bar.ChildFragment;
import com.zou.fund.bar.NavitationScrollLayout;
import com.zou.fund.bar.ViewPagerAdapter;
import com.zou.fund.fragment.Fr_contrast;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager1 = (ViewPager) findViewById(R.id.viewpager1);
        SQLiteDatabase db= Connector.getDatabase();
        navitationScrollLayout = (NavitationScrollLayout) findViewById(R.id.bar1);

        fragments1 =  new ArrayList<>();
        fragments1.add(new ChildFragment());
        fragments1.add(new ChildFragment());
        fragments1.add(new Fr_contrast());
        fragments1.add(new Fr_test());
        viewPagerAdapter1 = new ViewPagerAdapter(getSupportFragmentManager(), fragments1);
        viewPager1.setAdapter(viewPagerAdapter1);
        navitationScrollLayout.setViewPager(this, titles1, viewPager1, R.color.color_333333, R.color.color_2581ff, 16, 20, 5, true,R.color.color_333333,1, 15f, 15f, 100);
        navitationScrollLayout.setBgLine(this, 1, R.color.color_333333);
        navitationScrollLayout.setNavLine(this, 3, R.color.colorPrimary);


    }
}

