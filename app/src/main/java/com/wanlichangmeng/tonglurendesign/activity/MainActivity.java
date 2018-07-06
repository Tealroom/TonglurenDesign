package com.wanlichangmeng.tonglurendesign.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;

import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.Toast;


import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.adapter.ViewPagerAdapter;
import com.wanlichangmeng.tonglurendesign.fragment.CommunityFragment;
import com.wanlichangmeng.tonglurendesign.fragment.HomeFragment;
import com.wanlichangmeng.tonglurendesign.fragment.MessageFragment;
import com.wanlichangmeng.tonglurendesign.fragment.UserFragment;
import com.wanlichangmeng.tonglurendesign.utils.BottomNavigationViewHelper;
import com.wanlichangmeng.tonglurendesign.utils.ViewPagerHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * 作者：周才智
 * 时间：2018/05/18
 * 注释：一级activity，程序最主要的入口,包含了四个大的fragment分别是Home,Community,message,user
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //底部菜单栏控件
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.main_viewpager)
    LinearLayout main_viewpager;
    //底部菜单选项对应的适配器
    @BindView(R.id.viewpager)
    ViewPagerHelper viewpager;
    private MenuItem menuItem;
    int lastItem;
    private boolean mIsExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {

        //ActivityUtils.transparencyBar(this);

        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(navigation);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new CommunityFragment());
        adapter.addFragment(new MessageFragment());
        adapter.addFragment(UserFragment.newInstance("myself"));
        viewpager.setAdapter(adapter);
        viewpager.setScanScroll(false);
        viewpager.setOffscreenPageLimit(4);//viewpager缓存的个数，默认是2，表示前后各一个
        // 存在的问题，如果有的页面不需要缓存该如何自动刷新，可以利用eventbus传参来进行该页面的操作
        viewpager.setCurrentItem(0);





        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        lastItem = 0;
                        viewpager.setCurrentItem(0);//首页
                        return true;
                    case R.id.navigation_dashboard:
                        lastItem = 1;
                        viewpager.setCurrentItem(1);//社区
                        return true;
                    case R.id.navigation_Add:
                        Intent intent = new Intent(MainActivity.this, TripActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_notifications:
                        lastItem = 3;
                        viewpager.setCurrentItem(2);//消息
                        return true;
                    case R.id.navigation_personal:
                        lastItem = 4;
                        viewpager.setCurrentItem(3);//我的
                        return true;
                }
                return false;
            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });






    }









    /**
     * 双击退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
    @Override
    public void onResume() {
        super.onResume(); // Always call the superclass method first
        // Get the Camera instance as the activity achieves full user focus
        //暂停回来就是回到发布之前
        if (menuItem != null) {
            menuItem.setChecked(false);
        } else {
            navigation.getMenu().getItem(0).setChecked(false);
        }
        menuItem = navigation.getMenu().getItem(lastItem);
        menuItem.setChecked(true);
    }
}

