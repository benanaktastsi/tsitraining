package com.tsi.training.util;

import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class OutputWriter {


    public static void writeToFile(Map<String, String> DealerMap, String outputFolderPath) throws IOException {
        DealerMap.forEach((dealer, JSONString) -> {
            String filename = outputFolderPath + dealer;
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                writer.write(JSONString);writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
