package com.qianfeng.smsplatform.gateway.test.vo;

import java.io.Serializable;

/**
 * Created by zhangjianbin on 2017/1/4.
 */
public class UserVo implements Serializable {
    private static final long serialVersionUID = 2L;

    private String name;

    private Integer age;

    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "UserVo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}


