package com.example.lx.wyredpacketandroid.ui.activity.record.entity;

public class RecordPopEntity {

    private String title;
    private boolean state = false;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public RecordPopEntity(String title, boolean state, int type) {
        this.title = title;
        this.state = state;
        this.type = type;
    }

    public RecordPopEntity() {
    }

    public RecordPopEntity(String title) {
        this.title = title;
    }
}
