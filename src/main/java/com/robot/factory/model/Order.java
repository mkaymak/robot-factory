package com.robot.factory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Order {
    private String id;
    private Double price;
    @JsonIgnore
    private String[] components;

    Order setId(String id) {
        this.id = id;
        return this;
    }

    public Order(Double price, String[] components) {
        this.price = price;
        this.components = components;
    }
}
