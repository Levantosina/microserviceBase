package com.levantos.customer;

import com.levantos.ampq.RabbitMqMessageProducer;
import com.levantos.clients.fraud.FraudCheckResponse;
import com.levantos.clients.fraud.FraudClient;
import com.levantos.clients.notification.NotificationClient;
import com.levantos.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final FraudClient fraudClient;

    private final RabbitMqMessageProducer rabbitMqMessageProducer;
    public  void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        Customer customer= Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        customerRepository.saveAndFlush(customer);

//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//        );

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());


    if(fraudCheckResponse.isFraudster()){
        throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Levantos...",
                        customer.getFirstName())
        );
        rabbitMqMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");

    }
}
