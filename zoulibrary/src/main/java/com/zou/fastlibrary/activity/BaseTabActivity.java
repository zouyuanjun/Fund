package com.zou.fastlibrary.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.zou.fastlibrary.R;
import com.zou.fastlibrary.adapter.ViewPagerAdapter;
import com.zou.fastlibrary.ui.OnlyIconItemView;
import com.zou.fastlibrary.ui.OnlyTextTab;
import java.util.List;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

/**
 * 带Tab标签的导航主页，必须实现initview方法
 */
public class BaseTabActivity extends AppCompatActivity {
    ViewPager viewPager;
    Context context;
    Activity activity;
    NavigationController mNavigationController;
    PageNavigationView pageBottomTabLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basetab);
        activity=this;
        context=this;
        pageBottomTabLayout= findViewById(R.id.basebottomtab);
        viewPager=  findViewById(R.id.baseviewpage);
    }

    /**
     * 设置自定义的tab布局，pageBottomTabLayout的id必须为basebottomtab，viewpage的id必须为baseviewpage
     * @param savedInstanceState
     * @param layoutResID
     */
    public void onCreate(Bundle savedInstanceState,int layoutResID) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID);
        activity=this;
        context=this;
        pageBottomTabLayout= findViewById(R.id.basebottomtab);
        viewPager=  findViewById(R.id.baseviewpage);
    }

    /**
     * 初始化文本标签view
     * @param textlist     Tab文本标签list
     * @param fragmentList
     */
    public void initview(List<String> textlist,List<Fragment> fragmentList){
        creatOnlyTextTab(textlist);
        setViewPagerAdaptr(fragmentList);
    }

    /**
     * 初始化图标标签Tab
      * @param iocdef   未选中状态的图标list
     * @param iocsel    已选中选中状态的图标list
     * @param fragmentList
     */
    public void initview(List<Integer> iocdef,List<Integer> iocsel,List<Fragment> fragmentList){
        creatOnlyIconTab(iocdef,iocsel);
        setViewPagerAdaptr(fragmentList);
    }

    public void addNavigationControllerListener(OnTabItemSelectedListener onTabItemSelectedListener){
            mNavigationController.addTabItemSelectedListener(onTabItemSelectedListener);
    }
    /**
     *创建只包含图标的Tab
     * @param iocdef
     * @param iocsel
     */
    public void creatOnlyIconTab(List<Integer> iocdef,List<Integer> iocsel){
        PageNavigationView.CustomBuilder  customBuilder=pageBottomTabLayout.custom();
     for (int i:iocdef){
         customBuilder. addItem(newItem(iocdef.get(i),iocsel.get(i)));
     }
       mNavigationController=customBuilder.build();

    }

    /**
     * 创建只有文字的Tab
     * @param textlist
     * @return
     */
    public void creatOnlyTextTab(List<String> textlist){
        PageNavigationView.CustomBuilder  customBuilder=pageBottomTabLayout.custom();
        for (String string:textlist){
            customBuilder. addItem(newItem(context,string));
        }
        mNavigationController=customBuilder.build();

    }
    /**
     * 设置Viewpage的Adapter
     * @param fragmentList 要添加的fragment
     */
    public void setViewPagerAdaptr(List<Fragment> fragmentList){
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        mNavigationController.setupWithViewPager(viewPager);
    }
    private BaseTabItem newItem(int drawable, int checkedDrawable){
        OnlyIconItemView onlyIconItemView = new OnlyIconItemView(this);
        onlyIconItemView.initialize(drawable,checkedDrawable);
        return onlyIconItemView;
    }
    private BaseTabItem newItem(Context context,String tab){
        OnlyTextTab onlyTextTab=new OnlyTextTab(context,tab);
        return onlyTextTab;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public Context getContext() {
        return context;
    }

    public Activity getActivity() {
        return activity;
    }

    public NavigationController getmNavigationController() {
        return mNavigationController;
    }
}
