package com.example.lx.wyredpacketandroid.entity;

public class UserInfoEntity {


    /**
     * data : {"city":"","country":"","distance":1000,"headimgurl":"","id":1000004,"nickname":"","province":"","sex":1}
     * err_code : 200
     * return_msg : success
     */

    private DataBean data;
    private String err_code;
    private String return_msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public static class DataBean {
        /**
         * city :
         * country :
         * distance : 1000
         * headimgurl :
         * id : 1000004
         * nickname :
         * province :
         * sex : 1
         */

        private String city;
        private String country;
        private int distance;
        private String headimgurl;
        private int id;
        private String nickname;
        private String province;
        private int sex;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }
}
