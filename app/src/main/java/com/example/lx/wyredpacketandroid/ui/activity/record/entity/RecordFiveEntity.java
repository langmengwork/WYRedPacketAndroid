package com.example.lx.wyredpacketandroid.ui.activity.record.entity;

import java.util.List;

public class RecordFiveEntity {


    /**
     * err_code : 200
     * return_msg : success
     * data : {"totalMoney":0,"list":[{"created_at":"2018-04-28 12:31:13","money":0,"status":1,"remark":""},{"created_at":"2018-04-28 12:35:12","money":0,"status":1,"remark":""}]}
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
         * totalMoney : 0
         * list : [{"created_at":"2018-04-28 12:31:13","money":0,"status":1,"remark":""},{"created_at":"2018-04-28 12:35:12","money":0,"status":1,"remark":""}]
         */

        private String totalMoney;
        private List<ListBean> list;

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
            this.totalMoney = totalMoney;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * created_at : 2018-04-28 12:31:13
             * money : 0
             * status : 1
             * remark :
             */

            private String created_at;
            private double money;
            private int status;
            private String remark;

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
