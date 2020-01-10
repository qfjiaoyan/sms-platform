package com.qianfeng.smsplatform.gateway.mq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * MessagePostProcessor 扩展类
 * @author swt
 */
public class DelayMessagePostProcessor implements MessagePostProcessor {

    private long ttl = 0L;

    public DelayMessagePostProcessor(long ttl) {
        this.ttl = ttl;
    }

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().setExpiration(Long.toString(ttl));
        return message;
    }

}
