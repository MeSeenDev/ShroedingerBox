package quests.strings

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ReverseIntegerKtTest {

    @ParameterizedTest
    @MethodSource("Args")
    fun Example1(int: Int, answer: Int) {
        val reversed = reverse2(int)
        Assertions.assertEquals(answer, reversed)
    }

    companion object {
        @JvmStatic
        fun Args() = Stream.of<Arguments>(
            Arguments.of(123, 321),
            Arguments.of(-123, -321),
            Arguments.of(120, 21),
            Arguments.of(0, 0),
            Arguments.of(1534236469, 0),
        )

    }

}