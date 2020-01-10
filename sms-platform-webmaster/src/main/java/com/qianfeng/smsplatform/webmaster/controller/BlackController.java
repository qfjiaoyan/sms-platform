package com.qianfeng.smsplatform.webmaster.controller;

import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TBlackList;
import com.qianfeng.smsplatform.webmaster.service.BlackService;
import com.qianfeng.smsplatform.webmaster.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BlackController {

    @Autowired
    private BlackService blackService;

    @ResponseBody
    @RequestMapping("/sys/black/list")
    public DataGridResult findBlack(QueryDTO queryDTO){
        return  blackService.findByPage(queryDTO);
    }

    @ResponseBody
    @RequestMapping("/sys/black/del")
    public R delBlack(@RequestBody List<Long> ids){
        for (Long id : ids) {
            blackService.delBlack(id);
        }
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/sys/black/info/{id}")
    public R findById(@PathVariable("id") Long id){
        TBlackList tBlackList = blackService.findById(id);
        return R.ok().put("black",tBlackList);
    }

    @ResponseBody
    @RequestMapping("/sys/black/save")
    public R addBlack(@RequestBody TBlackList tBlackList){
        int i = blackService.addBlack(tBlackList);
        return i>0?R.ok():R.error("添加失败");
    }

    @ResponseBody
    @RequestMapping("/sys/black/update")
    public R updateBlack(@RequestBody TBlackList tBlackList){
        int i = blackService.updateBlack(tBlackList);
        return i>0?R.ok():R.error("修改失败");
    }

}
