package com.wanlichangmeng.tonglurendesign.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.adapter.TabFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 作者：周才智
 * 时间：2018/05/18
 * 注释：首页
 */
public class HomeFragment extends Fragment implements AMap.InfoWindowAdapter,AMap.OnMarkerClickListener {

    //获取地图控件引用
    @BindView(R.id.map1)
    MapView mMapView;

    @BindView(R.id.fdag)
    Button btn1;

    @BindView(R.id.main_home_info)
    LinearLayout info_window;
//    //获取地图控件引用
//    @BindView(R.id.main1)
//    LinearLayout relative;



    private MarkerOptions markerOption;
    private UiSettings mUiSettings;
    private AMap aMap;
    private Marker marker;
    private Marker marker1;
    private LatLng latlng = new LatLng(39.91746, 116.397481);
    private LatLng latlng1 = new LatLng(39.92746, 116.396481);
    private LatLng latlng2 = new LatLng(39.88250, 116.409468);
    private LatLng latlng3 = new LatLng(39.87814, 116.191765);
    private LatLng latlng4 = new LatLng(39.73481, 116.307089);
    private LatLng latlng5 = new LatLng(39.78416, 116.399999);
    private LatLng latlng6 = new LatLng(40.00779, 116.304431);
    private LatLng latlng7 = new LatLng(40.01384, 116.396730);
    private LatLng latlng8 = new LatLng(39.98383, 116.497937);
    private LatLng latlng9 = new LatLng(39.98746, 116.366481);
    private LatLng latlng10 = new LatLng(39.86390, 116.497245 );
    //private LatLng latlng11 = new LatLng(34.341568, 108.940174);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图



        mMapView.onCreate(savedInstanceState);
        init();
        return view;
    }
    /**
     * 初始化AMap对象
     */
    private void init() {

        if (aMap == null) {
            aMap = mMapView.getMap();
            aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
            mUiSettings = aMap.getUiSettings();
            mUiSettings.setZoomControlsEnabled(false);
            addMarkersToMap();// 往地图上添加marker

        }
        //绑定点击事件监听（这里用的是匿名内部类创建监听）
        btn1.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),"你点击了"+(++i)+"次", Toast.LENGTH_LONG);//提示被点击了

                toast.show();

            }
        });

    }

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {
//        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.user12))
//                .position(latlng)
//                .title("标题")
//                .snippet("详细信息")
//                .draggable(true);
//        marker = aMap.addMarker(markerOption);
//        //aMap.setInfoWindowAdapter(this);
//
//        marker.showInfoWindow();// 设置默认显示一个infowinfow
//        markerOption = new MarkerOptions();
//        markerOption.position(latlng1);
//        markerOption.title("西安市").snippet("西安市：34.341568, 108.940174");
//
//        markerOption.draggable(true);
//        marker1 = aMap.addMarker(markerOption);
//        marker1.showInfoWindow();



        MarkerOptions markerOption1 = new MarkerOptions()
                .position(latlng2).icon(BitmapDescriptorFactory.fromResource(R.drawable.user12))
                .draggable(true);
        MarkerOptions markerOption2 = new MarkerOptions()
                .position(latlng3).icon(BitmapDescriptorFactory.fromResource(R.drawable.user22))
                .draggable(true);
        MarkerOptions markerOption3 = new MarkerOptions()
                .position(latlng4).icon(BitmapDescriptorFactory.fromResource(R.drawable.user32))
                .draggable(true);
        MarkerOptions markerOption4 = new MarkerOptions()
                .position(latlng5).icon(BitmapDescriptorFactory.fromResource(R.drawable.user12))
                .draggable(true);
        MarkerOptions markerOption5 = new MarkerOptions()
                .position(latlng6).icon(BitmapDescriptorFactory.fromResource(R.drawable.user22))
                .draggable(true);
        MarkerOptions markerOption6 = new MarkerOptions()
                .position(latlng7).icon(BitmapDescriptorFactory.fromResource(R.drawable.user32))
                .draggable(true);
        MarkerOptions markerOption7 = new MarkerOptions()
                .position(latlng8).icon(BitmapDescriptorFactory.fromResource(R.drawable.user12))
                .draggable(true);
        MarkerOptions markerOption8 = new MarkerOptions()
                .position(latlng9).icon(BitmapDescriptorFactory.fromResource(R.drawable.user22))
                .draggable(true);
        MarkerOptions markerOption9 = new MarkerOptions()
                .position(latlng10).icon(BitmapDescriptorFactory.fromResource(R.drawable.user32))
                .draggable(true);
        MarkerOptions markerOption10 = new MarkerOptions()
                .position(latlng1).icon(BitmapDescriptorFactory.fromResource(R.drawable.user12))
                .draggable(true);

        ArrayList<MarkerOptions> markerOptionlst = new ArrayList<MarkerOptions>();
        markerOptionlst.add(markerOption1);
        markerOptionlst.add(markerOption2);
        markerOptionlst.add(markerOption3);
        markerOptionlst.add(markerOption4);
        markerOptionlst.add(markerOption5);
        markerOptionlst.add(markerOption6);
        markerOptionlst.add(markerOption7);
        markerOptionlst.add(markerOption8);
        markerOptionlst.add(markerOption9);
        markerOptionlst.add(markerOption10);
        List<Marker> markerlst = aMap.addMarkers(markerOptionlst, true);
    }

    /**
     * 监听自定义infowindow窗口的infocontents事件回调,暂时没有用
     */
    @Override
    public View getInfoContents(Marker marker) {

        View infoContent = getLayoutInflater().inflate(
                R.layout.custom_info_window, null);
        render(marker, infoContent);
        return infoContent;
    }

    /**
     * 监听自定义infowindow窗口的infowindow事件回调,暂时没有用
     */
    @Override
    public View getInfoWindow(Marker marker) {

        View infoWindow = getLayoutInflater().inflate(
                R.layout.custom_info_window, null);

        render(marker, infoWindow);
        return infoWindow;
    }

    /**
     * 自定义infowinfow窗口
     */
    public void render(Marker marker, View view) {

    }

    /**
     * 对marker标注点点击响应事件
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        info_window.setVisibility(View.VISIBLE);
        if (aMap != null) {
//            ViewGroup.LayoutParams params=relative.getLayoutParams();
//            params.height =180;
//            relative.setLayoutParams(params);
//            Log.i("jinlaile",Integer.toString(params.height));
        }


        return false;
    }
}
