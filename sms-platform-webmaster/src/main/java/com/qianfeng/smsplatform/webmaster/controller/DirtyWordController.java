package com.qianfeng.smsplatform.webmaster.controller;

import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TDirtyword;
import com.qianfeng.smsplatform.webmaster.service.DirtywordService;
import com.qianfeng.smsplatform.webmaster.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DirtyWordController {

    @Autowired
    private DirtywordService dirtywordService;

    @ResponseBody
    @RequestMapping("/sys/message/list")
    public DataGridResult findDirtyword(QueryDTO queryDTO){
        return  dirtywordService.findByPage(queryDTO);
    }

    @ResponseBody
    @RequestMapping("/sys/message/del")
    public R delDirtyword(@RequestBody List<Long> ids){
        for (Long id : ids) {
            dirtywordService.delDirtyword(id);
        }
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/sys/message/info/{id}")
    public R findById(@PathVariable("id") Long id){
        TDirtyword tDirtyword = dirtywordService.findById(id);
        return R.ok().put("message",tDirtyword);
    }

    @ResponseBody
    @RequestMapping("/sys/message/save")
    public R addDirtyword(@RequestBody TDirtyword tDirtyword){
        int i = dirtywordService.addDirtyword(tDirtyword);
        return i>0?R.ok():R.error("添加失败");
    }

    @ResponseBody
    @RequestMapping("/sys/message/update")
    public R updateDirtyword(@RequestBody TDirtyword tDirtyword){
        int i = dirtywordService.updateDirtyword(tDirtyword);
        return i>0?R.ok():R.error("修改失败");
    }

}
