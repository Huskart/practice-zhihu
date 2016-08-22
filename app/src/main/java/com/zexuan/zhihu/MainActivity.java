package com.zexuan.zhihu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.zexuan.zhihu.adapter.MainFragmentAdapter;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        this.mTabLayout = (TabLayout) findViewById(R.id.tab_FindFragment_title);
        if (this.mTabLayout != null) {
            this.mTabLayout.addTab(this.mTabLayout.newTab().setIcon(R.drawable.ic_main));
            this.mTabLayout.addTab(this.mTabLayout.newTab().setIcon(R.drawable.ic_compass));
            this.mTabLayout.addTab(this.mTabLayout.newTab().setIcon(R.drawable.ic_bell));
            this.mTabLayout.addTab(this.mTabLayout.newTab().setIcon(R.drawable.ic_message));
            this.mTabLayout.addTab(this.mTabLayout.newTab().setIcon(R.drawable.ic_setting));
        }
        this.mViewPager = (ViewPager) findViewById(R.id.id_page_vp);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        this.mViewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(), fragments));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
