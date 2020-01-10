package com.qianfeng.smsplatform.gateway.thread;

import com.qianfeng.smsplatform.common.model.Standard_Report;
import com.qianfeng.smsplatform.common.model.Standard_Submit;
import com.qianfeng.smsplatform.gateway.mq.DelayMessagePostProcessor;
import com.qianfeng.smsplatform.gateway.mq.StatQueue;
import com.qianfeng.smsplatform.gateway.netty4.entity.CmppDeliver;
import com.qianfeng.smsplatform.gateway.netty4.entity.ReportMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;

import static com.qianfeng.smsplatform.gateway.config.RabbitConfig.QUEUE_DELAY_PER_MESSAGE_TTL_MSG_SMS_SEND;

public class SendReportThread implements Runnable{
    private final static Logger log = LoggerFactory.getLogger(SendReportThread.class);

    private AmqpTemplate rabbitTemplate;

    private String pushTopic;
    private String updateTopic;

    private StatQueue statqueue = StatQueue.getInstance();

    public SendReportThread(String pushTopic,String updateTopic,AmqpTemplate rabbitTemplate){
        this.pushTopic = pushTopic;
        this.updateTopic = updateTopic;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (statqueue.size() > 0) {
                    log.info("stat queue size:{}",statqueue.size());
                    CmppDeliver deliver = (CmppDeliver) statqueue.remove();
                    String str = deliver.getStat();
                    long msgid = deliver.getMsg_Id_DELIVRD();
                    ReportMatch reportMatch = ReportMatch.getInstance();
                    log.info("从REPORT MATCH中取值：{}",msgid);
                    Standard_Submit submit = (Standard_Submit) reportMatch.remove(msgid);
                    Standard_Report report = new Standard_Report();
                    if (deliver.getStat().equals("DELIVER")){
                        report.setState(1);
                    }
                    report.setSrcID(submit.getSrcSequenceId());
                    report.setMobile(submit.getDestMobile());
                    report.setErrorCode(deliver.getStat());
                    report.setMsgId(String.valueOf(msgid));
                    report.setClientID(submit.getClientID());
                    log.info("send report:{}",report);
                    rabbitTemplate.convertAndSend(pushTopic, report);
//                    rabbitTemplate.convertAndSend(updateTopic, report);
                    rabbitTemplate.convertAndSend(QUEUE_DELAY_PER_MESSAGE_TTL_MSG_SMS_SEND, report, new DelayMessagePostProcessor(10 * 1000));
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
