package quests;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FactorialTest {

    @ParameterizedTest
    @MethodSource("numPair")
    void calculate(String factorialVal, String number) {
        assertEquals(new BigInteger(factorialVal), Factorial.calculate(new BigInteger(number)));
    }

    @ParameterizedTest
    @MethodSource("numPair")
    void reverse(String factorialVal, String number) {
        assertEquals(new BigInteger(number), Factorial.reverse(new BigInteger(factorialVal)));
    }

    private static Stream<Arguments> numPair() {
        return Stream.of(
                arguments("3628800", "10"),
                arguments("120", "5"),
                arguments("5040", "7"),
                arguments("6227020800", "13"),
                arguments("8222838654177922817725562880000000", "31"),
                arguments("1", "0")
        );
    }
}