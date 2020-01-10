package com.qianfeng.smsplatform.webmaster.controller;

import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TClientChannel;
import com.qianfeng.smsplatform.webmaster.service.ClientChannelService;
import com.qianfeng.smsplatform.webmaster.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ClientChannelController {

    @Autowired
    private ClientChannelService clientChannelService;

    @ResponseBody
    @RequestMapping("/sys/clientchannel/list")
    public DataGridResult findClientChannel(QueryDTO queryDTO) {
        return clientChannelService.findByPage(queryDTO);
    }

    @ResponseBody
    @RequestMapping("/sys/clientchannel/del")
    public R delClientChannel(@RequestBody List<Long> ids) {
        for (Long id : ids) {
            clientChannelService.delClientChannel(id);
        }
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/sys/clientchannel/info/{id}")
    public R findById(@PathVariable("id") Long id) {
        TClientChannel tClientChannel = clientChannelService.findById(id);
        return R.ok().put("clientchannel", tClientChannel);
    }

    @ResponseBody
    @RequestMapping("/sys/clientchannel/save")
    public R addClientChannel(@RequestBody TClientChannel tClientChannel) {
        int i = clientChannelService.addClientChannel(tClientChannel);
        return i > 0 ? R.ok() : R.error("添加失败");
    }

    @ResponseBody
    @RequestMapping("/sys/clientchannel/update")
    public R updateClientChannel(@RequestBody TClientChannel tClientChannel) {
        int i = clientChannelService.updateClientChannel(tClientChannel);
        return i > 0 ? R.ok() : R.error("修改失败");
    }

}
