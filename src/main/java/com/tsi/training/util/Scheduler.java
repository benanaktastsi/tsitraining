package com.tsi.training.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Scheduler {

    @Scheduled(fixedRate = 10 * 1000)
    public static void ScheduleTest() {
        log.info("Scheduler moment");
    }

}
