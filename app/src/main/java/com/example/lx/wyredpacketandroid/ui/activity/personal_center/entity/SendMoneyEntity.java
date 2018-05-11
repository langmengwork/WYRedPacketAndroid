package com.example.lx.wyredpacketandroid.ui.activity.personal_center.entity;

import java.util.List;

public class SendMoneyEntity {

    /**
     * err_code : 200
     * return_msg : success
     * data : {"totalMoney":3100.0200000000004,"totalPeople":6,"list":[{"id":29,"uid":1000000,"type":3,"content":"10023","image":[],"packImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","packName":"凭什么说","pack_id":29,"statistics":{"viewNum":"21","praiseNum":1,"messageNum":0,"is_praise":false}},{"id":33,"uid":1000000,"type":3,"content":"10023","image":[],"packImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","packName":"凭什么说","pack_id":33,"statistics":{"viewNum":"41","praiseNum":0,"messageNum":0,"is_praise":false}},{"id":34,"uid":1000000,"type":3,"content":"难兄难弟激动就接电话","image":[],"packImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","packName":"凭什么说","pack_id":34,"statistics":{"viewNum":"119","praiseNum":0,"messageNum":5,"is_praise":false}},{"id":38,"uid":1000000,"type":3,"content":"10023","image":["http://images.yaxiangame.com/2018-05-04_MTchFKfp.png","http://images.yaxiangame.com/2018-05-04_MTchFKfp.png","http://images.yaxiangame.com/2018-05-04_MTchFKfp.png","http://images.yaxiangame.com/2018-05-04_MTchFKfp.png","http://images.yaxiangame.com/2018-05-04_MTchFKfp.png","http://images.yaxiangame.com/2018-05-04_MTchFKfp.png"],"packImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","packName":"凭什么说","pack_id":38,"statistics":{"viewNum":"25","praiseNum":0,"messageNum":0,"is_praise":false}},{"id":35,"uid":1000004,"type":2,"content":"图","image":["http://images.yaxiangame.com/2018-05-04_MTchFKfp.png"],"packImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/d8nYib6w74U1dE1oPReWhrYNPibvqks14SeZiaicgRCJO9wkuTnqsZdvDLPCLmQD5EJnNVVhVc4oWrkXpcHRerJV5A/132","packName":"coming","pack_id":35}]}
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
         * totalMoney : 3100.0200000000004
         * totalPeople : 6
         * list : [{"id":29,"uid":1000000,"type":3,"content":"10023","image":[],"packImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","packName":"凭什么说","pack_id":29,"statistics":{"viewNum":"21","praiseNum":1,"messageNum":0,"is_praise":false}},{"id":33,"uid":1000000,"type":3,"content":"10023","image":[],"packImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","packName":"凭什么说","pack_id":33,"statistics":{"viewNum":"41","praiseNum":0,"messageNum":0,"is_praise":false}},{"id":34,"uid":1000000,"type":3,"content":"难兄难弟激动就接电话","image":[],"packImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","packName":"凭什么说","pack_id":34,"statistics":{"viewNum":"119","praiseNum":0,"messageNum":5,"is_praise":false}},{"id":38,"uid":1000000,"type":3,"content":"10023","image":["http://images.yaxiangame.com/2018-05-04_MTchFKfp.png","http://images.yaxiangame.com/2018-05-04_MTchFKfp.png","http://images.yaxiangame.com/2018-05-04_MTchFKfp.png","http://images.yaxiangame.com/2018-05-04_MTchFKfp.png","http://images.yaxiangame.com/2018-05-04_MTchFKfp.png","http://images.yaxiangame.com/2018-05-04_MTchFKfp.png"],"packImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132","packName":"凭什么说","pack_id":38,"statistics":{"viewNum":"25","praiseNum":0,"messageNum":0,"is_praise":false}},{"id":35,"uid":1000004,"type":2,"content":"图","image":["http://images.yaxiangame.com/2018-05-04_MTchFKfp.png"],"packImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/d8nYib6w74U1dE1oPReWhrYNPibvqks14SeZiaicgRCJO9wkuTnqsZdvDLPCLmQD5EJnNVVhVc4oWrkXpcHRerJV5A/132","packName":"coming","pack_id":35}]
         */

        private double totalMoney;
        private int totalPeople;
        private List<ListBean> list;

        public double getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(double totalMoney) {
            this.totalMoney = totalMoney;
        }

        public int getTotalPeople() {
            return totalPeople;
        }

        public void setTotalPeople(int totalPeople) {
            this.totalPeople = totalPeople;
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
             * uid : 1000000
             * type : 3
             * content : 10023
             * image : []
             * packImg : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkjcARRKCeFq1seDLpQTiaChaXeFWDKIiaPicPakwfWyiaIuibFRKTh3NEuwWz1EbDADbWGCibb3U3m8Pg/132
             * packName : 凭什么说
             * pack_id : 29
             * statistics : {"viewNum":"21","praiseNum":1,"messageNum":0,"is_praise":false}
             */

            private int id;
            private int uid;
            private int type;
            private String content;
            private String packImg;
            private String packName;
            private int pack_id;
            private StatisticsBean statistics;
            private List<?> image;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPackImg() {
                return packImg;
            }

            public void setPackImg(String packImg) {
                this.packImg = packImg;
            }

            public String getPackName() {
                return packName;
            }

            public void setPackName(String packName) {
                this.packName = packName;
            }

            public int getPack_id() {
                return pack_id;
            }

            public void setPack_id(int pack_id) {
                this.pack_id = pack_id;
            }

            public StatisticsBean getStatistics() {
                return statistics;
            }

            public void setStatistics(StatisticsBean statistics) {
                this.statistics = statistics;
            }

            public List<?> getImage() {
                return image;
            }

            public void setImage(List<?> image) {
                this.image = image;
            }

            public static class StatisticsBean {
                /**
                 * viewNum : 21
                 * praiseNum : 1
                 * messageNum : 0
                 * is_praise : false
                 */

                private String viewNum;
                private int praiseNum;
                private int messageNum;
                private boolean is_praise;

                public String getViewNum() {
                    return viewNum;
                }

                public void setViewNum(String viewNum) {
                    this.viewNum = viewNum;
                }

                public int getPraiseNum() {
                    return praiseNum;
                }

                public void setPraiseNum(int praiseNum) {
                    this.praiseNum = praiseNum;
                }

                public int getMessageNum() {
                    return messageNum;
                }

                public void setMessageNum(int messageNum) {
                    this.messageNum = messageNum;
                }

                public boolean isIs_praise() {
                    return is_praise;
                }

                public void setIs_praise(boolean is_praise) {
                    this.is_praise = is_praise;
                }
            }
        }
    }
}
