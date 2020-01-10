package com.qianfeng.smsplatform.webmaster.dao;

import com.qianfeng.smsplatform.webmaster.pojo.TRoleMenuExample;
import com.qianfeng.smsplatform.webmaster.pojo.TRoleMenuKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TRoleMenuMapper {
    long countByExample(TRoleMenuExample example);

    int deleteByExample(TRoleMenuExample example);

    int deleteByPrimaryKey(TRoleMenuKey key);

    int insert(TRoleMenuKey record);

    int insertSelective(TRoleMenuKey record);

    List<TRoleMenuKey> selectByExample(TRoleMenuExample example);

    int updateByExampleSelective(@Param("record") TRoleMenuKey record, @Param("example") TRoleMenuExample example);

    int updateByExample(@Param("record") TRoleMenuKey record, @Param("example") TRoleMenuExample example);
}