package com.robot.factory.rule.checker;

import com.robot.factory.exceptions.ComponentOutOfStockException;
import com.robot.factory.repository.RobotFactoryRepository;
import com.robot.factory.service.rule.checkers.StockAvailabilityChecker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class StockAvailabilityCheckerTest {

    @Autowired
    StockAvailabilityChecker checker;

    @MockBean
    private RobotFactoryRepository repository;

    @ParameterizedTest
    @MethodSource("provideAvailableStockedComponentCodes")
    void shouldReturnTrueIfAllComponentHasAvailableStock(Character[] input, boolean expected) {
        when(repository.isStockAvailable(any())).thenReturn(true);
        assertThat(expected).isEqualTo(checker.check(input));
    }

    @ParameterizedTest
    @MethodSource("provideUnavailableStockedComponentCodes")
    void shouldThrowExceptionIfOneOfTheComponentHasNoStock(Character[] input) {
        when(repository.isStockAvailable(any())).thenReturn(false);
        assertThatThrownBy(() -> checker.check(input)).isInstanceOf(ComponentOutOfStockException.class);
    }

    private static Stream<Arguments> provideAvailableStockedComponentCodes() {
        return Stream.of(
                Arguments.of(new Character[]{'I', 'A', 'D', 'F'}, true),
                Arguments.of(new Character[]{'I', 'B', 'D', 'G'}, true),
                Arguments.of(new Character[]{'J', 'H', 'E', 'C'}, true),
                Arguments.of(new Character[]{'B', 'D', 'H', 'J'}, true)
        );
    }

    private static Stream<Arguments> provideUnavailableStockedComponentCodes() {
        return Stream.of(
                Arguments.of(new Character[]{'I', 'C', 'D', 'F'}, false),
                Arguments.of(new Character[]{'I', 'C', 'D', 'G'}, false),
                Arguments.of(new Character[]{'J', 'H', 'E', 'C'}, false),
                Arguments.of(new Character[]{'C', 'D', 'H', 'J'}, false)
        );
    }
}
