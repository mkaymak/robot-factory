package com.robot.factory.service.rule.checkers;

import com.robot.factory.exceptions.NumberOfComponentsIsNotSufficientException;
import com.robot.factory.model.RobotComponentType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(1)
public class NumberOfComponentChecker implements RuleChecker{
    @Override
    public boolean check(String[] components) {
        if(components.length != RobotComponentType.values().length) {
            throw new NumberOfComponentsIsNotSufficientException(String.valueOf(components.length));
        }
        return true;
    }
}
