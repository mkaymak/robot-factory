package com.robot.factory.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RobotComponent {
    private double price;
    private int stock;
    private String name;
    private RobotComponentType type;

    public boolean isStockAvailable() {
        return stock != 0;
    }

    public double getPrice() {
        return price;
    }

    public RobotComponentType getType() {
        return type;
    }
}
