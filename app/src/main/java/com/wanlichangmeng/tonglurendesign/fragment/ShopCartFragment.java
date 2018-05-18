package com.wanlichangmeng.tonglurendesign.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanlichangmeng.tonglurendesign.R;

/**
 * 购物车
 */
public class ShopCartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shop_cart, container, false);

        return view;
    }

}
