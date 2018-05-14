package com.example.lx.wyredpacketandroid.ui.activity.wallet.entity;

public class PutForwardEntity {


    /**
     * err_code : 200
     * return_msg : success
     * data : {"stock":0.00985009,"stockNum":23.1282}
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
         * stock : 0.00985009
         * stockNum : 23.1282
         */

        private double stock;
        private double stockNum;

        public double getStock() {
            return stock;
        }

        public void setStock(double stock) {
            this.stock = stock;
        }

        public double getStockNum() {
            return stockNum;
        }

        public void setStockNum(double stockNum) {
            this.stockNum = stockNum;
        }
    }
}
