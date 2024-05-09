package com.tsi.training.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {

    static void writeToFile(String output, String fileName) throws IOException {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/"+fileName));
            writer.write(output);

            writer.close();
    }
}
