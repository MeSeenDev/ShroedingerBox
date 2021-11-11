package quests.strings

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ValidAnagramTest {

    @ParameterizedTest
    @MethodSource("Args")
    fun testAnagram(s: String, t: String, answer: Boolean) {
        Assertions.assertEquals(answer, isAnagram(s = s, t = t))
    }

    companion object {
        @JvmStatic
        fun Args() =
            Stream.of(
                Arguments.of("anagram", "nagaram", true),
                Arguments.of("rat", "car", false),
                Arguments.of("evil", "vile", true),
                Arguments.of("ac", "bb", false)
            )
    }
}