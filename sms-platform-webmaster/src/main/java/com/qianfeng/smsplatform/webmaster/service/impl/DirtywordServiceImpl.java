package com.qianfeng.smsplatform.webmaster.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.smsplatform.webmaster.dao.TDirtywordMapper;
import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TDirtyword;
import com.qianfeng.smsplatform.webmaster.pojo.TDirtywordExample;
import com.qianfeng.smsplatform.webmaster.service.DirtywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DirtywordServiceImpl implements DirtywordService {

    @Autowired
    private TDirtywordMapper tDirtywordMapper;


    @Override
    public int addDirtyword(TDirtyword tDirtyword) {
        return tDirtywordMapper.insertSelective(tDirtyword);
    }

    @Override
    public int delDirtyword(Long id) {
        TDirtyword tDirtyword = findById(id);
        return tDirtywordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateDirtyword(TDirtyword tDirtyword) {
        int i = tDirtywordMapper.updateByPrimaryKey(tDirtyword);
        return i;
    }

    @Override
    public TDirtyword findById(Long id) {
        return tDirtywordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TDirtyword> findAll() {
        return tDirtywordMapper.selectByExample(null);
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        TDirtywordExample example = new TDirtywordExample();
        String sort = queryDTO.getSort();
        if(!StringUtils.isEmpty(sort)){
            example.setOrderByClause("id");
        }
        List<TDirtyword> tDirtywords = tDirtywordMapper.selectByExample(example);
        PageInfo<TDirtyword> info = new PageInfo<>(tDirtywords);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total,tDirtywords);
        return result;
    }


}
