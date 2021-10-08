package com.gulbagomedovich.orderservice.service;

import com.gulbagomedovich.orderservice.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto addOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long id);

    void deleteOrderById(Long id);
}
