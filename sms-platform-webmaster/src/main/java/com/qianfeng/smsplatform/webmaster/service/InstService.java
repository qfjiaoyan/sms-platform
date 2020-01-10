package com.qianfeng.smsplatform.webmaster.service;

import com.qianfeng.smsplatform.webmaster.pojo.TInst;

import java.util.List;

public interface InstService {
    //查询所有的省
    public List<TInst> findProvs();
    //查询省对应的市
    public List<TInst> findCitys(Long provId);

}
