package com.robot.factory.service;

import com.robot.factory.model.RobotFactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockHandler {
    private final RobotFactoryRepository repository;

    @Autowired
    public StockHandler(RobotFactoryRepository repository) {
        this.repository = repository;
    }

    public void reduceStocksOfOrderComponents(String[] componentCodes) {
        for(String componentCode : componentCodes) {
            repository.reduceStock(componentCode);
        }
    }
}
