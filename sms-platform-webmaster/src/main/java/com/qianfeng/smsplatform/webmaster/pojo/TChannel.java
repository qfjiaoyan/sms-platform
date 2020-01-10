package com.qianfeng.smsplatform.webmaster.pojo;

import java.io.Serializable;

public class TChannel implements Serializable {
    private Long id;

    private String channelname;

    private Byte channeltype;

    private String spnumber;

    private Byte protocaltype;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname == null ? null : channelname.trim();
    }

    public Byte getChanneltype() {
        return channeltype;
    }

    public void setChanneltype(Byte channeltype) {
        this.channeltype = channeltype;
    }

    public String getSpnumber() {
        return spnumber;
    }

    public void setSpnumber(String spnumber) {
        this.spnumber = spnumber == null ? null : spnumber.trim();
    }

    public Byte getProtocaltype() {
        return protocaltype;
    }

    public void setProtocaltype(Byte protocaltype) {
        this.protocaltype = protocaltype;
    }
}