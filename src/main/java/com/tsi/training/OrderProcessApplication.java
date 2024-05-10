package com.tsi.training;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;


@EnableJpaRepositories
@SpringBootApplication
@Slf4j
@EnableScheduling
public class OrderProcessApplication {


    public static void main(String[] args) throws IOException {
        startKafkaServer();
        SpringApplication.run(OrderProcessApplication.class, args);
    }

    private static void startKafkaServer() throws IOException {
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
}