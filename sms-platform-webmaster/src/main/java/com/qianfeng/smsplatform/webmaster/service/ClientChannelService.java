package com.qianfeng.smsplatform.webmaster.service;

import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TClientChannel;

import java.util.List;

public interface ClientChannelService {
    public int addClientChannel(TClientChannel tClientChannel);

    public int delClientChannel(Long id);

    public int updateClientChannel(TClientChannel tClientChannel);

    public TClientChannel findById(Long id);

    public List<TClientChannel> findAll();

    public DataGridResult findByPage(QueryDTO queryDTO);
}
