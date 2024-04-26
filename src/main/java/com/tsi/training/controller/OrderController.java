package com.tsi.training.controller;


import com.tsi.training.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/kafka")
    public void sendKafkaTopic()
    {
        orderService.sendKafkaTopic();
    }


}
