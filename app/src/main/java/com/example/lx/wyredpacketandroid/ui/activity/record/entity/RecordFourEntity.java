package com.example.lx.wyredpacketandroid.ui.activity.record.entity;

import java.util.List;

public class RecordFourEntity {


    /**
     * err_code : 200
     * return_msg : success
     * data : {"totalStockValue":0,"list":[{"created_at":"2018-04-27 12:12:57","type":1,"title":"广告红包","stockValue":0},{"created_at":"2018-04-27 12:22:04","type":1,"title":"广告红包","stockValue":0}]}
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
         * totalStockValue : 0
         * list : [{"created_at":"2018-04-27 12:12:57","type":1,"title":"广告红包","stockValue":0},{"created_at":"2018-04-27 12:22:04","type":1,"title":"广告红包","stockValue":0}]
         */

        private String totalStockValue;
        private List<ListBean> list;

        public String getTotalStockValue() {
            return totalStockValue;
        }

        public void setTotalStockValue(String totalStockValue) {
            this.totalStockValue = totalStockValue;
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
             * stockValue : 0
             */

            private String created_at;
            private int type;
            private String title;
            private String stockValue;

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

            public String getStockValue() {
                return stockValue;
            }

            public void setStockValue(String stockValue) {
                this.stockValue = stockValue;
            }
        }
    }
}
