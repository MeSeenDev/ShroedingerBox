package quests.nodes

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ReverseNodeKtTest {

    @ParameterizedTest
    @MethodSource("nodes")
    fun reverseNode(node: LineNode) {
        val fairAnswer = node.values.reversed().toIntArray()
        val testAnswer = reverseNodeClassic(node).values.toIntArray()
        Assertions.assertArrayEquals(fairAnswer, testAnswer)
    }

    @ParameterizedTest
    @MethodSource("nodes")
    fun reverseNodeTailRec(node: LineNode) {
        val fairAnswer = node.values.reversed().toIntArray()
        val testAnswer = reverseTailRec(current = node).values.toIntArray()
        Assertions.assertArrayEquals(fairAnswer, testAnswer)
    }

    companion object {
        @JvmStatic
        fun nodes(): Stream<Arguments> =
            Stream.of(
                Arguments.of(ArrayDeque<LineNode>().also { deque ->
                    deque.addFirst(LineNode(value = 1))
                    (2..10).onEach { i ->
                        val nextNode = LineNode(value = i)
                        deque.first().next = nextNode
                        deque.addFirst(nextNode)
                    }

                }.last()),
                Arguments.of(ArrayDeque<LineNode>().also { deque ->
                    deque.addLast(LineNode(value = 10))
                    (9 downTo 1).onEach { i ->
                        val nextNode = LineNode(value = i)
                        deque.first().next = nextNode
                        deque.addFirst(nextNode)
                    }

                }.last())


            )
    }
}