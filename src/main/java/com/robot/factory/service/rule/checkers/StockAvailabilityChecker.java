package com.robot.factory.service.rule.checkers;

import com.robot.factory.exceptions.ComponentOutOfStockException;
import com.robot.factory.exceptions.InvalidComponentException;
import com.robot.factory.model.RobotFactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(2)
public class StockAvailabilityChecker implements RuleChecker{
    private final RobotFactoryRepository repository;

    @Autowired
    public StockAvailabilityChecker(RobotFactoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean check(String[] components) throws InvalidComponentException {
        for(String component : components) {
            if(!repository.isStockAvailable(component))
                throw new ComponentOutOfStockException(String.valueOf(component));
        }
        return true;
    }
}
