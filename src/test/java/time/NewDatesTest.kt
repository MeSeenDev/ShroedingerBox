package time

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDateTime
import java.util.stream.Stream
import kotlin.test.assertEquals

internal class NewDatesTest {
    @ParameterizedTest
    @MethodSource("args")
    fun toMoscowTime(
        localDateTime: LocalDateTime,
        zoneName: String,
        answer: LocalDateTime,
        message: String
    ) {
        val result = NewDates.toMoscowTime(localDateTime, zoneName)
        assertEquals(answer, result, message)
    }

    @ParameterizedTest
    @MethodSource("args")
    fun toMoscowTimeCut(
        localDateTime: LocalDateTime,
        zoneName: String,
        answer: LocalDateTime,
        message: String
    ) {
        val result = NewDates.toMoscowTimeCut(localDateTime, zoneName)
        assertEquals(answer, result, message)
    }


    companion object {
        @JvmStatic
        fun args() = Stream.of(
            Arguments.of(
                LocalDateTime.parse("2007-05-14T21:27"),
                "Europe/Moscow",
                LocalDateTime.parse("2007-05-14T21:27"),
                "Московская Зона и Время"
            ),
            Arguments.of(
                LocalDateTime.parse("2002-01-01T23:55"),
                "Europe/Berlin",
                LocalDateTime.parse("2002-01-02T01:55"),
                "Немецкая Зона и Время"
            )
        )

    }
}