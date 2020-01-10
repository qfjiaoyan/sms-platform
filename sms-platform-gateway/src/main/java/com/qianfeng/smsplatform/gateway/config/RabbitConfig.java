package com.qianfeng.smsplatform.gateway.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.qianfeng.smsplatform.common.constants.RabbitMqConsants.TOPIC_PUSH_SMS_REPORT;
import static com.qianfeng.smsplatform.common.constants.RabbitMqConsants.TOPIC_UPDATE_SMS_REPORT;
import static com.qianfeng.smsplatform.common.constants.RabbitMqConsants.TOPIC_SMS_SEND_LOG;

@Configuration
public class RabbitConfig {

    /**
     * 消费者数量，默认10
     */
    public static final int DEFAULT_CONCURRENT = 1;

    /**
     * 每个消费者获取最大投递数量 默认50
     */
    public static final int DEFAULT_PREFETCH_COUNT = 50;

    /** 短信发送队列 DLX */
    public static final String DLX_MSG_SMS_SEND = TOPIC_UPDATE_SMS_REPORT + ":dlx";

    /** 短信发送队列 延迟缓冲（按消息） */
    public static final String QUEUE_DELAY_PER_MESSAGE_TTL_MSG_SMS_SEND = "delay:per:message:" + TOPIC_UPDATE_SMS_REPORT;


    /**
     * 注入 Queue
     *
     * @return
     */
    @Bean
    public Queue pushQueue() {
        return new Queue(TOPIC_PUSH_SMS_REPORT, true);
    }

    @Bean
    public Queue updateQueue() {
        return new Queue(TOPIC_UPDATE_SMS_REPORT, true);
    }

    @Bean
    public Queue smsLogQueue() {
        return new Queue(TOPIC_SMS_SEND_LOG, true);
    }

    /**
     * 并发消费配置
     *
     * @param configurer
     * @param connectionFactory
     * @return
     */
    @Bean("pointTaskContainerFactory")
    public SimpleRabbitListenerContainerFactory pointTaskContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setPrefetchCount(DEFAULT_PREFETCH_COUNT);
        factory.setConcurrentConsumers(DEFAULT_CONCURRENT);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public Channel getChannel(ConnectionFactory connectionFactory) {
        Connection connection = connectionFactory.createConnection();
        Channel channel = connection.createChannel(true);
        return channel;
    }

    /**
     * 短信发送队列
     * @return
     */
    @Bean
    public Queue smsQueueDelayPerMessageTTL() {
        return QueueBuilder.durable(QUEUE_DELAY_PER_MESSAGE_TTL_MSG_SMS_SEND)
                .withArgument("x-dead-letter-exchange", DLX_MSG_SMS_SEND)
                .withArgument("x-dead-letter-routing-key", TOPIC_UPDATE_SMS_REPORT)
                .build();
    }

    @Bean
    public DirectExchange smsDelayExchange(){
        return new DirectExchange(DLX_MSG_SMS_SEND);
    }

    @Bean
    public Binding smsDelayBinding(Queue updateQueue, DirectExchange smsDelayExchange) {
        return BindingBuilder.bind(updateQueue)
                .to(smsDelayExchange)
                .with(TOPIC_UPDATE_SMS_REPORT);
    }

}

