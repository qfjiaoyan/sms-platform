package com.qianfeng.smsplatform.webmaster.pojo;

import java.io.Serializable;

public class TDirtyword implements Serializable {
    private Long id;

    private String dirtyword;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDirtyword() {
        return dirtyword;
    }

    public void setDirtyword(String dirtyword) {
        this.dirtyword = dirtyword == null ? null : dirtyword.trim();
    }
}