package com.example.lx.wyredpacketandroid.ui.activity.sendmoney.entity;

import com.amap.api.services.core.LatLonPoint;

public class SearchAddressEntity {

    private boolean isstate = false;
    private String address;
    private String name;
    private LatLonPoint point;

    public LatLonPoint getPoint() {
        return point;
    }

    public void setPoint(LatLonPoint point) {
        this.point = point;
    }

    public boolean isIsstate() {
        return isstate;
    }

    public void setIsstate(boolean isstate) {
        this.isstate = isstate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
