package com.tsi.training;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.KafkaListener;

@EnableJpaRepositories
@SpringBootApplication
@Slf4j
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Checking that Kafka message can be received
    // In reality, will be received by a different service
    @KafkaListener(topics = "Message")
    public void handleMessage(String message) {
        log.info("Received topic from Order - {}", message);
    }

}