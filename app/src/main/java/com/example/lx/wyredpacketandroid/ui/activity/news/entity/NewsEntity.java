package com.example.lx.wyredpacketandroid.ui.activity.news.entity;

import java.util.List;

public class NewsEntity {


    /**
     * err_code : 200
     * return_msg : success
     * data : [{"id":35,"pid":0,"pack_id":37,"uid":1000000,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","name":"凭什么说","content":"啦啦啦","created_at":"2018-05-09 11:50:16","param_id":35,"pack_image":"http://images.yaxiangame.com/2018-05-04_MTchFKfp.png"},{"id":34,"pid":0,"pack_id":26,"uid":1000000,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","name":"凭什么说","content":"我来留个言","created_at":"2018-05-09 11:48:23","param_id":34,"pack_image":""}]
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
         * id : 35
         * pid : 0
         * pack_id : 37
         * uid : 1000000
         * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132
         * name : 凭什么说
         * content : 啦啦啦
         * created_at : 2018-05-09 11:50:16
         * param_id : 35
         * pack_image : http://images.yaxiangame.com/2018-05-04_MTchFKfp.png
         */

        private int id;
        private int pid;
        private int pack_id;
        private int uid;
        private String headimgurl;
        private String name;
        private String content;
        private String created_at;
        private int param_id;
        private String pack_image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getPack_id() {
            return pack_id;
        }

        public void setPack_id(int pack_id) {
            this.pack_id = pack_id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getParam_id() {
            return param_id;
        }

        public void setParam_id(int param_id) {
            this.param_id = param_id;
        }

        public String getPack_image() {
            return pack_image;
        }

        public void setPack_image(String pack_image) {
            this.pack_image = pack_image;
        }
    }
}
