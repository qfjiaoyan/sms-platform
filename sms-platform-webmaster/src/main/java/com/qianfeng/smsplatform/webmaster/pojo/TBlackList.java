package com.qianfeng.smsplatform.webmaster.pojo;

import java.io.Serializable;

public class TBlackList implements Serializable {
    private Long id;

    private String mobile;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }
}