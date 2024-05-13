package com.tsi.training.scheduler;

import com.tsi.training.consumer.ProcessOrderConsumer;
import com.tsi.training.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderScheduler {

    private final OrderService orderService;

    private final ProcessOrderConsumer processOrderConsumer;
    @Value("${json.folder.sampleFileName}") private String sampleFileName;

    @Scheduled(fixedRate = 10 * 1000)
    public void scheduleSendOrderMessage() {
        // orderService.sendOrderProcessMessage("Process Orders");

        // Process order without Kafka
        this.processOrderConsumer.processOrders(this.sampleFileName);
    }

}
