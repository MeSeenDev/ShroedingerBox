package quests.strings

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

internal class MyAtoiTest {

    @ParameterizedTest
    @MethodSource("args")
    fun myAtoi(question: String, answer: Int) {
        val atoi = MyAtoi()
        assertEquals(answer, atoi.myAtoi(question), "Сравнение")
    }


    companion object {
        @JvmStatic
        fun args() = Stream.of(
            Arguments.of("\"42\"", 42),
            Arguments.of("42", 42),
            Arguments.of("   -42", -42),
            Arguments.of("4193 with words", 4193),
            Arguments.of("fdadfa with -154 words", -154),
        )
    }
}