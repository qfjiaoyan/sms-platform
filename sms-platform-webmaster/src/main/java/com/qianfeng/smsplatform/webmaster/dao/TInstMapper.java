package com.qianfeng.smsplatform.webmaster.dao;

import com.qianfeng.smsplatform.webmaster.pojo.TInst;
import com.qianfeng.smsplatform.webmaster.pojo.TInstExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TInstMapper {
    long countByExample(TInstExample example);

    int deleteByExample(TInstExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TInst record);

    int insertSelective(TInst record);

    List<TInst> selectByExample(TInstExample example);

    TInst selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TInst record, @Param("example") TInstExample example);

    int updateByExample(@Param("record") TInst record, @Param("example") TInstExample example);

    int updateByPrimaryKeySelective(TInst record);

    int updateByPrimaryKey(TInst record);
}