package quests.arrays.twosum

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class TwoSumKtTest {

    @ParameterizedTest
    @MethodSource("args")
    fun twoSumSimple(nums: IntArray, target: Int, answer: IntArray) {
        val res = TwoSum.twoSumSimple(nums, target)
        Assertions.assertArrayEquals(answer, res)
    }

    @ParameterizedTest
    @MethodSource("args")
    fun twoSumSimpleKtTest(nums: IntArray, target: Int, answer: IntArray) {
        val res = twoSumSimpleKt(nums, target)
        Assertions.assertArrayEquals(answer, res)
    }

    @ParameterizedTest
    @MethodSource("args")
    fun twoSumMidleKtTest(nums: IntArray, target: Int, answer: IntArray) {
        val res = twoSumMidle(nums, target)
        Assertions.assertArrayEquals(answer, res)
    }

    companion object {
        @JvmStatic
        fun args() = Stream.of(
            Arguments.of(intArrayOf(2, 7, 11, 15), 9, intArrayOf(0, 1)),
            Arguments.of(intArrayOf(3, 2, 4), 6, intArrayOf(1, 2)),
            Arguments.of(intArrayOf(3, 3), 6, intArrayOf(0, 1))
        )
    }
}