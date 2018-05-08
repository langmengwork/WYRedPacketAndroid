package com.example.lx.wyredpacketandroid.utils;

public class UserInfoUtil {

    private static UserInfoUtil userInfoUtil;

    private int id;
    private String nickname;
    private int sex;
    private Object headimgurl;
    private String country;
    private String province;
    private String city;
    private int distance;

    private UserInfoUtil() {
    }

    public static UserInfoUtil instance() {

        if (userInfoUtil == null) {
            synchronized (UserInfoUtil.class) {
                if (userInfoUtil == null) {
                    userInfoUtil = new UserInfoUtil();
                }
            }
        }
        return userInfoUtil;
    }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public Object getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(Object headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
    }
}
