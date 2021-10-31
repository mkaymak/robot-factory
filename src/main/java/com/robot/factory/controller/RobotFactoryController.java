package com.robot.factory.controller;

import com.robot.factory.model.ComponentDto;
import com.robot.factory.model.Order;
import com.robot.factory.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/robot-factory")
public class RobotFactoryController {

    OrderService orderService;

    @Autowired
    public RobotFactoryController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/orders")
    public ResponseEntity<Order> createOrder(@RequestBody ComponentDto component) {
        return new ResponseEntity<>(orderService.createOrder(component), HttpStatus.CREATED);
    }
}
