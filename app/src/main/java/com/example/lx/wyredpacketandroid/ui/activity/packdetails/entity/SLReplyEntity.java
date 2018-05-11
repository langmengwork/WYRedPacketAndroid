package com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity;

import java.util.List;

public class SLReplyEntity {


    /**
     * err_code : 200
     * return_msg : success
     * data : {"returnCount":1,"info":{"id":1,"uid":1000000,"name":"测试","headimgurl":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","content":"测试留言","created_at":"6小时前"},"list":[{"id":2,"uid":1000000,"name":"测试","headimgurl":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","content":"测试留言回复","created_at":"6小时前"}]}
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
         * returnCount : 1
         * info : {"id":1,"uid":1000000,"name":"测试","headimgurl":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","content":"测试留言","created_at":"6小时前"}
         * list : [{"id":2,"uid":1000000,"name":"测试","headimgurl":"https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg","content":"测试留言回复","created_at":"6小时前"}]
         */

        private int returnCount;
        private InfoBean info;
        private List<ListBean> list;

        public int getReturnCount() {
            return returnCount;
        }

        public void setReturnCount(int returnCount) {
            this.returnCount = returnCount;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class InfoBean {
            /**
             * id : 1
             * uid : 1000000
             * name : 测试
             * headimgurl : https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg
             * content : 测试留言
             * created_at : 6小时前
             */

            private int id;
            private int uid;
            private String name;
            private String headimgurl;
            private String content;
            private String created_at;

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

        public static class ListBean {
            /**
             * id : 2
             * uid : 1000000
             * name : 测试
             * headimgurl : https://tongchenghongbao.yaxiangame.com/style/images/yuzhou.jpg
             * content : 测试留言回复
             * created_at : 6小时前
             */

            private int id;
            private int uid;
            private String name;
            private String headimgurl;
            private String content;
            private String created_at;

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
