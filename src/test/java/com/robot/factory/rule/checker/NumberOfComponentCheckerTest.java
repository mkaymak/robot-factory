package com.robot.factory.rule.checker;

import com.robot.factory.exceptions.NumberOfComponentsIsNotSufficientException;
import com.robot.factory.service.rule.checkers.NumberOfComponentChecker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class NumberOfComponentCheckerTest {

    @Autowired
    NumberOfComponentChecker checker;

    @ParameterizedTest
    @MethodSource("provideSufficientNumberOfComponents")
    void shouldReturnTrueWhenNumberOfComponentIsSufficient(String[] input, boolean expected) {
        assertThat(expected).isEqualTo(checker.check(input));
    }

    @ParameterizedTest
    @MethodSource("provideInsufficientNumberOfComponents")
    void shouldThrowExceptionWhenNumberOfComponentIsInsufficient(String[] input) {
        assertThatThrownBy(() -> checker.check(input)).isInstanceOf(NumberOfComponentsIsNotSufficientException.class);
    }

    private static Stream<Arguments> provideSufficientNumberOfComponents() {
        return Stream.of(
                Arguments.of(new String[]{"I", "A", "D", "F"}, true),
                Arguments.of(new String[]{"I", "B", "D", "G"}, true),
                Arguments.of(new String[]{"J", "H", "E", "C"}, true),
                Arguments.of(new String[]{"B", "D", "H", "J"}, true)
        );
    }

    private static Stream<Arguments> provideInsufficientNumberOfComponents() {
        return Stream.of(
                Arguments.of(new String[]{"I", "D", "F"}, false),
                Arguments.of(new String[]{"I", "B", "D", "G", "C"}, false),
                Arguments.of(new String[]{"J"}, false),
                Arguments.of(new String[]{"B", "D"}, false),
                Arguments.of(new String[]{"B", "D", "H", "J", "A", "B"}, false)
        );
    }
}
