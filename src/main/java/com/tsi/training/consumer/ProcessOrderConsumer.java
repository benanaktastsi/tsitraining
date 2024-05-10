package com.tsi.training.consumer;

import com.google.gson.Gson;
import com.tsi.training.dto.response.OrderDTO;
import com.tsi.training.service.PartService;
import com.tsi.training.util.JSONataMapper;
import com.tsi.training.util.OutputMapper;
import com.tsi.training.util.ProcessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

@Component
public class ProcessOrderConsumer {

    private final PartService partService;
    private static final Logger log = LoggerFactory.getLogger(ProcessOrderConsumer.class);

    @Autowired
    public ProcessOrderConsumer(PartService partService) {
        this.partService = partService;
    }

    @KafkaListener(topics="topics.example.text-file-name")
    public void receiveKafkaMessage(String message){
        Path filePath = Path.of("./src/main/resources/" + message);
        try {
            ProcessResponse formattedInput = JSONataMapper.processJSON(filePath);
            partService.validateParts(formattedInput);
            System.out.println(OutputMapper.splitIntoStrings(formattedInput));


        } catch (IOException exception){
            log.error(exception.getMessage());
        }
    }
}
