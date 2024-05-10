package com.tsi.training.controller;


import com.tsi.training.dto.StartMessageRequest;
import com.tsi.training.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderConsumerController {

    private final OrderService orderService;

    @PostMapping("/startProcess")
    public void sendKafkaTopic(@RequestBody @Valid StartMessageRequest startMessageRequest)
    {
        orderService.sendOrderProcessMessage(startMessageRequest.getMessageText());
    }
}
