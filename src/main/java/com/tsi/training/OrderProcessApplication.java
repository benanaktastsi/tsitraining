package com.tsi.training;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableJpaRepositories
@SpringBootApplication
@Slf4j
@EnableScheduling
public class OrderProcessApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderProcessApplication.class, args);
    }
}