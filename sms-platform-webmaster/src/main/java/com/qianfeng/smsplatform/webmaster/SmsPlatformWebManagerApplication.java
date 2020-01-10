package com.qianfeng.smsplatform.webmaster;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
@EnableTransactionManagement//开启事务管理
@MapperScan("com.qianfeng.smsplatform.webmaster.dao")//Mybatis的DAO所在包
@ServletComponentScan(basePackages = "com.qianfeng.smsplatform.webmaster.config")
@EnableEurekaClient
@EnableFeignClients
public class SmsPlatformWebManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsPlatformWebManagerApplication.class, args);
    }

    @Autowired
    private DataSource dataSource;

    private String transactionExecution = "execution(* com.qianfeng.smsplatform.webmaster.service.impl..*(..))";

    //事务传播机制
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(transactionExecution);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        Properties attributes = new Properties();
        attributes.setProperty("find*", "PROPAGATION_SUPPORTS,ISOLATION_DEFAULT,readOnly");
        attributes.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
        TransactionInterceptor txAdvice = new TransactionInterceptor(new DataSourceTransactionManager(dataSource), attributes);
        advisor.setAdvice(txAdvice);
        return advisor;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
