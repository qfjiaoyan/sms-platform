package com.qianfeng.smsplatform.webmaster.service.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "SEARCH-SERVICE", fallback = SearchServiceFallback.class)
public interface SearchService {
    @RequestMapping(value = "/search/search", method = RequestMethod.POST)
    List<Map> searchLog(@RequestParam("paras") String paras);

    @RequestMapping(value = "/search/searchcount", method = RequestMethod.POST)
    Long searchLogCount(@RequestParam("paras") String paras);

    @RequestMapping(value = "/search/statStatus", method = RequestMethod.POST)
    public Map<String, Long> statSendStatus(@RequestParam("paras") String paras);

}
