package com.tsi.training;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@CucumberOptions(
        stepNotifications = true,
        glue = {"src/java/com/tsi/training/stepdefs"},
        plugin = "pretty",
        features = {"src/test/resources/features"})
@ContextConfiguration(classes = OrderProcessApplication.class)
@CucumberContextConfiguration
@AutoConfigureMockMvc
class OrderProcessApplicationTests {

    @Test
    void contextLoads() {
    }

}
