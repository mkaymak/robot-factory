package com.robot.factory.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderEntity {
    private Map<String, Order> orders = new HashMap<>();

    Order addOrder(Order order) {
        String id = createRandomUUID();
        order.setId(id);
        orders.put(id, order);
        return order;
    }

    private String createRandomUUID() {
        return UUID.randomUUID().toString();
    }
}
