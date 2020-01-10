package com.qianfeng.smsplatform.webmaster.pojo;

import java.io.Serializable;

public class TUserRoleKey implements Serializable {
    private Integer userId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}