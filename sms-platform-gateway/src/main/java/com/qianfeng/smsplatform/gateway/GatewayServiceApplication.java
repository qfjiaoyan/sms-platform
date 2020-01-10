package com.qianfeng.smsplatform.gateway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
public class GatewayServiceApplication implements CommandLineRunner {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
