package com.robot.factory.model;

import java.util.HashMap;
import java.util.Map;

public class RobotComponentEntity {
    private Map<String, RobotComponent> robotGeneralInformation = new HashMap<String, RobotComponent>() {{
        put("A",new RobotComponent(10.28,"Humanoid Face", RobotComponentType.FACE));
        put("B",new RobotComponent(24.07,"LCD Face", RobotComponentType.FACE));
        put("C",new RobotComponent(13.30, "Steampunk Face", RobotComponentType.FACE));
        put("D",new RobotComponent(28.94, "Arms with Hands", RobotComponentType.ARM));
        put("E",new RobotComponent(12.39, "Arms with Grippers", RobotComponentType.ARM));
        put("F",new RobotComponent(30.77, "Mobility with Wheels", RobotComponentType.MOBILITY));
        put("G",new RobotComponent(55.13, "Mobility with Legs", RobotComponentType.MOBILITY));
        put("H",new RobotComponent(50.00, "Mobility with Tracks", RobotComponentType.MOBILITY));
        put("I",new RobotComponent(90.12,"Material Bioplastic", RobotComponentType.MATERIAL));
        put("J",new RobotComponent(82.31,"Material Metallic", RobotComponentType.MATERIAL));
    }};

    public Map<String, RobotComponent> getRobotGeneralInformation() {
        return robotGeneralInformation;
    }
}
