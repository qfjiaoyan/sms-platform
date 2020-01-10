package com.qianfeng.smsplatform.webmaster.service;

import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TClientBusiness;

import java.util.List;

public interface ClientBusinessService {
    public int addClientBusiness(TClientBusiness tClientBusiness);

    public int delClientBusiness(Long id);

    public int updateClientBusiness(TClientBusiness tClientBusiness);

    public TClientBusiness findById(Long id);

    public List<TClientBusiness> findAll();

    public DataGridResult findByPage(QueryDTO queryDTO);
}
