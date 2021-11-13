package quests.strings

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ValidPalindromeKtTest {

    @ParameterizedTest
    @MethodSource("args")
    fun isPalindrome(s: String, b: Boolean) {
        Assertions.assertEquals(b, isPalindrome(s))
    }

    companion object {
        @JvmStatic
        fun args() =
            Stream.of(
                Arguments.of("A man, a _plan, a canal: Panama", true),
                Arguments.of("race a car", false),
                Arguments.of("ab_a", true),
                Arguments.of(" ", true),
            )
    }
}