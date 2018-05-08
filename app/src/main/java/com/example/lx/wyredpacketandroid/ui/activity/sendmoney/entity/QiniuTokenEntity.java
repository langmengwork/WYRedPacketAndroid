package com.example.lx.wyredpacketandroid.ui.activity.sendmoney.entity;

public class QiniuTokenEntity {


    /**
     * err_code : 200
     * return_msg : ok
     * data : {"token":"hR9D3MZXGvqAnAwdsm2tPaovMGg1FOat0eUrb40e:vJ7LGNl7gF0LW4h_FRNAAzUBWkM=:eyJzY29wZSI6InRvbmdjaGVuZ2hvbmdiYW8iLCJkZWFkbGluZSI6MTUyNTQyNDk5Nn0="}
     */

    private int err_code;
    private String return_msg;
    private DataBean data;

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
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
         * token : hR9D3MZXGvqAnAwdsm2tPaovMGg1FOat0eUrb40e:vJ7LGNl7gF0LW4h_FRNAAzUBWkM=:eyJzY29wZSI6InRvbmdjaGVuZ2hvbmdiYW8iLCJkZWFkbGluZSI6MTUyNTQyNDk5Nn0=
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
