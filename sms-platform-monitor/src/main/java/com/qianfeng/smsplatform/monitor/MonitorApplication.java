package com.qianfeng.smsplatform.monitor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class MonitorApplication implements CommandLineRunner {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MonitorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}

