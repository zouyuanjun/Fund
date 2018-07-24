package com.zou.fund.view.activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zou.fund.R;
import com.zou.fund.view.fragment.Fr_contrast;
import com.zou.fund.view.fragment.Fr_myfund;
import com.zou.fund.view.fragment.Fr_rangking_list;
import com.zou.fund.view.fragment.Fr_test;
import com.zou.fund.view.bar.NavitationScrollLayout;
import com.zou.fund.view.bar.ViewPagerAdapter;

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
    EditText editText;
    ImageButton imageButton;

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
        editText=findViewById(R.id.ed_main_seach);
        imageButton=findViewById(R.id.ib_main_seach);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.clearFocus();
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH){
                    Intent intent=new Intent(MainActivity.this,Activity_fund.class);
                    String fundcode=editText.getText().toString();
                    intent.putExtra("code",fundcode);
                    startActivity(intent);
                }
                return false;
            }
        });

        toolbar=findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.YELLOW);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Activity_fund.class);
                String fundcode=editText.getText().toString();
                intent.putExtra("code",fundcode);
                startActivity(intent);
            }
        });
        toolbar.setSubtitleTextColor(Color.parseColor("#ff0000"));
        setSupportActionBar(toolbar);
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

