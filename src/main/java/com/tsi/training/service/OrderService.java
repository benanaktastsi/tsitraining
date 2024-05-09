package com.tsi.training.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    @Value("${spring.kafka.template.topics}")
    private final String[] topics;
    public final KafkaTemplate<String, String> kafkaTemplate;


    public void sendKafkaTopic()
    {

        log.info("Sending message from topic [{}]", this.topics[0]);
        kafkaTemplate.send(this.topics[0], "ProcessOrders");

        log.info("Sending message from topic [{}]", this.topics[1]);
        kafkaTemplate.send(this.topics[1], "input");

    }
}
