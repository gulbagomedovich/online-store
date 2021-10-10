package com.gulbagomedovich.deliveryservice.service.impl;

import com.gulbagomedovich.deliveryservice.dto.OrderDto;
import com.gulbagomedovich.deliveryservice.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {
    @Override
    @RabbitListener(queues = "orderServiceQueue")
    public void doDelivery(OrderDto orderDto) {
        log.info("Order was delivered: {}", orderDto);
    }
}
