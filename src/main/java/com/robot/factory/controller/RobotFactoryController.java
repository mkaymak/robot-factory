package com.robot.factory.controller;

import com.robot.factory.exceptions.ComponentTypesIncompatibilityException;
import com.robot.factory.exceptions.NumberOfComponentsIsNotSufficientException;
import com.robot.factory.model.ComponentDto;
import com.robot.factory.service.RobotFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/robot-factory", produces = "text/plain;charset=UTF-8")
public class RobotFactoryController {

    RobotFactoryService robotFactoryService;

    @Autowired
    public RobotFactoryController(RobotFactoryService robotFactoryService) {
        this.robotFactoryService = robotFactoryService;
    }

    @PostMapping("/orders")
    public ResponseEntity convertToUrl(@RequestBody ComponentDto component)
                                                throws NumberOfComponentsIsNotSufficientException,
                                                        ComponentTypesIncompatibilityException{
        return new ResponseEntity<>(robotFactoryService.createOrder(component), HttpStatus.CREATED);
    }
}
