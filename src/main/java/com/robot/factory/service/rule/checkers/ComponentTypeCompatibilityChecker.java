package com.robot.factory.service.rule.checkers;

import com.robot.factory.exceptions.ComponentTypesIncompatibilityException;
import com.robot.factory.model.RobotComponentType;
import com.robot.factory.repository.RobotFactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
public class ComponentTypeCompatibilityChecker extends RuleChecker{

    private RobotFactoryRepository repository;

    @Autowired
    public ComponentTypeCompatibilityChecker(RobotFactoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean check(Character[] components) {
        EnumSet<RobotComponentType> types = EnumSet.noneOf(RobotComponentType.class);
        for(Character component : components) {
            types.add(repository.getComponentType(component));
        }
        if(types.size() == RobotComponentType.values().length) {
            return checkNext(components);
        }
        throw new ComponentTypesIncompatibilityException("");
    }
}
