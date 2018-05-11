package com.example.lx.wyredpacketandroid.entity;

import java.util.List;

public class OpenPackEntity {


    /**
     * err_code : 200
     * return_msg : success
     * data : {"packMoney":"0.00001元","gushu":1.0E-5,"is_owner":true,"praiseNum":20,"totalUserNum":1,"lastUserHeader":["https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg"],"packImg":"","packName":"","packArea":"一公里","content":"hahaha","image":["https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg"],"url":"","pack_id":"1","type":"2","messageCount":102,"messageInfo":[{"id":1,"uid":1000000,"name":"测试","headimgurl":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","content":"测试留言","created_at":"1天前","returnMessage":{"returnMessageCount":2,"id":2,"uid":1000000,"name":"测试","headimgurl":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","content":"测试留言","created_at":"1天前"}}]}
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
         * packMoney : 0.00001元
         * gushu : 1.0E-5
         * is_owner : true
         * praiseNum : 20
         * totalUserNum : 1
         * lastUserHeader : ["https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg"]
         * packImg :
         * packName :
         * packArea : 一公里
         * content : hahaha
         * image : ["https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg"]
         * url :
         * pack_id : 1
         * type : 2
         * messageCount : 102
         * messageInfo : [{"id":1,"uid":1000000,"name":"测试","headimgurl":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","content":"测试留言","created_at":"1天前","returnMessage":{"returnMessageCount":2,"id":2,"uid":1000000,"name":"测试","headimgurl":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","content":"测试留言","created_at":"1天前"}}]
         */

        private String packMoney;
        private String gushu;
        private boolean is_owner;
        private int praiseNum;
        private int totalUserNum;
        private String packImg;
        private String packName;
        private String packArea;
        private String content;
        private String url;
        private String pack_id;
        private String type;
        private int messageCount;
        private List<String> lastUserHeader;
        private List<String> image;
        private List<MessageInfoBean> messageInfo;

        public String getPackMoney() {
            return packMoney;
        }

        public void setPackMoney(String packMoney) {
            this.packMoney = packMoney;
        }

        public String getGushu() {
            return gushu;
        }

        public void setGushu(String gushu) {
            this.gushu = gushu;
        }

        public boolean isIs_owner() {
            return is_owner;
        }

        public void setIs_owner(boolean is_owner) {
            this.is_owner = is_owner;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getTotalUserNum() {
            return totalUserNum;
        }

        public void setTotalUserNum(int totalUserNum) {
            this.totalUserNum = totalUserNum;
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

        public String getPackArea() {
            return packArea;
        }

        public void setPackArea(String packArea) {
            this.packArea = packArea;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPack_id() {
            return pack_id;
        }

        public void setPack_id(String pack_id) {
            this.pack_id = pack_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getMessageCount() {
            return messageCount;
        }

        public void setMessageCount(int messageCount) {
            this.messageCount = messageCount;
        }

        public List<String> getLastUserHeader() {
            return lastUserHeader;
        }

        public void setLastUserHeader(List<String> lastUserHeader) {
            this.lastUserHeader = lastUserHeader;
        }

        public List<String> getImage() {
            return image;
        }

        public void setImage(List<String> image) {
            this.image = image;
        }

        public List<MessageInfoBean> getMessageInfo() {
            return messageInfo;
        }

        public void setMessageInfo(List<MessageInfoBean> messageInfo) {
            this.messageInfo = messageInfo;
        }

        public static class MessageInfoBean {
            /**
             * id : 1
             * uid : 1000000
             * name : 测试
             * headimgurl : https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg
             * content : 测试留言
             * created_at : 1天前
             * returnMessage : {"returnMessageCount":2,"id":2,"uid":1000000,"name":"测试","headimgurl":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","content":"测试留言","created_at":"1天前"}
             */

            private int id;
            private int uid;
            private String name;
            private String headimgurl;
            private String content;
            private String created_at;
            private ReturnMessageBean returnMessage;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
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

            public ReturnMessageBean getReturnMessage() {
                return returnMessage;
            }

            public void setReturnMessage(ReturnMessageBean returnMessage) {
                this.returnMessage = returnMessage;
            }

            public static class ReturnMessageBean {
                /**
                 * returnMessageCount : 2
                 * id : 2
                 * uid : 1000000
                 * name : 测试
                 * headimgurl : https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg
                 * content : 测试留言
                 * created_at : 1天前
                 */

                private int returnMessageCount;
                private int id;
                private int uid;
                private String name;
                private String headimgurl;
                private String content;
                private String created_at;

                public int getReturnMessageCount() {
                    return returnMessageCount;
                }

                public void setReturnMessageCount(int returnMessageCount) {
                    this.returnMessageCount = returnMessageCount;
                }

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

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getHeadimgurl() {
                    return headimgurl;
                }

                public void setHeadimgurl(String headimgurl) {
                    this.headimgurl = headimgurl;
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
            }
        }
    }
}
