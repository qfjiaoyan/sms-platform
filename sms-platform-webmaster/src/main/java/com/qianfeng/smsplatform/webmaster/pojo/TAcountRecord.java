package com.qianfeng.smsplatform.webmaster.pojo;

import java.io.Serializable;
import java.util.Date;

public class TAcountRecord implements Serializable {
    private Long id;

    private Long clientid;
    private String corpname;

    private Integer paidvalue;

    private Date createtime;

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientid() {
        return clientid;
    }

    public void setClientid(Long clientid) {
        this.clientid = clientid;
    }

    public Integer getPaidvalue() {
        return paidvalue;
    }

    public void setPaidvalue(Integer paidvalue) {
        this.paidvalue = paidvalue;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}