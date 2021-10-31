package com.robot.factory.model;

import org.springframework.stereotype.Service;

@Service
public class OrderRepository {
    OrderEntity orderEntity = new OrderEntity();

    public Order saveOrder(Order order) {
        return orderEntity.addOrder(order);
    }
}
