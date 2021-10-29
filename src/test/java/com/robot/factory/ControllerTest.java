package com.robot.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robot.factory.controller.RobotFactoryController;
import com.robot.factory.exceptions.ComponentTypesIncompatibilityException;
import com.robot.factory.exceptions.NumberOfComponentsIsNotSufficientException;
import com.robot.factory.model.ComponentDto;
import com.robot.factory.model.Order;
import com.robot.factory.service.RobotFactoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RobotFactoryController.class)
class ControllerTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String SERVICE_CALL_ERROR_MESSAGE = "Cannot call {} mockMvc service";
    private static final String REST_SERVICE_BASE_PATH = "/api/robot-factory";
    private static final String ORDERS_REST_SERVICE_PATH = REST_SERVICE_BASE_PATH + "/orders";
    private final ComponentDto INSUFFICIENT_NUMBER_OF_COMPONENT_DTO = new ComponentDto(Arrays.asList('I', 'A'));
    private final ComponentDto INCOMPATIBLE_TYPES_COMPONENT_DTO = new ComponentDto(Arrays.asList('B', 'A', 'I', 'H'));
    private final ComponentDto PROPER_COMPONENT_DTO = new ComponentDto(Arrays.asList('I', 'A', 'D', 'F'));

    @MockBean
    RobotFactoryService robotFactoryService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnErrorResponseWhenNumberOfComponentsIsNotSufficient() {
        when(robotFactoryService.createOrder(any())).thenThrow(NumberOfComponentsIsNotSufficientException.class);
        try {
            this.mockMvc.perform(post(ORDERS_REST_SERVICE_PATH).contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(INSUFFICIENT_NUMBER_OF_COMPONENT_DTO)))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            logger.error(SERVICE_CALL_ERROR_MESSAGE, ORDERS_REST_SERVICE_PATH);
        }
    }

    @Test
    void shouldReturnErrorResponseWhenComponentTypesAreIncompatible() {
        when(robotFactoryService.createOrder(any())).thenThrow(ComponentTypesIncompatibilityException.class);
        try {
            this.mockMvc.perform(post(ORDERS_REST_SERVICE_PATH).contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(INCOMPATIBLE_TYPES_COMPONENT_DTO)))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            logger.error(SERVICE_CALL_ERROR_MESSAGE, ORDERS_REST_SERVICE_PATH);
        }
    }

    @Test
    void shouldReturnCreatedOrderWhenCorrectInputProvided(){
        Order mockOrder = new Order("ID", 160.11);
        when(robotFactoryService.createOrder(any())).thenReturn(mockOrder);
        try {
            this.mockMvc.perform(post(ORDERS_REST_SERVICE_PATH).contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(PROPER_COMPONENT_DTO)))
                    .andExpect(content().string(objectMapper.writeValueAsString(mockOrder)))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            logger.error(SERVICE_CALL_ERROR_MESSAGE, ORDERS_REST_SERVICE_PATH);
        }
    }
}
