package doubleex

import equls.trueEquals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DoubleExTest {


    private fun generateDouble(): Stream<Arguments> =
        (0..5).asSequence().asStream().map { arguments(Math.random()) }

    @DisplayName(value = "operator trueEquals")
    @ParameterizedTest
    @MethodSource("generateDouble")
    fun simpleTrue(double: Double) {
        Assertions.assertEquals(true, double.trueEquals(double))
    }

    @DisplayName(value = "Kotlin operator equals")
    @ParameterizedTest
    @MethodSource("generateDouble")
    fun simpleBase(double: Double) {
        Assertions.assertEquals(true, double.equals(double))
    }


    @DisplayName(value = "Kotlin operator ==")
    @ParameterizedTest
    @MethodSource("generateDouble")
    fun simpleBaseKotlinSix(double: Double) {
        Assertions.assertEquals(true, (double == double))
    }

}