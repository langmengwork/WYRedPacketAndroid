package com.example.lx.wyredpacketandroid.entity;

public class LoginEntity {


    /**
     * err_code : 200
     * return_msg : success
     * data : {"openid":"oxP1P1tbpLoGQXwYHm2RL34MLdgY","access_token":"9_Lyg5HsCNBKq4-VIarftkGBV087FMF3LNinuxrTGtKwoEE3woc4hgg-Ilv5HKMkcMeaWvodRB-R7fcSH3TcDdX9NUEyWac6Kl3nyPfn7v4SE","expires_in":7200,"refresh_token":"9_vtmBP_1YTIt1NCLRa-0dLWnFsh0YalCfdBierCQKzJYBPDkSf5O75AABNVePBA2WedNQQlbpVrHI3HKgdtEQMpPDt-eA4YdynvM2ZslgseU","scope":"snsapi_base,snsapi_userinfo,"}
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
         * openid : oxP1P1tbpLoGQXwYHm2RL34MLdgY
         * access_token : 9_Lyg5HsCNBKq4-VIarftkGBV087FMF3LNinuxrTGtKwoEE3woc4hgg-Ilv5HKMkcMeaWvodRB-R7fcSH3TcDdX9NUEyWac6Kl3nyPfn7v4SE
         * expires_in : 7200
         * refresh_token : 9_vtmBP_1YTIt1NCLRa-0dLWnFsh0YalCfdBierCQKzJYBPDkSf5O75AABNVePBA2WedNQQlbpVrHI3HKgdtEQMpPDt-eA4YdynvM2ZslgseU
         * scope : snsapi_base,snsapi_userinfo,
         */

        private String openid;
        private String access_token;
        private int expires_in;
        private String refresh_token;
        private String scope;

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }
    }
}
