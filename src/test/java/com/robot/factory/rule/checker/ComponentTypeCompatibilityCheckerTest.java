package com.robot.factory.rule.checker;

import com.robot.factory.exceptions.ComponentTypesIncompatibilityException;
import com.robot.factory.model.RobotComponentType;
import com.robot.factory.repository.RobotFactoryRepository;
import com.robot.factory.service.rule.checkers.ComponentTypeCompatibilityChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
class ComponentTypeCompatibilityCheckerTest {

    @Autowired
    ComponentTypeCompatibilityChecker checker;

    @MockBean
    private RobotFactoryRepository repository;

    @BeforeEach
    void setUp() {
        when(repository.getComponentType("D")).thenReturn(RobotComponentType.ARM);
        when(repository.getComponentType("E")).thenReturn(RobotComponentType.ARM);
        when(repository.getComponentType("A")).thenReturn(RobotComponentType.FACE);
        when(repository.getComponentType("B")).thenReturn(RobotComponentType.FACE);
        when(repository.getComponentType("C")).thenReturn(RobotComponentType.FACE);
        when(repository.getComponentType("I")).thenReturn(RobotComponentType.MATERIAL);
        when(repository.getComponentType("J")).thenReturn(RobotComponentType.MATERIAL);
        when(repository.getComponentType("F")).thenReturn(RobotComponentType.MOBILITY);
        when(repository.getComponentType("G")).thenReturn(RobotComponentType.MOBILITY);
        when(repository.getComponentType("H")).thenReturn(RobotComponentType.MOBILITY);
    }

    @ParameterizedTest
    @MethodSource("provideCompatibleComponentCodes")
    void shouldReturnTrueIfAllComponentTypesAreCompatible(String[] input, boolean expected) {
        assertThat(expected).isEqualTo(checker.check(input));
    }

    @ParameterizedTest
    @MethodSource("provideIncompatibleComponentCodes")
    void shouldThrowExceptionWhenOneOfTheComponentTypeOccursMoreThanOnce(String[] input) {
        assertThatThrownBy(() -> checker.check(input)).isInstanceOf(ComponentTypesIncompatibilityException.class);
    }

    private static Stream<Arguments> provideCompatibleComponentCodes() {
        return Stream.of(
                Arguments.of(new String[]{"I", "A", "D", "F"}, true),
                Arguments.of(new String[]{"I", "B", "D", "G"}, true),
                Arguments.of(new String[]{"J", "H", "E", "C"}, true),
                Arguments.of(new String[]{"B", "D", "H", "J"}, true)
        );
    }

    private static Stream<Arguments> provideIncompatibleComponentCodes() {
        return Stream.of(
                Arguments.of(new String[]{"I", "J", "D", "F"}, false),
                Arguments.of(new String[]{"F", "G", "H", "D"}, false),
                Arguments.of(new String[]{"J", "I", "E", "C"}, false),
                Arguments.of(new String[]{"A", "B", "H", "J"}, false)
        );
    }
}
