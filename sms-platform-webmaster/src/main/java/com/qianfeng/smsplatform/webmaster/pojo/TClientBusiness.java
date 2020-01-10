package com.qianfeng.smsplatform.webmaster.pojo;

import java.io.Serializable;

public class TClientBusiness implements Serializable {
    private Long id;

    private String corpname;

    private String usercode;

    private String pwd;

    private String ipaddress;

    private Byte isreturnstatus;

    private String receivestatusurl;

    private Byte priority;

    private Byte usertype;

    private Integer state;

    private String mobile;

    private String paidValueStr;//扩展实时费用字段

    public String getPaidValueStr() {
        return paidValueStr;
    }

    public void setPaidValueStr(String paidValueStr) {
        this.paidValueStr = paidValueStr;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname == null ? null : corpname.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress == null ? null : ipaddress.trim();
    }

    public Byte getIsreturnstatus() {
        return isreturnstatus;
    }

    public void setIsreturnstatus(Byte isreturnstatus) {
        this.isreturnstatus = isreturnstatus;
    }

    public String getReceivestatusurl() {
        return receivestatusurl;
    }

    public void setReceivestatusurl(String receivestatusurl) {
        this.receivestatusurl = receivestatusurl == null ? null : receivestatusurl.trim();
    }

    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public Byte getUsertype() {
        return usertype;
    }

    public void setUsertype(Byte usertype) {
        this.usertype = usertype;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }
}