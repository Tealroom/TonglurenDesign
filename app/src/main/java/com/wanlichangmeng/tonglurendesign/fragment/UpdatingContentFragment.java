package com.wanlichangmeng.tonglurendesign.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanlichangmeng.tonglurendesign.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class UpdatingContentFragment extends Fragment {


    Unbinder unbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_updating_content, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    public static  UpdatingContentFragment newInstance(String whatever){
        UpdatingContentFragment fragmentOne = new UpdatingContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data", whatever);
        //fragment保存参数，传入一个Bundle对象
        fragmentOne.setArguments(bundle);
        return fragmentOne;
    }
}
