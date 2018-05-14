package com.example.lx.wyredpacketandroid.ui.activity.wallet.entity;

import java.util.List;

public class UserWalletEntity {


    /**
     * err_code : 200
     * return_msg : success
     * data : {"stockprice":0.01022326,"stocknums":1001.54807498,"min":0.0100016,"max":0.0101744,"list":{"key":["05.04","05.05","05.06","05.07","05.08","05.09","05.10"],"values":[0.0100016,0.0100304,0.0100592,0.010088,0.0101168,0.0101456,0.0101744]}}
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
         * stockprice : 0.01022326
         * stocknums : 1001.54807498
         * min : 0.0100016
         * max : 0.0101744
         * list : {"key":["05.04","05.05","05.06","05.07","05.08","05.09","05.10"],"values":[0.0100016,0.0100304,0.0100592,0.010088,0.0101168,0.0101456,0.0101744]}
         */

        private double stockprice;
        private double stocknums;
        private double min;
        private double max;
        private ListBean list;

        public double getStockprice() {
            return stockprice;
        }

        public void setStockprice(double stockprice) {
            this.stockprice = stockprice;
        }

        public double getStocknums() {
            return stocknums;
        }

        public void setStocknums(double stocknums) {
            this.stocknums = stocknums;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            private List<String> key;
            private List<Double> values;

            public List<String> getKey() {
                return key;
            }

            public void setKey(List<String> key) {
                this.key = key;
            }

            public List<Double> getValues() {
                return values;
            }

            public void setValues(List<Double> values) {
                this.values = values;
            }
        }
    }
}
