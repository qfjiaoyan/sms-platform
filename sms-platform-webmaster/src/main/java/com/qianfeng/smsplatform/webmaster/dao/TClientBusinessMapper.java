package com.qianfeng.smsplatform.webmaster.dao;

import com.qianfeng.smsplatform.webmaster.pojo.TClientBusiness;
import com.qianfeng.smsplatform.webmaster.pojo.TClientBusinessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TClientBusinessMapper {
    long countByExample(TClientBusinessExample example);

    int deleteByExample(TClientBusinessExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TClientBusiness record);

    int insertSelective(TClientBusiness record);

    List<TClientBusiness> selectByExample(TClientBusinessExample example);

    TClientBusiness selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TClientBusiness record, @Param("example") TClientBusinessExample example);

    int updateByExample(@Param("record") TClientBusiness record, @Param("example") TClientBusinessExample example);

    int updateByPrimaryKeySelective(TClientBusiness record);

    int updateByPrimaryKey(TClientBusiness record);
}