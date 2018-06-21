package com.wanlichangmeng.tonglurendesign.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.amap.api.maps.MapView;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：周才智
 * 时间：2018/05/18
 * 注释：消息
 */
public class MessageFragment extends Fragment {
    @BindView(R.id.ll_bar4)
    LinearLayout ll_bar4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResume() {
        ActivityUtils.initStateInFragment(this,ll_bar4);
//        SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(R.color.colorPrimary);//设置系统状态栏颜色
//        tintManager.setStatusBarTintDrawable(getResources().getDrawable(R.drawable.mybgcolor_02));//设置系统状态栏背景图
        super.onResume();
    }

}
