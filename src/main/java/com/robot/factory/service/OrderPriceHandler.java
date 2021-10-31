package com.robot.factory.service;

import com.robot.factory.model.RobotFactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPriceHandler {

    private final RobotFactoryRepository repository;

    @Autowired
    public OrderPriceHandler(RobotFactoryRepository repository) {
        this.repository = repository;
    }

    public Double calculateOrderPrice(String[] componentCodes) {
        Double sum = 0.0;
        for(String componentCode : componentCodes) {
            sum += repository.getPrice(componentCode);
        }
        return sum;
    }
}
