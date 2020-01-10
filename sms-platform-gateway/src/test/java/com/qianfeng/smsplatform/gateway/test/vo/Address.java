package com.qianfeng.smsplatform.gateway.test.vo;

import java.io.Serializable;

/**
 * Created by zhangjianbin on 2017/1/4.
 */
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" + "address='" + address + '\'' + '}';
    }
}
