package com.tsi.training.util;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class JSONataMapperTest {

    @Test
    public void readValidFileTest() {
        Path goodPath = Path.of("./src/main/resources/input/input.json");
        try {
            JSONataMapper.processJSON(goodPath);
        } catch (Exception e){
            fail("No error should ocuur when reading a correct file");
        }
    }

    @Test
    public void readMissingFileTest() {
        Path missingPath = Path.of("./src/main/resources/input/missingfile.json");
        assertThrows(NoSuchFileException.class, () -> JSONataMapper.processJSON(missingPath));
    }

    @Test
    public void readBadFileTest() {
        Path badPath = Path.of("./src/main/resources/input/baddata.json");
        try {
            JSONataMapper.processJSON(badPath);
        } catch (Exception e){
            Assertions.assertInstanceOf(ClassCastException.class, e, "Expected error should be ClassCastException, but was" + e.getClass());
        }
    }

}