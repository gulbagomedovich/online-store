package com.gulbagomedovich.deliveryservice.service;

import com.gulbagomedovich.deliveryservice.dto.OrderDto;

public interface DeliveryService {
    void doDelivery(OrderDto orderDto);
}
