package com.wanlichangmeng.tonglurendesign.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;

import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.adapter.ViewPagerAdapter;
import com.wanlichangmeng.tonglurendesign.fragment.CommunityFragment;
import com.wanlichangmeng.tonglurendesign.fragment.ContentFragment;
import com.wanlichangmeng.tonglurendesign.fragment.HomeFragment;
import com.wanlichangmeng.tonglurendesign.fragment.MessageFragment;
import com.wanlichangmeng.tonglurendesign.fragment.UserFragment;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;
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
    //底部菜单选项对应的适配器
    @BindView(R.id.viewpager)
    ViewPagerHelper viewpager;
    private MenuItem menuItem;

    private ContentFragment contentFragment;
    private boolean mIsExit;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout rlHome, rlGift, rlShare;
    private int currentSelectItem = R.id.rl_home;// 默认首页

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ActivityUtils.transparencyBar(this);
        //ActivityUtils.StatusBarLightMode(this);
        //.setStatusBarColor(this, R.color.common_color);//设置状态栏颜色
        initView();//去掉这句话就是一个典型的底部导航界面

    }

    private void initView() {












        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(navigation);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new CommunityFragment());
        adapter.addFragment(new MessageFragment());
        adapter.addFragment(new UserFragment());
        viewpager.setAdapter(adapter);
        viewpager.setScanScroll(false);
        viewpager.setCurrentItem(0);

        //缓存3个页面，来解决点击“我的”回来，首页空白的问题，
        // 存在的问题，如果有的页面不需要缓存该如何自动刷新，可以利用eventbus传参来进行该页面的操作
        //viewpager.setOffscreenPageLimit(3);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        viewpager.setCurrentItem(0);//首页
                        return true;
                    case R.id.navigation_dashboard:
                        viewpager.setCurrentItem(1);//社区
                        return true;
                    case R.id.navigation_notifications:
                        viewpager.setCurrentItem(2);//购物车
                        return true;
                    case R.id.navigation_personal:
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

        //禁止ViewPager滑动 这里解开之后会有个错误就是首页的HomeFragment里面的viewPage会和这个viewPage搞混淆_在右划到头的时候。
//        viewpager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.i("zhoucaizhid","jinlaile");
//                return true;
//            }
//        });



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //设置左滑栏的监听事件
//        findViewById(R.id.iv_menu).setOnClickListener(clickListener);
//        initLeftMenu();//初始化左边菜单
        contentFragment=new ContentFragment();
        //getSupportFragmentManager().beginTransaction().add(R.id.content_frame,contentFragment).commit();
        setWindowStatus();

    }

    private void initLeftMenu() {
        rlHome = (RelativeLayout) findViewById(R.id.rl_home);
        rlGift = (RelativeLayout) findViewById(R.id.rl_gift);
        rlShare = (RelativeLayout) findViewById(R.id.rl_share);

        rlHome.setOnClickListener(onLeftMenuClickListener);
        rlGift.setOnClickListener(onLeftMenuClickListener);
        rlShare.setOnClickListener(onLeftMenuClickListener);

        rlHome.setSelected(true);
    }

    private View.OnClickListener onLeftMenuClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (currentSelectItem != v.getId()) {//防止重复点击
                currentSelectItem=v.getId();
                noItemSelect();

                switch (v.getId()) {
                    case R.id.rl_home:
                        rlHome.setSelected(true);
                        contentFragment.setContent("这是首页");
                        break;
                    case R.id.rl_gift:
                        rlGift.setSelected(true);
                        contentFragment.setContent("这是礼物兑换");
                        break;
                    case R.id.rl_share:
                        rlShare.setSelected(true);
                        contentFragment.setContent("这是我的分享");
                        break;
                }

                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        }
    };

    private void noItemSelect(){
        rlHome.setSelected(false);
        rlGift.setSelected(false);
        rlShare.setSelected(false);
    }

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_menu:// 打开左边抽屉
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                    break;
            }
        }
    };

    // 设置状态栏
    private void setWindowStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            // 设置状态栏颜色
            getWindow().setBackgroundDrawableResource(R.color.main_color);
        }
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
}

