package com.qianfeng.smsplatform.webmaster.dao;

import com.qianfeng.smsplatform.webmaster.pojo.TAcountRecord;
import com.qianfeng.smsplatform.webmaster.pojo.TAcountRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAcountRecordMapper {
    long countByExample(TAcountRecordExample example);

    int deleteByExample(TAcountRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TAcountRecord record);

    int insertSelective(TAcountRecord record);

    List<TAcountRecord> selectByExample(TAcountRecordExample example);

    TAcountRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TAcountRecord record, @Param("example") TAcountRecordExample example);

    int updateByExample(@Param("record") TAcountRecord record, @Param("example") TAcountRecordExample example);

    int updateByPrimaryKeySelective(TAcountRecord record);

    int updateByPrimaryKey(TAcountRecord record);
}