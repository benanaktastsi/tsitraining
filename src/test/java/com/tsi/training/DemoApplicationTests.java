package com.tsi.training;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@RunWith(Cucumber.class)
@CucumberOptions(
        stepNotifications = true,
        glue = {"src/java/com/tsi/training/stepdefs"},
        plugin = "pretty",
        features = {"src/test/resources/features"})
@ContextConfiguration(classes = DemoApplication.class)
@CucumberContextConfiguration
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

}
