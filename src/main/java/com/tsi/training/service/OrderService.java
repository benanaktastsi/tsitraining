package com.tsi.training.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {

    @Value("${spring.kafka.template.topics}")
    private String[] topics;

    @Value("${json.folder.sampleFileName}")
    private  String sampleFileName;

    @NonNull
    private KafkaTemplate<String, String> kafkaTemplate;


    public void sendOrderProcessMessage(String startProcessMessage) {

        log.info("Sending message from topic [{}]", this.topics[0]);
        kafkaTemplate.send(this.topics[0], startProcessMessage);

        log.info("Sending message from topic [{}]", this.topics[1]);
        kafkaTemplate.send(this.topics[1], sampleFileName);
    }
}
