package com.tsi.training.util;

import com.tsi.training.exception.MissingFileException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Path;

@SpringBootTest
public class JSONataMapperTest {

    @Test
    public void readValidFileTest() {
        Path goodPath = Path.of("./src/test/resources/input/input.json");
        try {
            JSONataMapper.processJSON(goodPath);
        } catch (Exception e){
            Assertions.fail("No error should ocuur when reading a correct file");
        }
    }

    @Test
    public void readMissingFileTest() {
        Path missingPath = Path.of("./src/test/resources/input/missingfile.json");
        Assertions.assertThrows(
                MissingFileException.class,
                () -> JSONataMapper.processJSON(missingPath),
                "Expected a MissingFileException"
        );
    }

    @Test
    public void readBadFileTest() {
        Path badPath = Path.of("./src/test/resources/input/baddata.json");
        Assertions.assertThrows(
                ClassCastException.class,
                () -> JSONataMapper.processJSON(badPath),
                "Expected a ClassCastException"
        );
    }

}