package com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity;

import java.util.List;

public class MessageListEntity {

    /**
     * err_code : 200
     * return_msg : success
     * data : [{"id":1,"uid":1000000,"name":"测试","headimgurl":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","content":"测试留言","created_at":"1天前","returnMessage":{"returnMessageCount":2,"id":2,"uid":1000000,"name":"测试","headimgurl":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","content":"测试留言","created_at":"1天前"}}]
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
