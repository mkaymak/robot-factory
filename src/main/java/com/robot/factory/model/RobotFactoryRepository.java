package com.robot.factory.model;

import com.robot.factory.exceptions.InvalidComponentException;
import org.springframework.stereotype.Service;

@Service
public class RobotFactoryRepository {
    private final RobotComponentEntity robotComponentEntity = new RobotComponentEntity();
    private final RobotStockEntity stockEntity = new RobotStockEntity();

    public boolean isStockAvailable(String componentCode) throws InvalidComponentException{
        return getRobotComponentStock(componentCode) != 0;
    }

    public RobotComponentType getComponentType(String componentCode) throws InvalidComponentException {
        return getRobotComponent(componentCode).getType();
    }

    public Double getPrice(String componentCode) throws InvalidComponentException {
        return getRobotComponent(componentCode).getPrice();
    }

    public void reduceStock(String componentCode) throws InvalidComponentException {
        stockEntity.reduceStock(componentCode);
    }

    private RobotComponent getRobotComponent(String componentCode) {
        if(robotComponentEntity.getRobotGeneralInformation().containsKey(componentCode)) {
            return robotComponentEntity.getRobotGeneralInformation().get(componentCode);
        }
        throw new InvalidComponentException(String.valueOf(componentCode));
    }

    public Integer getRobotComponentStock(String componentCode) {
        if(robotComponentEntity.getRobotGeneralInformation().containsKey(componentCode)) {
            return stockEntity.getRobotStock().get(componentCode);
        }
        throw new InvalidComponentException(String.valueOf(componentCode));
    }
}
