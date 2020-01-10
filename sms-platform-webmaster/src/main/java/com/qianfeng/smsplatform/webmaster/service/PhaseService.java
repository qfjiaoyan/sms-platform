package com.qianfeng.smsplatform.webmaster.service;

import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TPhase;

import java.util.List;

public interface PhaseService {
    public int addPhase(TPhase tPhase);
    public int delPhase(Long id);
    public int updatePhase(TPhase tPhase);
    public TPhase findById(Long id);
    public List<TPhase> findALL();

    public DataGridResult findByPage(QueryDTO queryDTO);


}
