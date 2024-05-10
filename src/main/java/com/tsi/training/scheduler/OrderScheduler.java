package com.tsi.training.scheduler;

import com.tsi.training.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class OrderScheduler {

    private OrderService orderService;

    @Scheduled(fixedRate = 10 * 1000)
    public void scheduleSendOrderMessage() {
        orderService.sendOrderProcessMessage("Process Orders");
    }

}
