package com.levantos.notification;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@SpringBootApplication(
        scanBasePackages = {"com.levantos.notification",
                             "com.levantos.ampq",}
)
@EnableDiscoveryClient
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")

})


public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMqMessageProducer rabbitMqMessageProducer, NotificationConfig notificationConfig) {
//        return args -> {
//            rabbitMqMessageProducer.publish(
//                    new Person("Dmitrii",20),
//                    notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKey());
//        };
//    }
//
//    record Person(String name,int age){}
}

