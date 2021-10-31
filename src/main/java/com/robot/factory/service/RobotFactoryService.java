package com.robot.factory.service;

import com.robot.factory.exceptions.ComponentOutOfStockException;
import com.robot.factory.exceptions.ComponentTypesIncompatibilityException;
import com.robot.factory.exceptions.InvalidComponentException;
import com.robot.factory.exceptions.NumberOfComponentsIsNotSufficientException;
import com.robot.factory.model.ComponentDto;
import com.robot.factory.model.Order;
import com.robot.factory.service.rule.checkers.RuleChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RobotFactoryService {
    private final List<RuleChecker> ruleCheckers;

    @Autowired
    public RobotFactoryService(List<RuleChecker> ruleCheckers) {
        this.ruleCheckers = ruleCheckers;
    }

    public Order createOrder(ComponentDto component) throws ComponentOutOfStockException,
                                                        ComponentTypesIncompatibilityException,
                                                        InvalidComponentException,
                                                        NumberOfComponentsIsNotSufficientException {
        ruleCheckers.forEach(checker -> checker.check(component.getComponents()));
        return new Order("12", 12.3);
    }
}
