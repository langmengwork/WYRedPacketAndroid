package com.example.lx.wyredpacketandroid.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.entity.GetPackEntity;
import com.example.lx.wyredpacketandroid.entity.OpenPackEntity;
import com.example.lx.wyredpacketandroid.mvp.contract.MainContract;
import com.example.lx.wyredpacketandroid.mvp.presenter.MapPresenter;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.PackDetailsActivity;
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.PersonalActivity;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.SendActivity;
import com.example.lx.wyredpacketandroid.utils.DateUtil;
import com.example.lx.wyredpacketandroid.utils.GPSLocation;
import com.example.lx.wyredpacketandroid.utils.LocationUtils;
import com.example.lx.wyredpacketandroid.utils.LogUtil;
import com.example.lx.wyredpacketandroid.utils.ToastUtil;
import com.example.lx.wyredpacketandroid.utils.UserInfoUtil;
import com.example.lx.wyredpacketandroid.utils.customview.OpenDialogUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AMap.OnMyLocationChangeListener, View.OnClickListener,MainContract.mapView, AMap.OnMarkerClickListener, OpenDialogUtil.openListner {

    private static final String TAG = "Tag";
    private MapView mMapView = null;
    private MyLocationStyle myLocationStyle;
    private AMap aMap;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private double distance = 5000; //用户可接收的红包范围
    private Circle circle;
    private float scalePerPixel;
    private int width;
    private int size = 13;
    private LatLng latLng;
    private boolean initstate = true,aa = true;
    private Toolbar map_toolbar;
    private CheckBox map_collect_check;
    private CheckBox map_wallet_check;
    private RelativeLayout map_bottombar;
    private MapView mapView;
    private LinearLayout map_send_money;
    private ImageView map_expand_img,map_personal_center;
    private RelativeLayout map_expand_layout;
    private LinearLayout map_refresh;
    private LinearLayout map_history;
    private ImageView map_advert_img;
    private RelativeLayout map_advert_layout;
    private MapPresenter presenter;
    private List<GetPackEntity.DataBean.ListBean> markerList;
    private Bitmap makerBitmap;
    private TextView map_title_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        presenter = new MapPresenter(this);

        //获取用户信息
        presenter.obtainUserInfo();

        initMap(savedInstanceState);

        initLocation();

        findViewById(R.id.map_send_money).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SendActivity.class));
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMyLocationChange(Location location) {

        //从location对象中获取经纬度信息，地址描述信息，建议拿到位置之后调用逆地理编码接口获取（获取地址描述数据章节有介绍）

        //获取当前经纬度
        latLng = new LatLng(location.getLatitude(), location.getLongitude());

        //重绘范围圈
        circle.setCenter(latLng);


        if (aa) {
            HashMap<String, String> map = new HashMap<>();
            map.put("uid", UserInfoUtil.instance().getId()+"");
            map.put("point", latLng.longitude+","+latLng.latitude);
            presenter.obtainGetPack(map);
            aa = false;
        }



        changeZoom();
    }

    private void drawMaker(LatLng latLng) {


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void initLocation() {

        initLocationStyle();

        setWidth();

        initMapView();

    }

    private void initMapView() {

//        设置希望展示的地图缩放级别
        CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(size);
        aMap.moveCamera(mCameraUpdate);

        // 绘制一个圆形
        circle = aMap.addCircle(new CircleOptions().center(new LatLng(0.0, 0.0))
                .radius(distance)
                .fillColor(Color.argb(50, 1, 1, 1))
                .strokeColor(Color.argb(50, 1, 1, 1))
                .strokeWidth(2));

        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象

        mUiSettings.setScaleControlsEnabled(true);//控制比例尺控件是否显示
        mUiSettings.setZoomControlsEnabled(false);//控制缩放按钮是否显示
//        mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_RIGHT);//设置logo位置

//        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        //设置定位监听
        aMap.setOnMyLocationChangeListener(this);

    }

    private void initLocationStyle() {

        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
        myLocationStyle.interval(1000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。

        myLocationStyle.strokeColor(Color.TRANSPARENT);//设置定位蓝点精度圆圈的边框颜色的方法。
        myLocationStyle.radiusFillColor(Color.TRANSPARENT);//设置定位蓝点精度圆圈的填充颜色的方法。

        //        自定义定位点
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pin);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
        myLocationStyle.myLocationIcon(bitmapDescriptor);

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style

    }

    private void initMap(Bundle savedInstanceState) {

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.mapView);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = mMapView.getMap();
        }
    }

    private void changeZoom() {

        //设置合适的缩放比例
        if (initstate) {

            //获取当前地图上的比例尺，单位m
            scalePerPixel = aMap.getScalePerPixel();

            if (circle.getRadius() * 2 > scalePerPixel * width) {
                size -= 1;
                CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(size);
                aMap.moveCamera(mCameraUpdate);
            } else {
                initstate = false;
            }
        }
    }

    // 屏幕宽度（像素）
    public void setWidth() {

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        width = metric.widthPixels;
//        int height = metric.heightPixels;  // 屏幕高度（像素）
//        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
//        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）

    }

    private void initView() {
        map_toolbar = (Toolbar) findViewById(R.id.map_toolbar);
        map_collect_check = (CheckBox) findViewById(R.id.map_collect_check);
        map_wallet_check = (CheckBox) findViewById(R.id.map_wallet_check);
        map_bottombar = (RelativeLayout) findViewById(R.id.map_bottombar);
        mapView = (MapView) findViewById(R.id.mapView);
        map_send_money = (LinearLayout) findViewById(R.id.map_send_money);
        map_expand_img = (ImageView) findViewById(R.id.map_expand_img);
        map_personal_center = (ImageView) findViewById(R.id.map_personal_center);
        map_personal_center.setOnClickListener(this);
        map_expand_layout = (RelativeLayout) findViewById(R.id.map_expand_layout);
        map_refresh = (LinearLayout) findViewById(R.id.map_refresh);
        map_history = (LinearLayout) findViewById(R.id.map_history);
        map_advert_img = (ImageView) findViewById(R.id.map_advert_img);
        map_advert_layout = (RelativeLayout) findViewById(R.id.map_advert_layout);
        map_title_money = findViewById(R.id.map_title_money);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.map_personal_center:

                startActivity(new Intent(this, PersonalActivity.class));

                break;
        }
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        int position = Integer.parseInt(marker.getId().substring(marker.getId().length()- 1))-2;

        LogUtil.e("position"+position);
        if (position >= 0) {

            OpenDialogUtil instance = OpenDialogUtil.instance();

            instance.showDialog(this, markerList.get(position),marker);

            instance.setOpenListner(this);

        }

        return false;
    }

    @Override
    public void openSuccess(OpenPackEntity.DataBean data, Marker marker) {

        ToastUtil.showShortToast("成功" + data.getPack_id());

        startActivity(new Intent(this, PackDetailsActivity.class)
        .putExtra("data",new Gson().toJson(data)));

        marker.remove();

    }

    @Override
    public void openError(String error, Marker marker) {

        ToastUtil.showShortToast(error);

        marker.remove();
    }

    @Override
    public void showView(GetPackEntity.DataBean data) {

        markerList = data.getList();

        //设置余额
        String moneystr =  "¥"+data.getUserStock();
        SpannableString spanString = new SpannableString(moneystr);
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(30);
        spanString.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        map_title_money.setText(spanString);

        for (final GetPackEntity.DataBean.ListBean datum : markerList) {
//            //绘制覆盖物
//            drawMaker(latLng);
            GPSLocation gpsLocation = new GPSLocation();
            gpsLocation.setLatitude(latLng.latitude);
            gpsLocation.setLongitude(latLng.longitude);
            GPSLocation loca = LocationUtils.GetRandomLocation(gpsLocation, distance);
            LatLng markLatlng = new LatLng(loca.getLatitude(), loca.getLongitude());
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.position(markLatlng);
            markerOption.draggable(false);//设置Marker可拖动

//            LogUtil.e("type："+datum.getType()+"  is:"+datum.isSecret());

            //设置红包图标
            if (datum.isSecret()) {
                makerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.encryption_money);
            } else if (datum.getType() == 1 || datum.getType() == 4) {
                makerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ordinary_money);
            } else if (datum.getType() == 2 || datum.getType() == 3) {
                makerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blessing_money);
            }

            markerOption.icon(BitmapDescriptorFactory.fromBitmap(makerBitmap));

            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption.setFlat(true);//设置marker平贴地图效果

            aMap.addMarker(markerOption);

            aMap.setOnMarkerClickListener(this);

        }
    }
}
