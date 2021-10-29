package com.robot.factory.service;

import com.robot.factory.model.ComponentDto;
import com.robot.factory.model.Order;
import org.springframework.stereotype.Service;

@Service
public class RobotFactoryService {
    public Order createOrder(ComponentDto component) {
        return new Order("ID", 160.11);
    }
}
