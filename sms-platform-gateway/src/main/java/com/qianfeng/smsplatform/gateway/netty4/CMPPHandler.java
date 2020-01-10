package com.qianfeng.smsplatform.gateway.netty4;

import com.qianfeng.smsplatform.common.model.Standard_Submit;
import com.qianfeng.smsplatform.gateway.mq.MTQueue;
import com.qianfeng.smsplatform.gateway.mq.StatQueue;
import com.qianfeng.smsplatform.gateway.netty4.Utils.MsgUtils;
import com.qianfeng.smsplatform.gateway.netty4.entity.CmppDeliver;
import com.qianfeng.smsplatform.gateway.netty4.entity.CmppSubmitResp;
import com.qianfeng.smsplatform.gateway.netty4.entity.ControlWindow;
import com.qianfeng.smsplatform.gateway.netty4.entity.ReportMatch;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 主要业务 handler
 * @author weixiang
 * @date 2018/8/2 15:37
 */

public class CMPPHandler extends SimpleChannelInboundHandler {
    private final static Logger log = LoggerFactory.getLogger(CMPPHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof CmppSubmitResp){
            CmppSubmitResp resp=(CmppSubmitResp)msg;
            log.info("-------------接收到短信提交应答-------------");
            log.info("----自增id："+resp.getMsgId());
            log.info("----状态："+ resp.getState());
            log.info("----第一次响应："+resp.getMsgId2());
            ControlWindow controlWindow = ControlWindow.getInstance();
            Standard_Submit submit = (Standard_Submit)controlWindow.remove(resp.getMsgId());
            log.info("从滑动窗体中取 msgid:{},submit:{}",resp.getMsgId(),submit);
            ReportMatch reportMatch = ReportMatch.getInstance();
            log.info("把 msgid:{},submit:{}，放入reportMatch",resp.getMsgId(),submit);
            reportMatch.put(resp.getMsgId2(),submit);
            MTQueue mtQueue = MTQueue.getInstance();
            submit.setMsgid(String.valueOf(resp.getMsgId2()));
            //未返回状态
            submit.setReportState(1);
            mtQueue.add(submit);
        }

        if (msg instanceof CmppDeliver){
            CmppDeliver resp=(CmppDeliver)msg;
            // 是否为状态报告 0：非状态报告1：状态报告
            if (resp.getRegistered_Delivery() == 1) {
                // 如果是状态报告的话
                log.info("-------------状态报告---------------");
                log.info("----第二次响应："+resp.getMsg_Id_DELIVRD());
                log.info("----手机号："+resp.getDest_terminal_Id());
                log.info("----状态："+resp.getStat());
                StatQueue statQueue = StatQueue.getInstance();
                statQueue.add(msg);
            } else {
                //用户回复会打印在这里
                log.info(""+ MsgUtils.bytesToLong(resp.getMsg_Id()));
                log.info(resp.getSrc_terminal_Id());
                log.info(resp.getMsg_Content());
            }
        }
    }

}
