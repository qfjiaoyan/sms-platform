package com.qianfeng.smsplatform.webmaster.controller;

import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TChannel;
import com.qianfeng.smsplatform.webmaster.service.ChannelService;
import com.qianfeng.smsplatform.webmaster.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @ResponseBody
    @RequestMapping("/sys/channel/list")
    public DataGridResult findPhase(QueryDTO queryDTO) {
        return channelService.findByPage(queryDTO);
    }

    @ResponseBody
    @RequestMapping("/sys/channel/del")
    public R delChannel(@RequestBody List<Long> ids) {
        for (Long id : ids) {
            channelService.delChannel(id);
        }
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/sys/channel/info/{id}")
    public R findById(@PathVariable("id") Long id) {
        TChannel tChannel = channelService.findById(id);
        return R.ok().put("channel", tChannel);
    }

    @ResponseBody
    @RequestMapping("/sys/channel/all")
    public R findAll() {
        List<TChannel> all = channelService.findALL();
        return R.ok().put("channelsites", all);
    }

    @ResponseBody
    @RequestMapping("/sys/channel/save")
    public R addChannel(@RequestBody TChannel tChannel) {
        int i = channelService.addChannel(tChannel);
        return i > 0 ? R.ok() : R.error("添加失败");
    }

    @ResponseBody
    @RequestMapping("/sys/channel/update")
    public R updateChannel(@RequestBody TChannel tChannel) {
        int i = channelService.updateChannel(tChannel);
        return i > 0 ? R.ok() : R.error("修改失败");
    }

}
