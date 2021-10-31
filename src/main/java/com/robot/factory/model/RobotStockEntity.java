package com.robot.factory.model;

import java.util.HashMap;
import java.util.Map;

public class RobotStockEntity {
    public static Map<String, RobotComponent> robotStock = new HashMap<String, RobotComponent>() {{
        put("A",new RobotComponent(10.28, 9, "Humanoid Face", RobotComponentType.FACE));
        put("B",new RobotComponent(24.07,7,"LCD Face", RobotComponentType.FACE));
        put("C",new RobotComponent(13.30,0, "Steampunk Face", RobotComponentType.FACE));
        put("D",new RobotComponent(28.94,1, "Arms with Hands", RobotComponentType.ARM));
        put("E",new RobotComponent(12.39, 3, "Arms with Grippers", RobotComponentType.ARM));
        put("F",new RobotComponent(30.77, 2, "Mobility with Wheels", RobotComponentType.MOBILITY));
        put("G",new RobotComponent(55.13, 15, "Mobility with Legs", RobotComponentType.MOBILITY));
        put("H",new RobotComponent(50.00, 7, "Mobility with Tracks", RobotComponentType.MOBILITY));
        put("I",new RobotComponent(90.12, 92, "Material Bioplastic", RobotComponentType.MATERIAL));
        put("J",new RobotComponent(82.31, 15, "Material Metallic", RobotComponentType.MATERIAL));
    }};
}
