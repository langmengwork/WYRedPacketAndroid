package com.example.lx.wyredpacketandroid.utils.greendaoform;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserToken {

    @Id
    private Long id;
    @Property(nameInDb = "openid")
    private String openid;
    @Property(nameInDb = "access_token")
    private String access_token;
    @Property(nameInDb = "refresh_token")
    private String refresh_token;
    @Generated(hash = 5727389)
    public UserToken(Long id, String openid, String access_token,
            String refresh_token) {
        this.id = id;
        this.openid = openid;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }
    @Generated(hash = 2113443620)
    public UserToken() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOpenid() {
        return this.openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getAccess_token() {
        return this.access_token;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    public String getRefresh_token() {
        return this.refresh_token;
    }
    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
    
}
