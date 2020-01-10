package com.qianfeng.smsplatform.webmaster.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.smsplatform.webmaster.dao.TInstMapper;
import com.qianfeng.smsplatform.webmaster.dao.TPhaseMapper;
import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TInst;
import com.qianfeng.smsplatform.webmaster.pojo.TInstExample;
import com.qianfeng.smsplatform.webmaster.pojo.TPhase;
import com.qianfeng.smsplatform.webmaster.pojo.TPhaseExample;
import com.qianfeng.smsplatform.webmaster.service.PhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PhaseServiceImpl implements PhaseService {

    @Autowired
    private TPhaseMapper tPhaseMapper;

    @Autowired
    private TInstMapper tInstMapper;


    @Override
    public int addPhase(TPhase tPhase) {
        return tPhaseMapper.insertSelective(tPhase);
    }

    @Override
    public int delPhase(Long id) {
        TPhase tPhase = findById(id);
        return tPhaseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updatePhase(TPhase tPhase) {
        int i= tPhaseMapper.updateByPrimaryKey(tPhase);
        return i;
    }

    @Override
    public TPhase findById(Long id) {
        return tPhaseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TPhase> findALL() {
        return tPhaseMapper.selectByExample(null);
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(), queryDTO.getLimit());
        TPhaseExample example = new TPhaseExample();
        String sort = queryDTO.getSort();
        if (!StringUtils.isEmpty(sort)) {
            example.setOrderByClause("id");
        }
        List<TPhase> messages = tPhaseMapper.selectByExample(example);
        for (TPhase message : messages) {
            Long provId = message.getProvId();
            TInst tInst1 = tInstMapper.selectByPrimaryKey(provId);
            message.setProvName(tInst1.getAreaname());
            Long cityId = message.getCityId();
            TInstExample tInstExample = new TInstExample();
            TInstExample.Criteria criteria = tInstExample.createCriteria();
            criteria.andIdEqualTo(cityId);
            criteria.andParentidEqualTo(provId);
            List<TInst> tInsts = tInstMapper.selectByExample(tInstExample);
            if(tInsts!=null&&tInsts.size()>0){
                TInst tInst = tInsts.get(0);
                message.setCityName(tInst.getAreaname());
            }
        }

        PageInfo<TPhase> info = new PageInfo<>(messages);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total, messages);
        return result;
    }
}
