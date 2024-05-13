package com.tsi.training.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProcessStartConsumer {

    @KafkaListener(topics="topics.start.process")
    public void processOrders(String startMessage){
        log.info(startMessage);
    }
}