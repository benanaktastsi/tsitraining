package com.tsi.training.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

@Component
public class ProcessOrderConsumer {

    private static final Logger log = LoggerFactory.getLogger(ProcessOrderConsumer.class);

    @KafkaListener(topics="Message")
    public static void receiveKafkaMessage(String message){
        Path filePath = Path.of("./src/main/resources/" + message);
        try {
            ProcessResponse processed = JSONataMapper.processJSON(filePath);
            //todo see leo
        } catch (IOException exception){
            log.error(exception.getMessage());
        }
    }
}
