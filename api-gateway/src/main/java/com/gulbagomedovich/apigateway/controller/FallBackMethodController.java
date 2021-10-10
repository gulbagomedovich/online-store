package com.gulbagomedovich.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {
    @GetMapping("/productServiceFallBack")
    public String productServiceFallBackMethod() {
        return "Служба продукта занимает больше времени, чем ожидалось";
    }

    @GetMapping("/orderServiceFallBack")
    public String orderServiceFallBackMethod() {
        return "Служба заказа занимает больше времени, чем ожидалось";
    }
}
