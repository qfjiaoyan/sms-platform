package com.qianfeng.smsplatform.webmaster.service.api;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author menglili
 * 搜索服务的熔断
 */
@Component
public class SearchServiceFallback implements SearchService {
    @Override
    public List<Map> searchLog(String paras) {
        return null;
    }

    @Override
    public Long searchLogCount(String paras) {
        return null;
    }

    @Override
    public Map<String, Long> statSendStatus(String paras) {
        return null;
    }
}
