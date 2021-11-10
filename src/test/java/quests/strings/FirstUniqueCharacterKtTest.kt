package quests.strings

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class FirstUniqueCharacterKtTest {

    @ParameterizedTest
    @MethodSource("Arguments")
    fun Example(s: String, answer: Int) {
        assertEquals(answer, firstUniqChar(s))
    }


    companion object {
        @JvmStatic
        fun Arguments() = Stream.of(
            Arguments.of("leetcode", 0),
            Arguments.of("loveleetcode", 2),
            Arguments.of("aabb", -1),
            Arguments.of("a", 0),
            Arguments.of("awqeopiruqweoiruoqiweuroqpiwuerpqouieropiquweroiquweroiquwerpoiuqweoriuqwopieruqpowieurqopwieurpoqiwueropiqwuerpoiquweropiquwerpoiuqweopiruqowpieurpoqiwuerpoiquweropiuqwerpoiuqwopieruqopiweuroiqwueroiuqweroiuqowieruoqiwueroiqwueorqytuqreyqirtiiertyuqiewruqiouert", 0),
        )
    }

}