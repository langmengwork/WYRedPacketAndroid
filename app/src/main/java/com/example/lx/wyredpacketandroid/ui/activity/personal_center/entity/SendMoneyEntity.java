package com.example.lx.wyredpacketandroid.ui.activity.personal_center.entity;

import java.util.List;

public class SendMoneyEntity {


    /**
     * err_code : 200
     * return_msg : success
     * data : {"totalMoney":3200,"totalPeople":2,"list":[{"uid":1000000,"type":2,"content":"10023","image":"[]","packImg":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","packName":"测试"},{"uid":1000000,"type":2,"content":"10023","image":"[]","packImg":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","packName":"测试"},{"uid":1000000,"type":3,"content":"10023","image":"[]","packImg":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","packName":"测试","statistics":{"viewNum":0,"praiseNum":0,"messageNum":0,"is_praise":false}}]}
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
         * totalMoney : 3200
         * totalPeople : 2
         * list : [{"uid":1000000,"type":2,"content":"10023","image":"[]","packImg":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","packName":"测试"},{"uid":1000000,"type":2,"content":"10023","image":"[]","packImg":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","packName":"测试"},{"uid":1000000,"type":3,"content":"10023","image":"[]","packImg":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","packName":"测试","statistics":{"viewNum":0,"praiseNum":0,"messageNum":0,"is_praise":false}}]
         */

        private String totalMoney;
        private int totalPeople;
        private List<ListBean> list;

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
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
             * uid : 1000000
             * type : 2
             * content : 10023
             * image : []
             * packImg : https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg
             * packName : 测试
             * statistics : {"viewNum":0,"praiseNum":0,"messageNum":0,"is_praise":false}
             */

            private int uid;
            private int type;
            private String content;
            private String image;
            private String packImg;
            private String packName;
            private StatisticsBean statistics;

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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
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

            public StatisticsBean getStatistics() {
                return statistics;
            }

            public void setStatistics(StatisticsBean statistics) {
                this.statistics = statistics;
            }

            public static class StatisticsBean {
                /**
                 * viewNum : 0
                 * praiseNum : 0
                 * messageNum : 0
                 * is_praise : false
                 */

                private int viewNum;
                private int praiseNum;
                private int messageNum;
                private boolean is_praise;

                public int getViewNum() {
                    return viewNum;
                }

                public void setViewNum(int viewNum) {
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
