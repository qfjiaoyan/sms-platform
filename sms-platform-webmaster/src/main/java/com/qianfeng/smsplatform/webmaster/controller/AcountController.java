package com.qianfeng.smsplatform.webmaster.controller;

import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TAcountRecord;
import com.qianfeng.smsplatform.webmaster.service.AcountRecordService;
import com.qianfeng.smsplatform.webmaster.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AcountController {

    @Autowired
    private AcountRecordService acountRecordService;

    @ResponseBody
    @RequestMapping("/sys/acount/list")
    public DataGridResult findAcount(QueryDTO queryDTO){
        return  acountRecordService.findByPage(queryDTO);
    }

    @ResponseBody
    @RequestMapping("/sys/acount/del")
    public R delAcount(@RequestBody List<Long> ids){
        for (Long id : ids) {
            acountRecordService.delAcount(id);
        }
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/sys/acount/info/{id}")
    public R findById(@PathVariable("id") Long id){
        TAcountRecord tAcountRecord = acountRecordService.findById(id);
        return R.ok().put("acount",tAcountRecord);
    }

    @ResponseBody
    @RequestMapping("/sys/acount/save")
    public R addAcount(@RequestBody TAcountRecord tAcountRecord){
        int i = acountRecordService.addAcount(tAcountRecord);
        return i>0?R.ok():R.error("添加失败");
    }

    @ResponseBody
    @RequestMapping("/sys/acount/update")
    public R updateAcount(@RequestBody TAcountRecord tAcountRecord){
        int i = acountRecordService.updateAcount(tAcountRecord);
        return i>0?R.ok():R.error("修改失败");
    }


}
