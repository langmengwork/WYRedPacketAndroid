package com.example.lx.wyredpacketandroid.entity;

import com.google.gson.annotations.SerializedName;

public class CreateRedpackEntity {


    /**
     * err_code : 200
     * return_msg : success
     * data : {"appid":"wx7cd6f5455f4c3f47","partnerid":"1503291881","timestamp":1525661896,"noncestr":"s8n475jgtwx5xrp2zflodzeevvnx8dxq","package":"Sign=WXPay","prepayid":"wx07105816436771e92e1c1c4d3470426438","sign":"9B55E7F94E1986ACEA541E327ADAD0FF","ordernum":"2018050793542"}
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
         * appid : wx7cd6f5455f4c3f47
         * partnerid : 1503291881
         * timestamp : 1525661896
         * noncestr : s8n475jgtwx5xrp2zflodzeevvnx8dxq
         * package : Sign=WXPay
         * prepayid : wx07105816436771e92e1c1c4d3470426438
         * sign : 9B55E7F94E1986ACEA541E327ADAD0FF
         * ordernum : 2018050793542
         */

        private String appid;
        private String partnerid;
        private int timestamp;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String prepayid;
        private String sign;
        private String ordernum;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "appid='" + appid + '\'' +
                    ", partnerid='" + partnerid + '\'' +
                    ", timestamp=" + timestamp +
                    ", noncestr='" + noncestr + '\'' +
                    ", packageX='" + packageX + '\'' +
                    ", prepayid='" + prepayid + '\'' +
                    ", sign='" + sign + '\'' +
                    ", ordernum='" + ordernum + '\'' +
                    '}';
        }
    }
}
