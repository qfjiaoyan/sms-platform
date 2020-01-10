package com.qianfeng.smsplatform.webmaster.dao;

import com.qianfeng.smsplatform.webmaster.pojo.TPhase;
import com.qianfeng.smsplatform.webmaster.pojo.TPhaseExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TPhaseMapper {
    long countByExample(TPhaseExample example);

    int deleteByExample(TPhaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TPhase record);

    int insertSelective(TPhase record);

    List<TPhase> selectByExample(TPhaseExample example);

    TPhase selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TPhase record, @Param("example") TPhaseExample example);

    int updateByExample(@Param("record") TPhase record, @Param("example") TPhaseExample example);

    int updateByPrimaryKeySelective(TPhase record);

    int updateByPrimaryKey(TPhase record);
}