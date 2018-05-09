package com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity;

import java.util.List;

public class ReceiveDetailEntity {


    /**
     * err_code : 200
     * return_msg : success
     * data : {"totalNum":1000,"service_rate":"32.5%","totalMoney":1.8495,"userList":[{"money":1.8495,"headimgurl":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","name":"测试"}],"currentNum":1}
     */

    private String err_code;
    private String return_msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * totalNum : 1000
         * service_rate : 32.5%
         * totalMoney : 1.8495
         * userList : [{"money":1.8495,"headimgurl":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","name":"测试"}]
         * currentNum : 1
         */

        private int totalNum;
        private String service_rate;
        private double totalMoney;
        private int currentNum;
        private List<UserListBean> userList;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public String getService_rate() {
            return service_rate;
        }

        public void setService_rate(String service_rate) {
            this.service_rate = service_rate;
        }

        public double getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(double totalMoney) {
            this.totalMoney = totalMoney;
        }

        public int getCurrentNum() {
            return currentNum;
        }

        public void setCurrentNum(int currentNum) {
            this.currentNum = currentNum;
        }

        public List<UserListBean> getUserList() {
            return userList;
        }

        public void setUserList(List<UserListBean> userList) {
            this.userList = userList;
        }

        public static class UserListBean {
            /**
             * money : 1.8495
             * headimgurl : https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg
             * name : 测试
             */

            private double money;
            private String headimgurl;
            private String name;

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
