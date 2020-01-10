package com.qianfeng.smsplatform.gateway.config;

import com.qianfeng.smsplatform.common.constants.RabbitMqConsants;
import com.qianfeng.smsplatform.gateway.netty4.NettyClient;
import com.qianfeng.smsplatform.gateway.netty4.Utils.Command;
import com.qianfeng.smsplatform.gateway.netty4.Utils.MsgUtils;
import com.qianfeng.smsplatform.gateway.netty4.entity.CmppMessageHeader;
import com.qianfeng.smsplatform.gateway.netty4.entity.CmppSubmit;
import com.qianfeng.smsplatform.gateway.thread.SendReportThread;
import com.qianfeng.smsplatform.gateway.thread.SendSubmitRespThread;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
public class NettyConfig {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    //ip
    public static String host = "127.0.0.1";
    //端口
    public static int port = 7890;
    //账号
    public static String serviceId = "801xxx";
    //密码
    public static String pwd = "xxxxxx";

    public static String srcId = "106902202xxx";


    @Bean(initMethod = "start")
    public  NettyClient nettyClient(){
        NettyClient cmppClient= new NettyClient(host,port,serviceId,pwd);
        cmppClient.start();
        return cmppClient;
    }

    @Bean
    public SendReportThread sendReportThread() {
        SendReportThread sendReportThread= new SendReportThread(RabbitMqConsants.TOPIC_PUSH_SMS_REPORT
                ,RabbitMqConsants.TOPIC_UPDATE_SMS_REPORT,rabbitTemplate);
        new Thread(sendReportThread).start();
        return sendReportThread;
    }

    @Bean
    public SendSubmitRespThread sendSubmitRespThread() {
        SendSubmitRespThread sendSubmitRespThread= new SendSubmitRespThread(RabbitMqConsants.TOPIC_SMS_SEND_LOG
                ,rabbitTemplate);
        new Thread(sendSubmitRespThread).start();
        return sendSubmitRespThread;
    }
}
