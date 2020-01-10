package com.qianfeng.smsplatform.webmaster.dao;

import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TAdminUser;
import com.qianfeng.smsplatform.webmaster.pojo.TAdminUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAdminUserMapper {
    long countByExample(TAdminUserExample example);

    int deleteByExample(TAdminUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAdminUser record);

    int insertSelective(TAdminUser record);

    List<TAdminUser> selectByExample(TAdminUserExample example);

    TAdminUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAdminUser record, @Param("example") TAdminUserExample example);

    int updateByExample(@Param("record") TAdminUser record, @Param("example") TAdminUserExample example);

    int updateByPrimaryKeySelective(TAdminUser record);

    int updateByPrimaryKey(TAdminUser record);

    List<TAdminUser> findByPage(QueryDTO queryDTO);

    public TAdminUser findByUsername(String username);

}