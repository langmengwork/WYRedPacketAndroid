package com.example.lx.wyredpacketandroid.ui.activity.record.entity;

import java.util.List;

public class RecordTwoEntity {


    /**
     * err_code : 200
     * return_msg : success
     * data : {"totalStockNum":23.1231,"list":[{"created_at":"2018-04-27 12:12:57","type":1,"title":"广告红包","stockNum":21.2736},{"created_at":"2018-04-27 12:22:04","type":1,"title":"广告红包","stockNum":1.8495}]}
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
         * totalStockNum : 23.1231
         * list : [{"created_at":"2018-04-27 12:12:57","type":1,"title":"广告红包","stockNum":21.2736},{"created_at":"2018-04-27 12:22:04","type":1,"title":"广告红包","stockNum":1.8495}]
         */

        private String totalStockNum;
        private List<ListBean> list;

        public String getTotalStockNum() {
            return totalStockNum;
        }

        public void setTotalStockNum(String totalStockNum) {
            this.totalStockNum = totalStockNum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * created_at : 2018-04-27 12:12:57
             * type : 1
             * title : 广告红包
             * stockNum : 21.2736
             */

            private String created_at;
            private int type;
            private String title;
            private String stockNum;

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

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

            public String getStockNum() {
                return stockNum;
            }

            public void setStockNum(String stockNum) {
                this.stockNum = stockNum;
            }
        }
    }
}
