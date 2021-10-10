package com.gulbagomedovich.orderservice.service.impl;

import com.gulbagomedovich.orderservice.dto.OrderDto;
import com.gulbagomedovich.orderservice.model.Order;
import com.gulbagomedovich.orderservice.repository.OrderRepository;
import com.gulbagomedovich.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);

        Boolean isPaid = restTemplate.postForObject("http://payment-service/payments",
                order.getCustomerUsername(), Boolean.class);
        if (isPaid != null && isPaid) {
            orderRepository.save(order);
            log.info("Order was created: {}", order);
            rabbitTemplate.convertAndSend("orderExchange", "order.created", order);
        }

        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.getById(id);
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
        log.info("Order with id={} was deleted", id);
    }
}
