package com.zexuan.zhihu;

import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zexuan.zhihu.adapter.MainPageFragmentAdapter;
import com.zexuan.zhihu.fragments.Fragment1;
import com.zexuan.zhihu.fragments.Fragment2;
import com.zexuan.zhihu.fragments.Fragment3;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

//    private ViewPager mViewPager;
//    private TabLayout mTabLayout;
//    private List<String> titles = new ArrayList<>();
//    private List<Fragment> views = new ArrayList<>();
private BottomSheetBehavior mBottomSheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.tab_layout));
//        initView();
//        MainPageFragmentAdapter mainPageFragmentAdapter = new MainPageFragmentAdapter(getSupportFragmentManager(),titles,views);
//
//        this.mViewPager.setAdapter(mainPageFragmentAdapter);
//        //为TabLayout设置ViewPager
//        mTabLayout.setupWithViewPager(mViewPager);
//        //使用ViewPager的适配器
//        mTabLayout.setTabsFromPagerAdapter(mainPageFragmentAdapter);
    }

    private void initView(){

//        mViewPager = (ViewPager) findViewById(R.id.vp_view);
//        mTabLayout = (TabLayout) findViewById(R.id.tabs);
//        titles.add("page1");
//        titles.add("page2");
//        titles.add("page3");
//
//        views.add(new Fragment1());
//        views.add(new Fragment2());
//        views.add(new Fragment3());
    }
}
