package com.tsi.training;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@EnableJpaRepositories
@SpringBootApplication
@Slf4j
@EnableScheduling
public class OrderProcessApplication {


    public static void main(String[] args) throws IOException
    {
        startKafkaServer();

        SpringApplication.run(OrderProcessApplication.class, args);
    }

    // Checking that Kafka message can be received
    // In reality, will be received by a different service
    @KafkaListener(topicPattern = "topics.example.*")
    public void basicListener(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                              String message) {
        log.info("Received message \"{}\" from topic [{}]", message, topic);
    }


    private static void startKafkaServer() throws IOException
    {
        String[] zookeeperCommands = {
                "C:/kafka_2.13-3.7.0/bin/windows/zookeeper-server-start.bat",
                "C:/kafka_2.13-3.7.0/config/zookeeper.properties"
        };
        String[] kafkaCommands = {
                "C:/kafka_2.13-3.7.0/bin/windows/kafka-server-start.bat",
                "C:/kafka_2.13-3.7.0/config/server.properties"
        };

        ProcessBuilder zookeeper = new ProcessBuilder(zookeeperCommands);
        ProcessBuilder apache = new ProcessBuilder(kafkaCommands);

        zookeeper.start();
        apache.start();
    }


    private static void outputProcess(Process process) throws IOException
    {
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(process.getErrorStream()));

        System.out.println("Here is the standard output of the command:\n");
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }

        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
    }



}