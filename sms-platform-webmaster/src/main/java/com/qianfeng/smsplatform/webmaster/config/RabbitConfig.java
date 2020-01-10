package com.qianfeng.smsplatform.webmaster.config;

import com.qianfeng.smsplatform.common.constants.RabbitMqConsants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    /**
     * 消费者数量，默认10
     */
    public static final int DEFAULT_CONCURRENT = 10;

    /**
     * 每个消费者获取最大投递数量 默认50
     */
    public static final int DEFAULT_PREFETCH_COUNT = 50;


    /**
     * 注入 Queue
     * @return
     */
    @Bean
    public Queue Queue() {
        return new Queue(RabbitMqConsants.TOPIC_PRE_SEND,true);
    }


    /**
     * 并发消费配置
     * @param configurer
     * @param connectionFactory
     * @return
     */
    @Bean("pointTaskContainerFactory")
    public SimpleRabbitListenerContainerFactory pointTaskContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setPrefetchCount(DEFAULT_PREFETCH_COUNT);
        factory.setConcurrentConsumers(DEFAULT_CONCURRENT);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        configurer.configure(factory, connectionFactory);
        return factory;
    }

}

