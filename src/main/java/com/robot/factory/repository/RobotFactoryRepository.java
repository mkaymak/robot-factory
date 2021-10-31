package com.robot.factory.repository;

import com.robot.factory.exceptions.InvalidComponentException;
import com.robot.factory.model.RobotComponentType;
import com.robot.factory.model.RobotStockEntity;
import org.springframework.stereotype.Service;

@Service
public class RobotFactoryRepository {
    public boolean isStockAvailable(Character component) {
        if(RobotStockEntity.robotStock.containsKey(component)) {
           return RobotStockEntity.robotStock.get(component).isStockAvailable();
        }
        throw new InvalidComponentException(String.valueOf(component));
    }

    public RobotComponentType getComponentType(Character component) {
        if(RobotStockEntity.robotStock.containsKey(component)) {
            return RobotStockEntity.robotStock.get(component).getType();
        }
        throw new InvalidComponentException(String.valueOf(component));
    }
}
