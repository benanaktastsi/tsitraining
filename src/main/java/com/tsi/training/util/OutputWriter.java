package com.tsi.training.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class OutputWriter {

    public static void writeToFile(Map<String, String> DealerMap) throws IOException {
        DealerMap.forEach((dealer, JSONString) -> {
            String filename = "./src/main/resources/"+ dealer;
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                writer.write(JSONString);writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
