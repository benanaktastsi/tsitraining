package com.tsi.training.consumer;

import com.tsi.training.exception.MissingFileException;
import com.tsi.training.service.PartService;
import com.tsi.training.util.JSONataMapper;
import com.tsi.training.util.OutputMapper;
import com.tsi.training.util.OutputWriter;
import com.tsi.training.util.ProcessResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

/**
 * This class is for consuming messages of orders
 */
@Slf4j
@Component
public class ProcessOrderConsumer {

    private final PartService partService;

    @Value("${json.folder.input}")
    private String inputPath;

    public ProcessOrderConsumer(PartService partService) {
        this.partService = partService;
    }

    @KafkaListener(topics="topics.example.text-file-name")
    public void processOrders(String inputFileName){
        try {
            Path filePath = getPath(inputFileName);

            ProcessResponse formattedInput = JSONataMapper.processJSON(filePath);
            partService.validateParts(formattedInput);
            OutputWriter.writeToFile(OutputMapper.splitIntoStrings(formattedInput));
        } catch (IOException exception){
            log.error(exception.getMessage());
        }
    }

    private Path getPath(String inputFileName)
    {
        if(StringUtils.isEmpty(inputFileName)) throw new MissingFileException("Input file name empty.");

        String inputFile = inputPath + inputFileName;
        log.info("Opening file {}", inputFile);
        return Path.of(inputFile);
    }
}
