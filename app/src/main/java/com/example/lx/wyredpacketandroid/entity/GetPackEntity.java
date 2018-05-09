package com.example.lx.wyredpacketandroid.entity;

import java.util.List;

public class GetPackEntity {


    /**
     * data : {"last_get_pack_time":1525752344000,"list":[{"id":29,"logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","owner":"凭什么说","secret":false,"type":3,"uid":1000000,"created_at":""},{"created_at":"","id":34,"logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","owner":"凭什么说","secret":false,"type":3,"uid":1000000},{"id":37,"logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","owner":"凭什么说","secret":false,"type":2,"uid":1000000},{"id":31,"logo":"https://tongchenghongbao.yaxiangame.com/style/images/logo.jpg","owner":"同城红包","secret":false,"type":4},{"id":31,"logo":"https://tongchenghongbao.yaxiangame.com/style/images/logo.jpg","owner":"同城红包","secret":false,"type":4}],"userStock":66.3801}
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
         * last_get_pack_time : 1525752344000
         * list : [{"id":29,"logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","owner":"凭什么说","secret":false,"type":3,"uid":1000000},{"created_at":"","id":34,"logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","owner":"凭什么说","secret":false,"type":3,"uid":1000000},{"id":37,"logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","owner":"凭什么说","secret":false,"type":2,"uid":1000000},{"id":31,"logo":"https://tongchenghongbao.yaxiangame.com/style/images/logo.jpg","owner":"同城红包","secret":false,"type":4},{"id":31,"logo":"https://tongchenghongbao.yaxiangame.com/style/images/logo.jpg","owner":"同城红包","secret":false,"type":4}]
         * userStock : 66.3801
         */

        private long last_get_pack_time;
        private double userStock;
        private List<ListBean> list;

        public long getLast_get_pack_time() {
            return last_get_pack_time;
        }

        public void setLast_get_pack_time(long last_get_pack_time) {
            this.last_get_pack_time = last_get_pack_time;
        }

        public double getUserStock() {
            return userStock;
        }

        public void setUserStock(double userStock) {
            this.userStock = userStock;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 29
             * logo : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132
             * owner : 凭什么说
             * secret : false
             * type : 3
             * uid : 1000000
             * created_at :
             */

            private int id;
            private String logo;
            private String owner;
            private boolean secret;
            private int type;
            private int uid;
            private String created_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public boolean isSecret() {
                return secret;
            }

            public void setSecret(boolean secret) {
                this.secret = secret;
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

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }
        }
    }
}
