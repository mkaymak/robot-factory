package com.robot.factory.service.rule.checkers;

import com.robot.factory.exceptions.ComponentOutOfStockException;
import com.robot.factory.exceptions.InvalidComponentException;
import com.robot.factory.repository.RobotFactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockAvailabilityChecker extends RuleChecker{
    private RobotFactoryRepository repository;

    @Autowired
    public StockAvailabilityChecker(RobotFactoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean check(Character[] components) throws InvalidComponentException {
        for(Character component : components) {
            if(!repository.isStockAvailable(component))
                throw new ComponentOutOfStockException(String.valueOf(component));
        }
        return checkNext(components);
    }
}
