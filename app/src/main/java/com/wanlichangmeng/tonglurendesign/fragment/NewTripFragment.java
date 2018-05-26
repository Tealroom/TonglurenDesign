package com.wanlichangmeng.tonglurendesign.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wanlichangmeng.tonglurendesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewTripFragment extends Fragment {

    @BindView(R.id.button1_fragment_new_trip)
    Button nextstep;


    TripDetailSetFragment fragment2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_trip, container, false);
        ButterKnife.bind(this,view);

        nextstep.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                fragment2=new TripDetailSetFragment();
                //获取fragment管理器
                FragmentManager fm = getFragmentManager();
                //打开事务
                FragmentTransaction ft = fm.beginTransaction();
                //把内容显示至帧布局
                ft.replace(R.id.fragment1_activity_trip, fragment2);
                //提交
                ft.commit();
            }
        });
        return view;
    }





}
