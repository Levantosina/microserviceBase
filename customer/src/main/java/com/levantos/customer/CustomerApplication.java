package com.levantos.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@SpringBootApplication(
        scanBasePackages = {
                "com.levantos.customer",
                "com.levantos.ampq"
        }
)
@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.levantos.clients"
)

@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")

})


public class CustomerApplication {
    public static void main(String[] args) {

        SpringApplication.run(CustomerApplication.class,args);

    }
}
