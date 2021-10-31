package com.robot.factory.model;

public class RobotComponent {
    private double price;
    private String name;
    private RobotComponentType type;

    public RobotComponent(double price, String name, RobotComponentType type) {
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    RobotComponentType getType() {
        return type;
    }
}
