package com.qianfeng.smsplatform.webmaster.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.smsplatform.webmaster.dao.TChannelMapper;
import com.qianfeng.smsplatform.webmaster.dao.TClientBusinessMapper;
import com.qianfeng.smsplatform.webmaster.dao.TClientChannelMapper;
import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TChannel;
import com.qianfeng.smsplatform.webmaster.pojo.TClientBusiness;
import com.qianfeng.smsplatform.webmaster.pojo.TClientChannel;
import com.qianfeng.smsplatform.webmaster.pojo.TClientChannelExample;
import com.qianfeng.smsplatform.webmaster.service.ClientChannelService;
import com.qianfeng.smsplatform.webmaster.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class ClientChannelServiceImpl implements ClientChannelService {

    @Autowired
    private TClientChannelMapper tClientChannelMapper;
    @Autowired
    private TClientBusinessMapper tClientBusinessMapper;

    @Autowired
    private TChannelMapper tChannelMapper;


    @Override
    public int addClientChannel(TClientChannel tClientChannel) {
        int i =  tClientChannelMapper.insertSelective(tClientChannel);
        Map<String, String> stringObjectMap = JsonUtils.objectToMap(tClientChannel);

        return i;
    }

    @Override
    public int delClientChannel(Long id) {
        TClientChannel tClientChannel = findById(id);

        return tClientChannelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateClientChannel(TClientChannel tClientChannel) {
        int i =  tClientChannelMapper.updateByPrimaryKey(tClientChannel);
        return i;
    }

    @Override
    public TClientChannel findById(Long id) {
        return tClientChannelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TClientChannel> findAll() {
        return tClientChannelMapper.selectByExample(null);
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(), queryDTO.getLimit());
        TClientChannelExample example = new TClientChannelExample();
        String sort = queryDTO.getSort();
        if (!StringUtils.isEmpty(sort)) {
            example.setOrderByClause("id");
        }
        List<TClientChannel> tClientChannels = tClientChannelMapper.selectByExample(example);
        for (TClientChannel tClientChannel : tClientChannels) {
            Long clientid = tClientChannel.getClientid();
            TClientBusiness tClientBusiness = tClientBusinessMapper.selectByPrimaryKey(clientid);
            String corpname = tClientBusiness.getCorpname();
            tClientChannel.setCorpname(corpname);
            long channelid = (long)tClientChannel.getChannelid();
            TChannel tChannel = tChannelMapper.selectByPrimaryKey(channelid);
            String channelname = tChannel.getChannelname();
            tClientChannel.setChannelname(channelname);
        }
        PageInfo<TClientChannel> info = new PageInfo<>(tClientChannels);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total, tClientChannels);
        return result;
    }
}
