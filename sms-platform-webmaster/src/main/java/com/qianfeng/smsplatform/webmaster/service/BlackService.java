package com.qianfeng.smsplatform.webmaster.service;

import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TBlackList;

import java.util.List;

public interface BlackService {
    public int addBlack(TBlackList tBlackList);

    public int delBlack(Long id);

    public int updateBlack(TBlackList tBlackList);

    public TBlackList findById(Long id);

    public List<TBlackList> findAll();

    public DataGridResult findByPage(QueryDTO queryDTO);
}
