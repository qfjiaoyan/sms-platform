package com.qianfeng.smsplatform.webmaster.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.smsplatform.webmaster.dao.TChannelMapper;
import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TChannel;
import com.qianfeng.smsplatform.webmaster.pojo.TChannelExample;
import com.qianfeng.smsplatform.webmaster.pojo.TPhase;
import com.qianfeng.smsplatform.webmaster.pojo.TPhaseExample;
import com.qianfeng.smsplatform.webmaster.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private TChannelMapper tChannelMapper;

    @Override
    public int addChannel(TChannel tChannel) {
        return tChannelMapper.insertSelective(tChannel);
    }

    @Override
    public int delChannel(Long id) {
        return tChannelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateChannel(TChannel tChannel) {
        return tChannelMapper.updateByPrimaryKey(tChannel);
    }

    @Override
    public TChannel findById(Long id) {
        return tChannelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TChannel> findALL() {
        return tChannelMapper.selectByExample(null);
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(), queryDTO.getLimit());
        TChannelExample example = new TChannelExample();
        String sort = queryDTO.getSort();
        if (!StringUtils.isEmpty(sort)) {
            example.setOrderByClause("id");
        }
        List<TChannel> tChannels = tChannelMapper.selectByExample(example);
        PageInfo<TChannel> info = new PageInfo<>(tChannels);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total, tChannels);
        return result;
    }

}
