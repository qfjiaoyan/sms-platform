package com.qianfeng.smsplatform.gateway.thread;

import com.qianfeng.smsplatform.common.model.Standard_Submit;
import com.qianfeng.smsplatform.gateway.mq.MTQueue;
import com.qianfeng.smsplatform.gateway.mq.ReceiveSmsFromMq;
import com.qianfeng.smsplatform.gateway.netty4.entity.CmppSubmitResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class SendSubmitRespThread implements Runnable{
    private final static Logger log = LoggerFactory.getLogger(SendSubmitRespThread.class);
    @Autowired
    private AmqpTemplate rabbitTemplate;

    private String topic;

    private MTQueue mtqueue = MTQueue.getInstance();

    public SendSubmitRespThread(String topic,AmqpTemplate rabbitTemplate){
        this.topic = topic;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (mtqueue.size() > 0) {
                    Standard_Submit resp = (Standard_Submit) mtqueue.remove();
                    rabbitTemplate.convertAndSend(topic, resp);
                } else {
                    sleep(2000);
                }
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
                sleep(2000);
            }
        }
    }

    private void sleep(long time){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
}
