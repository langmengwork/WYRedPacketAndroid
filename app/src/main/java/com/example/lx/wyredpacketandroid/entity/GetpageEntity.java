package com.example.lx.wyredpacketandroid.entity;

import java.util.List;

public class GetpageEntity {


    /**
     * err_code : 200
     * return_msg : success
     * data : [{"id":1,"type":2,"uid":1000000,"secret":true,"created_at":"1524653149000","owner":"测试","logo":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg"},{"id":2,"type":2,"uid":1000000,"secret":false,"owner":"测试","logo":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg"},{"id":3,"type":2,"uid":1000000,"secret":false,"owner":"测试","logo":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg"},{"id":5,"type":2,"uid":1000000,"secret":true,"created_at":"1524658046000","owner":"测试","logo":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg"},{"id":6,"type":2,"uid":1000000,"secret":true,"created_at":"1524659052000","owner":"测试","logo":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg"},{"id":7,"type":2,"uid":1000000,"secret":true,"created_at":"1524659668000","owner":"测试","logo":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg"},{"id":8,"type":2,"uid":1000000,"secret":false,"owner":"测试","logo":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg"},{"id":11,"type":1,"uid":5000000,"secret":true,"created_at":"1524709421000","owner":"大宇宙银行","logo":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg"},{"id":4,"type":4,"owner":"同城红包","logo":"https://tongchenghongbao.yaxiangame.com/style/images/logo.jpg","secret":false},{"id":4,"type":4,"owner":"同城红包","logo":"https://tongchenghongbao.yaxiangame.com/style/images/logo.jpg","secret":false}]
     */

    private String err_code;
    private String return_msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * type : 2
         * uid : 1000000
         * secret : true
         * created_at : 1524653149000
         * owner : 测试
         * logo : https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg
         */

        private int id;
        private int type;
        private int uid;
        private boolean secret;
        private String created_at;
        private String owner;
        private String logo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public boolean isSecret() {
            return secret;
        }

        public void setSecret(boolean secret) {
            this.secret = secret;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }
}
