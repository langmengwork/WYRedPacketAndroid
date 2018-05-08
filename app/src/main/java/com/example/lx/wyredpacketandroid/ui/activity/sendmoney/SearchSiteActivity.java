package com.example.lx.wyredpacketandroid.ui.activity.sendmoney;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.base.BaseActivity;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.adapter.SearchSiteAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.entity.SearchAddressEntity;
import com.example.lx.wyredpacketandroid.utils.LogUtil;
import com.example.lx.wyredpacketandroid.utils.ToastUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SearchSiteActivity extends BaseActivity implements View.OnClickListener, AMap.OnMyLocationChangeListener, Inputtips.InputtipsListener, TextWatcher, GeocodeSearch.OnGeocodeSearchListener, SearchSiteAdapter.ItemOnclick {

    private static String RANGE_TYPE = "一公里";

    private ImageView search_site_back;
    private TextView search_site_confirm;
    private EditText search_site_edit;
    private RadioButton search_site_range_kilometre;
    private RadioButton search_site_range_county;
    private RadioButton search_site_range_city;
    private MapView search_map;
    private AMap aMap = null;
    private MyLocationStyle myLocationStyle;
    private int size = 13;
    private RecyclerView search_site_recycler;
    private SearchSiteAdapter searchSiteAdapter;
    private ArrayList<SearchAddressEntity> addresslist;
    private GeocodeSearch geocoderSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        initMap(savedInstanceState);

        initLocation();

        initAddress();
    }

    private void initAddress() {

        search_site_recycler.setHasFixedSize(true);
        search_site_recycler.setLayoutManager(new LinearLayoutManager(this));
        //添加分割线
        search_site_recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        addresslist = new ArrayList<>();
        searchSiteAdapter = new SearchSiteAdapter(this, addresslist);
        searchSiteAdapter.setItemOnclick(this);
        search_site_recycler.setAdapter(searchSiteAdapter);

    }

    @Override
    protected void initView() {

        search_site_back = (ImageView) findViewById(R.id.search_site_back);
        search_site_back.setOnClickListener(this);
        search_site_confirm = (TextView) findViewById(R.id.search_site_confirm);
        search_site_confirm.setOnClickListener(this);
        search_site_edit = (EditText) findViewById(R.id.search_site_edit);
        search_site_edit.setOnClickListener(this);
        search_site_range_kilometre = (RadioButton) findViewById(R.id.search_site_range_kilometre);
        search_site_range_kilometre.setOnClickListener(this);
        search_site_range_county = (RadioButton) findViewById(R.id.search_site_range_county);
        search_site_range_county.setOnClickListener(this);
        search_site_range_city = (RadioButton) findViewById(R.id.search_site_range_city);
        search_site_range_city.setOnClickListener(this);
        search_site_edit.addTextChangedListener(this);
        search_site_recycler = findViewById(R.id.search_site_recycler);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_search_site;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        search_map.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        search_map.onResume();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        search_map.onPause();
    }

    private void initMap(Bundle savedInstanceState) {

        search_map = findViewById(R.id.search_map);

        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        search_map.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = search_map.getMap();
        }
    }

    private void initLocation() {

        initLocationStyle();

        initMapView();

    }

    private void initPoi(String newText) {

        //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
        InputtipsQuery inputquery = new InputtipsQuery(newText, null);
        inputquery.setCityLimit(false);//限制在当前城市

        Inputtips inputTips = new Inputtips(this, inputquery);
        inputTips.setInputtipsListener(this);

        inputTips.requestInputtipsAsyn();
    }

    private void initMapView() {

//        设置希望展示的地图缩放级别
        CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(size);
        aMap.moveCamera(mCameraUpdate);

        UiSettings mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象

        mUiSettings.setScaleControlsEnabled(true);//控制比例尺控件是否显示
        mUiSettings.setZoomControlsEnabled(false);//控制缩放按钮是否显示

        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        //设置定位监听
        aMap.setOnMyLocationChangeListener(this);

        //创建地理编码监听
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);

    }

    private void initLocationStyle() {

        //初始化定位蓝点样式类
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(myLocationStyle.LOCATION_TYPE_LOCATE);
        myLocationStyle.interval(1000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。

        myLocationStyle.strokeColor(Color.TRANSPARENT);//设置定位蓝点精度圆圈的边框颜色的方法。
        myLocationStyle.radiusFillColor(Color.TRANSPARENT);//设置定位蓝点精度圆圈的填充颜色的方法。

        //        自定义定位点
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pin);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
        myLocationStyle.myLocationIcon(bitmapDescriptor);

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.search_site_confirm:

                //回传参数

                Intent intent = getIntent();
                intent.putExtra("type", RANGE_TYPE);
                for (SearchAddressEntity entity : addresslist) {
                    if (entity.isIsstate()) {
                        intent.putExtra("address", new Gson().toJson(entity));
                    }
                }
                setResult(RESULT_OK,intent);

                finish();

                break;
            case R.id.search_site_range_kilometre:

                //重置range type
                RANGE_TYPE = search_site_range_kilometre.getText().toString();
                break;
            case R.id.search_site_range_county:

                //重置range type
                RANGE_TYPE = search_site_range_county.getText().toString();

                break;
            case R.id.search_site_range_city:

                //重置range type
                RANGE_TYPE = search_site_range_city.getText().toString();
                break;
        }
    }

    @Override
    public void onMyLocationChange(Location location) {

        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(location.getLatitude(), location.getLongitude()), 0,GeocodeSearch.AMAP);

        geocoderSearch.getFromLocationAsyn(query);
    }

    //POI监听
    @Override
    public void onGetInputtips(List<Tip> list, int i) {

        addresslist.clear();

        for (Tip tip : list) {
            if (!tip.getPoiID().isEmpty() && tip.getPoint() != null) {
                SearchAddressEntity entity = new SearchAddressEntity();
                if (tip == list.get(0)) {
                    entity.setIsstate(true);
                }
                entity.setAddress(tip.getAddress());
                entity.setName(tip.getName());
                entity.setPoint(tip.getPoint());
                addresslist.add(entity);
            }
        }

        searchSiteAdapter.notifyDataSetChanged();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (!s.toString().isEmpty()) {
            initPoi(s.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    //逆地理编码
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

        initPoi(regeocodeResult.getRegeocodeAddress().getFormatAddress());
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    @Override
    public void siteClick(int position) {

        for (SearchAddressEntity entity : addresslist) {
            entity.setIsstate(false);
        }
        addresslist.get(position).setIsstate(true);
        searchSiteAdapter.notifyDataSetChanged();

        LatLng latLng = new LatLng(addresslist.get(position).getPoint().getLatitude(), addresslist.get(position).getPoint().getLongitude());

        //参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)
        CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng,size,0,0));
        aMap.moveCamera(mCameraUpdate);
    }
}
