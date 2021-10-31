package com.robot.factory.service;

import com.robot.factory.exceptions.ComponentOutOfStockException;
import com.robot.factory.exceptions.ComponentTypesIncompatibilityException;
import com.robot.factory.exceptions.InvalidComponentException;
import com.robot.factory.exceptions.NumberOfComponentsIsNotSufficientException;
import com.robot.factory.model.ComponentDto;
import com.robot.factory.model.Order;
import com.robot.factory.model.OrderRepository;
import com.robot.factory.service.rule.checkers.RuleChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final List<RuleChecker> ruleCheckers;
    private final StockHandler stockHandler;
    private final OrderPriceHandler priceHandler;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(List<RuleChecker> ruleCheckers, StockHandler stockHandler,
                        OrderPriceHandler priceHandler, OrderRepository orderRepository) {
        this.ruleCheckers = ruleCheckers;
        this.priceHandler = priceHandler;
        this.stockHandler = stockHandler;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(ComponentDto component) throws ComponentOutOfStockException,
                                                        ComponentTypesIncompatibilityException,
                                                        InvalidComponentException,
                                                        NumberOfComponentsIsNotSufficientException {
        String[] componentCodes = component.getComponents();
        ruleCheckers.forEach(checker -> checker.check(componentCodes));
        stockHandler.reduceStocksOfOrderComponents(componentCodes);
        Order order = new Order(priceHandler.calculateOrderPrice(componentCodes), componentCodes);
        return orderRepository.saveOrder(order);
    }
}
