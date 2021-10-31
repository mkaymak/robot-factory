package com.robot.factory.service.rule.checkers;

import com.robot.factory.exceptions.ComponentTypesIncompatibilityException;
import com.robot.factory.model.RobotComponentType;
import com.robot.factory.repository.RobotFactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
@Order(3)
public class ComponentTypeCompatibilityChecker implements RuleChecker {

    private RobotFactoryRepository repository;

    @Autowired
    public ComponentTypeCompatibilityChecker(RobotFactoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean check(String[] components) {
        EnumSet<RobotComponentType> types = EnumSet.noneOf(RobotComponentType.class);
        for(String component : components) {
            types.add(repository.getComponentType(component));
        }
        if(types.size() == RobotComponentType.values().length) {
            return true;
        }
        throw new ComponentTypesIncompatibilityException("");
    }
}
