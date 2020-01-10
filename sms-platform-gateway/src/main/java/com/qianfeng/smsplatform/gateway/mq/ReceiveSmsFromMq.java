package com.qianfeng.smsplatform.gateway.mq;

import com.qianfeng.smsplatform.common.model.Standard_Report;
import com.qianfeng.smsplatform.common.model.Standard_Submit;
import com.qianfeng.smsplatform.gateway.netty4.NettyClient;
import com.qianfeng.smsplatform.gateway.netty4.Utils.Command;
import com.qianfeng.smsplatform.gateway.netty4.Utils.MsgUtils;
import com.qianfeng.smsplatform.gateway.netty4.entity.CmppMessageHeader;
import com.qianfeng.smsplatform.gateway.netty4.entity.CmppSubmit;
import com.qianfeng.smsplatform.gateway.netty4.entity.ControlWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.qianfeng.smsplatform.common.constants.RabbitMqConsants.TOPIC_PRE_SEND;
import static com.qianfeng.smsplatform.common.constants.RabbitMqConsants.TOPIC_UPDATE_SMS_REPORT;
import static com.qianfeng.smsplatform.gateway.config.RabbitConfig.QUEUE_DELAY_PER_MESSAGE_TTL_MSG_SMS_SEND;

@Component
public class ReceiveSmsFromMq {
    private final static Logger log = LoggerFactory.getLogger(ReceiveSmsFromMq.class);

    @Autowired
    private NettyClient nettyClient;
    @Value("${gateway.longcode}")
    private String longcode;
//    @Value("${gateway.sendtopic}")
//    private String sendtopic;
    /**
     * 消息接受  并发消费
     */
    @RabbitListener(queues = "${gateway.sendtopic}", containerFactory = "pointTaskContainerFactory")
    public void receive(Standard_Submit submit) throws IOException, InterruptedException {
        Thread.sleep(10);
        log.info("接收消息:{}" + submit);
        String mobile=submit.getDestMobile();
        String content=submit.getMessageContent();
        int sequenceId =  MsgUtils.getSequence();
        log.info("sequenceId="+ sequenceId);
        String srcId = longcode + submit.getSrcNumber();
        submit.setSrcNumber(srcId);
        CmppMessageHeader submitMessage=new CmppSubmit(Command.CMPP2_VERSION, "", srcId, sequenceId, mobile, content);
        ControlWindow controlWindow = ControlWindow.getInstance();
        log.info("enter window seqID:{},submit:{}",sequenceId,submit);
        controlWindow.put(sequenceId,submit);
        nettyClient.submit(submitMessage);
    }

    /**
     * 消息接受  并发消费 TOPIC_UPDATE_SMS_REPORT  QUEUE_DELAY_PER_MESSAGE_TTL_MSG_SMS_SEND
     */
//    @RabbitListener(queues = TOPIC_UPDATE_SMS_REPORT, containerFactory = "pointTaskContainerFactory")
//    public void receive(Standard_Report report) throws Exception {
//        log.info("接收延迟消息:{}" , report);
//    }
}
