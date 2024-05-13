package com.tsi.training;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;


@EnableJpaRepositories
@SpringBootApplication
@Slf4j
@EnableScheduling
public class DemoApplication {


    public static void main(String[] args)
    {
        try
        {
            startKafkaServer();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        SpringApplication.run(DemoApplication.class, args);
    }

    // Checking that Kafka message can be received
    // In reality, will be received by a different service
    @KafkaListener(topicPattern = "topics.example.*")
    public void basicListener(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                              String message) {
        log.info("Received message \"{}\" from topic [{}]", message, topic);
    }


    private static void startKafkaServer() throws IOException, InterruptedException
    {

        String[] startZookeeperCommands = {
                "C:/kafka_2.13-3.7.0/bin/windows/zookeeper-server-start.bat",
                "C:/kafka_2.13-3.7.0/config/zookeeper.properties"
        };
        String[] startKafkaCommands = {
                "C:/kafka_2.13-3.7.0/bin/windows/kafka-server-start.bat",
                "C:/kafka_2.13-3.7.0/config/server.properties"
        };

        ProcessBuilder startZookeeper = new ProcessBuilder(startZookeeperCommands);
        ProcessBuilder startKafka = new ProcessBuilder(startKafkaCommands);

        startZookeeper.start();
        startKafka.start();
    }


}