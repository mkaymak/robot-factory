package com.robot.factory;

import com.robot.factory.exceptions.InvalidComponentException;
import com.robot.factory.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class RobotFactoryRepositoryTest {

    @Autowired
    RobotFactoryRepository repository;

    @Mock
    private RobotComponentEntity componentEntity;

    @Mock
    private RobotStockEntity stockEntity;

    private final HashMap<String, RobotComponent> GENERAL_INFO_MAP = provideGeneralInformationMap();
    private final HashMap<String, Integer> STOCK_MAP = provideStockInformationMap();

    @BeforeEach
    void setUp() {
        when(componentEntity.getRobotGeneralInformation()).thenReturn(GENERAL_INFO_MAP);
        when(stockEntity.getRobotStock()).thenReturn(STOCK_MAP);
    }

    @ParameterizedTest()
    @ValueSource(strings = {"A", "B", "G", "J"})
    void shouldReturnTrueIfAvailableStockIsNotZero(String component) {
        assertThat(repository.isStockAvailable(component)).isTrue();
    }

    @ParameterizedTest()
    @ValueSource(strings = {"C"})
    void shouldReturnFalseIfAvailableStockIsZero(String component) {
        assertThat(repository.isStockAvailable(component)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("provideComponentCodesWithTypes")
    void shouldReturnComponentType(String componentCode, RobotComponentType expected) {
        assertThat(repository.getComponentType(componentCode)).isEqualTo(expected);
    }

    @ParameterizedTest()
    @ValueSource(strings = {"A", "D", "F", "I"})
    void shouldReturnPriceWhenComponentCodeIsInMap(String componentCode){
        assertThat(repository.getPrice(componentCode)).isEqualTo(GENERAL_INFO_MAP.get(componentCode).getPrice());
    }

    @ParameterizedTest()
    @ValueSource(strings = {"A", "D", "F", "I"})
    void shouldReduceStockWhenStockAvailable(String componentCode) throws InvalidComponentException {
        Integer beforeMethodSize = STOCK_MAP.get(componentCode);
        repository.reduceStock(componentCode);
        assertThat(repository.getRobotComponentStock(componentCode)).isEqualTo(beforeMethodSize - 1);
    }

    private static Stream<Arguments> provideComponentCodesWithTypes() {
        return Stream.of(
                Arguments.of("I",RobotComponentType.MATERIAL),
                Arguments.of("A",RobotComponentType.FACE),
                Arguments.of("D",RobotComponentType.ARM),
                Arguments.of("G",RobotComponentType.MOBILITY)
        );
    }

    private HashMap<String, RobotComponent> provideGeneralInformationMap() {
        return new HashMap<String, RobotComponent>() {{
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
    }

    private HashMap<String, Integer> provideStockInformationMap() {
        return new HashMap<String, Integer>() {{
            put("A", 9);
            put("B", 7);
            put("C", 0);
            put("D", 1);
            put("E", 3);
            put("F", 2);
            put("G", 0);
            put("H", 7);
            put("I", 92);
            put("J", 0);
        }};
    }
}
