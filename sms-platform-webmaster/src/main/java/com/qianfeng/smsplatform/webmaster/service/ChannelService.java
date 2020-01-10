package com.qianfeng.smsplatform.webmaster.service;

import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TChannel;

import java.util.List;

public interface ChannelService {
    public int addChannel(TChannel tChannel);

    public int delChannel(Long id);

    public int updateChannel(TChannel tChannel);

    public TChannel findById(Long id);

    public List<TChannel> findALL();

    public DataGridResult findByPage(QueryDTO queryDTO);
}
