package com.robot.factory.service.rule.checkers;

import com.robot.factory.exceptions.NumberOfComponentsIsNotSufficientException;
import com.robot.factory.model.RobotComponentType;
import org.springframework.stereotype.Service;

@Service
public class NumberOfComponentChecker extends RuleChecker{
    @Override
    public boolean check(Character[] components) {
        if(components.length != RobotComponentType.values().length) {
            throw new NumberOfComponentsIsNotSufficientException(String.valueOf(components.length));
        }
        return checkNext(components);
    }
}
