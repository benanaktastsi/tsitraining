package com.tsi.training.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    public final KafkaTemplate<String, String> kafkaTemplate;


    public void sendKafkaTopic()
    {
        log.info("Sending Message topic from Order");
        kafkaTemplate.send("Message", "ProcessOrders");
    }
}
