package com.qianfeng.smsplatform.webmaster.dao;

import com.qianfeng.smsplatform.webmaster.pojo.TBlackList;
import com.qianfeng.smsplatform.webmaster.pojo.TBlackListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBlackListMapper {
    long countByExample(TBlackListExample example);

    int deleteByExample(TBlackListExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TBlackList record);

    int insertSelective(TBlackList record);

    List<TBlackList> selectByExample(TBlackListExample example);

    TBlackList selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TBlackList record, @Param("example") TBlackListExample example);

    int updateByExample(@Param("record") TBlackList record, @Param("example") TBlackListExample example);

    int updateByPrimaryKeySelective(TBlackList record);

    int updateByPrimaryKey(TBlackList record);
}