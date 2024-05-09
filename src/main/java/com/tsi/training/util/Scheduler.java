package com.tsi.training.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Scheduler {

    @Scheduled(fixedRate = 10 * 1000)
    public static void ScheduleTest() {
        System.out.println("Hello World");
    }

}
