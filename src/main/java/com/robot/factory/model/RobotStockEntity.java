package com.robot.factory.model;

import java.util.HashMap;
import java.util.Map;

public class RobotStockEntity {
    private Map<String, Integer> robotStock = new HashMap<String, Integer>() {{
        put("A", 9);
        put("B", 7);
        put("C", 0);
        put("D", 1);
        put("E", 3);
        put("F", 2);
        put("G", 15);
        put("H", 7);
        put("I", 92);
        put("J", 15);
    }};

    public Map<String, Integer> getRobotStock() {
        return robotStock;
    }

    void reduceStock(String componentCode) {
        robotStock.computeIfPresent(componentCode, (code, stock) -> --stock);
    }
}
