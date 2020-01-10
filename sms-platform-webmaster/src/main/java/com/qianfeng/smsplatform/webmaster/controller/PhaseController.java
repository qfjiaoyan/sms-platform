package com.qianfeng.smsplatform.webmaster.controller;

import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TPhase;
import com.qianfeng.smsplatform.webmaster.service.PhaseService;
import com.qianfeng.smsplatform.webmaster.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PhaseController {

    @Autowired
    private PhaseService phaseService;

    @ResponseBody
    @RequestMapping("/sys/phase/list")
    public DataGridResult findPhase(QueryDTO queryDTO) {
        return phaseService.findByPage(queryDTO);
    }

    @ResponseBody
    @RequestMapping("/sys/phase/del")
    public R delPhase(@RequestBody List<Long> ids) {
        for (Long id : ids) {
            phaseService.delPhase(id);
        }
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/sys/phase/info/{id}")
    public R findById(@PathVariable("id") Long id) {
        TPhase tPhase = phaseService.findById(id);
        return R.ok().put("phase", tPhase);
    }

    @ResponseBody
    @RequestMapping("/sys/phase/save")
    public R addPhase(@RequestBody TPhase tPhase) {
        int i = phaseService.addPhase(tPhase);
        return i > 0 ? R.ok() : R.error("添加失败");
    }

    @ResponseBody
    @RequestMapping("/sys/phase/update")
    public R updatePhase(@RequestBody TPhase tPhase) {
        int i = phaseService.updatePhase(tPhase);
        return i > 0 ? R.ok() : R.error("修改失败");
    }

}
