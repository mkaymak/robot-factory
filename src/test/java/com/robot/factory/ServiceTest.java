package com.robot.factory;

import com.robot.factory.model.ComponentDto;
import com.robot.factory.model.Order;
import com.robot.factory.service.OrderPriceHandler;
import com.robot.factory.service.OrderService;
import com.robot.factory.service.rule.checkers.ComponentTypeCompatibilityChecker;
import com.robot.factory.service.rule.checkers.NumberOfComponentChecker;
import com.robot.factory.service.rule.checkers.StockAvailabilityChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ServiceTest {
    private static final Double ORDER_PRICE = 160.11;
    private static final String[] COMPONENT_CODES = new String[]{"I", "A", "D", "F"};

    @Autowired
    OrderService service;

    @MockBean
    ComponentTypeCompatibilityChecker compatibilityChecker;

    @MockBean
    NumberOfComponentChecker numberOfComponentChecker;

    @MockBean
    StockAvailabilityChecker stockAvailabilityChecker;

    @MockBean
    OrderPriceHandler priceHandler;

    @BeforeEach
    void setUp() {
        when(numberOfComponentChecker.check(any())).thenReturn(true);
        when(stockAvailabilityChecker.check(any())).thenReturn(true);
        when(compatibilityChecker.check(any())).thenReturn(true);
        when(priceHandler.calculateOrderPrice(any())).thenReturn(ORDER_PRICE);
    }

    @Test
    void shouldReturnCreatedOrderWhenComponentCodesInputIsCorrect() {
        Order order = new Order(ORDER_PRICE, COMPONENT_CODES);
        ComponentDto dto = new ComponentDto(COMPONENT_CODES);
        assertThat(service.createOrder(dto))
                            .usingRecursiveComparison()
                            .ignoringFields("id")
                            .isEqualTo(order);
    }
}
