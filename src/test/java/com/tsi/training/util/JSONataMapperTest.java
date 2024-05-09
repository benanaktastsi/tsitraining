package com.tsi.training.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;

@SpringBootTest
public class JSONataMapperTest {

    @Test
    public void mapFile(){
        try {
            ProcessResponse response = JSONataMapper.processJSON(Path.of("./src/main/resources/input.json"));
            System.out.println("==========================\n PARTS");
            System.out.println(response.parts);
            System.out.println("==========================\n ORDERS");
            System.out.println(response.orders);
        }
        catch (Exception ignored){}
    }

}
