//package com.qianfeng.smsplatform.webmaster.controller;
//
//import com.qianfeng.smsplatform.webmaster.pojo.*;
//import com.qianfeng.smsplatform.webmaster.service.*;
//import com.qianfeng.smsplatform.webmaster.util.JsonUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class InitController {
//
//    @Autowired
//    private CacheService cacheService;
//
//    @Autowired
//    private DirtywordService dirtywordService;
//
//    @Autowired
//    private BlackService blackService;
//
//    @Autowired
//    private ClientChannelService clientChannelService;
//
//    @Autowired
//    private ChannelService channelService;
//
//    @Autowired
//    private PhaseService phaseService;
//
//    @Autowired
//    private ClientBusinessService clientBusinessService;
//
//
//    @RequestMapping("/initsms")
//    public Map<String ,Object> initsms(){
//        Map<String ,Object> map = new HashMap<>();
//        List<TDirtyword> all = dirtywordService.findAll();
//        //初始化敏感词
//        for (TDirtyword tDirtyword : all) {
//            cacheService.saveCache("DIRTYWORDS:"+tDirtyword.getDirtyword(),"1");
//        }
//        List<TBlackList> tBlackLists = blackService.findAll();
//        //初始化黑名单
//        for (TBlackList tBlackList : tBlackLists) {
//            cacheService.saveCache("BLACK:"+tBlackList.getMobile(),"1");
//        }
//        //初始化客户路由数据
//        List<TClientChannel> tClientChannels = clientChannelService.findAll();
//        for (TClientChannel tClientChannel : tClientChannels) {
//            Map<String, String> stringObjectMap = JsonUtils.objectToMap(tClientChannel);
//            cacheService.hmset("ROUTER:"+tClientChannel.getClientid(),stringObjectMap);
//        }
//
//        List<TPhase> tPhases = phaseService.findALL();
//        //初始化号段数据
//        for (TPhase tPhase : tPhases) {
//            cacheService.saveCache("PHASE:"+tPhase.getPhase(),tPhase.getProvId()+"&"+tPhase.getCityId());
//        }
//
//        List<TClientBusiness> tClientBusinesses = clientBusinessService.findAll();
//        for (TClientBusiness tClient : tClientBusinesses) {
//            Map<String, String> map1 = JsonUtils.objectToMap(tClient);
//            cacheService.hmset("CLIENT:"+tClient.getId(),map1);
//        }
//
//        map.put("msg","ok");
//        return map;
//    }
//
//
//}
