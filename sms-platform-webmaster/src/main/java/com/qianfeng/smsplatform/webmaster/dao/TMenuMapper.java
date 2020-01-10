package com.qianfeng.smsplatform.webmaster.dao;

import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TMenu;
import com.qianfeng.smsplatform.webmaster.pojo.TMenuExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TMenuMapper {
    long countByExample(TMenuExample example);

    int deleteByExample(TMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TMenu record);

    int insertSelective(TMenu record);

    List<TMenu> selectByExample(TMenuExample example);

    TMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TMenu record, @Param("example") TMenuExample example);

    int updateByExample(@Param("record") TMenu record, @Param("example") TMenuExample example);

    int updateByPrimaryKeySelective(TMenu record);

    int updateByPrimaryKey(TMenu record);

    int deleteMenu(List<Long> ids);

    List<TMenu> findMenuByPage(QueryDTO query);

    List<TMenu> findMenu();

    List<String> findPermsByUserId(@Param("userId") Integer userId);

    List<Map<String, Object>> findDirMenuByUserId(@Param("userId") Integer userId);

    List<Map<String, Object>> findMenuNotButtonByUserId(@Param("userId") Integer userId, @Param("parentId") Integer parentId);

}