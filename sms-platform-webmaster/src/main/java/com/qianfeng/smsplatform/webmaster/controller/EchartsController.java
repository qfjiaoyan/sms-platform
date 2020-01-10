package com.qianfeng.smsplatform.webmaster.controller;
import com.qianfeng.smsplatform.webmaster.dto.SmsStatusDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TAdminUser;
import com.qianfeng.smsplatform.webmaster.service.api.SearchService;
import com.qianfeng.smsplatform.webmaster.util.JsonUtil;
import com.qianfeng.smsplatform.webmaster.util.R;
import com.qianfeng.smsplatform.webmaster.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EchartsController {

    @Autowired
    private SearchService searchService;

    @ResponseBody
    @RequestMapping("/sys/echarts/pie")
    public R findBie(SmsStatusDTO dto){
        TAdminUser userEntity = ShiroUtils.getUserEntity();
        Integer clientid = userEntity.getClientid();
        if(clientid!=0){//非管理员只能查自己
            dto.setClientID(clientid);
        }
        if(StringUtils.isEmpty(dto.getClientID())){//没有传公司设置为当前客户
            dto.setClientID(clientid);
        }
        String str = JsonUtil.getJSON(dto);
        List<String> legendData = new ArrayList<>();
        legendData.add("成功");
        legendData.add("状态未返回");
        legendData.add("失败");

        List<Map<String,Object>> seriesData = new ArrayList<>();
        Map<String, Long> stringLongMap = searchService.statSendStatus(str);
        Map<String,Object> map1 = new HashMap<>();
        Map<String,Object> map2 = new HashMap<>();
        Map<String,Object> map3 = new HashMap<>();
        Long aLong = stringLongMap.get("0");
        if(!StringUtils.isEmpty(aLong)){
            map1.put("name","成功");
            map1.put("value",aLong);
        }else{
            map1.put("name","成功");
            map1.put("value",0);
        }
        seriesData.add(map1);
        Long aLong1 = stringLongMap.get("1");
        if(!StringUtils.isEmpty(aLong1)){
            map2.put("name","状态未返回");
            map2.put("value",aLong1);
        }else{
            map2.put("name","状态未返回");
            map2.put("value",0);
        }
        seriesData.add(map2);
        Long aLong2 = stringLongMap.get("2");
        if(!StringUtils.isEmpty(aLong2)){
            map3.put("name","失败");
            map3.put("value",aLong2);
        }else{
            map3.put("name","失败");
            map3.put("value",0);
        }
        seriesData.add(map3);
        return R.ok().put("legendData",legendData).put("seriesData",seriesData);
    }


}
