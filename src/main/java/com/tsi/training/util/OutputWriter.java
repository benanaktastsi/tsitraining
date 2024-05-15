package com.tsi.training.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class OutputWriter {

    private OutputWriter() {
    }

    public static void writeToFile(Map<String, String> dealerMap, String outputFolderPath)  {
        dealerMap.forEach((dealer, jsonString) -> {
            String filename = outputFolderPath + dealer + ".json";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write(jsonString);
            } catch (IOException e) {
                log.error("Error writing to file: " + e.getMessage());
            }
        });
    }
}