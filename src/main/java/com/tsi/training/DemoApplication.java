package com.tsi.training;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableJpaRepositories
@SpringBootApplication
@Slf4j
@EnableScheduling
public class DemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    // Checking that Kafka message can be received
    // In reality, will be received by a different service
    @KafkaListener(topicPattern = "topics.example.*")
    public void basicListener(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                              String message) {
        log.info("Received message \"{}\" from topic [{}]", message, topic);
    }

}