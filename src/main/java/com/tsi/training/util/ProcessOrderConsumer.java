package com.tsi.training.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;
import java.nio.file.Path;

public class ProcessOrderConsumer {

    private static final Logger log = LoggerFactory.getLogger(ProcessOrderConsumer.class);

    @KafkaListener(topics="Message")
    public static void receiveKafkaMessage(String message){
        Path filePath = Path.of("./src/main/resources/" + message);
        try {
            ProcessResponse processed = JSONataMapper.processJSON(filePath);
        } catch (IOException exception){
            log.error(exception.getMessage());
        }
    }
}
