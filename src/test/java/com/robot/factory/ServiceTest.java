package com.robot.factory;

import com.robot.factory.model.ComponentDto;
import com.robot.factory.service.RobotFactoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceTest {

    @Autowired
    RobotFactoryService service;

    @Test
    void serviceTest() {
        ComponentDto componentDto = new ComponentDto(new String[]{"I", "A", "D", "F"});
        service.createOrder(componentDto);
    }
}
