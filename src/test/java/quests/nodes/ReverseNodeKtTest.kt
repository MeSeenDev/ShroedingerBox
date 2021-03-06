package quests.nodes

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import quests.nodes.ReverseNode.iterJavaReverseNode
import quests.nodes.ReverseNode.recJavaReverseNode
import java.util.stream.Stream

internal class ReverseNodeKtTest {

    @ParameterizedTest
    @MethodSource("nodes")
    fun reverseNode(node: LineNode<Int>) {
        val fairAnswer = node.values.reversed().toIntArray()
        val testAnswer = reverseNodeClassic(node).values.toIntArray()
        Assertions.assertArrayEquals(fairAnswer, testAnswer)
    }

    @ParameterizedTest
    @MethodSource("nodes")
    fun reverseJavaNode(node: LineNode<Int>) {
        val fairAnswer = node.values.reversed().toIntArray()
        val testAnswer = iterJavaReverseNode(node).values.toIntArray()
        Assertions.assertArrayEquals(fairAnswer, testAnswer)
    }

    @ParameterizedTest
    @MethodSource("nodes")
    fun reverseJavaRecNode(node: LineNode<Int>) {
        val fairAnswer = node.values.reversed().toIntArray()
        val testAnswer = recJavaReverseNode(null, node).values.toIntArray()
        Assertions.assertArrayEquals(fairAnswer, testAnswer)
    }

    @ParameterizedTest
    @MethodSource("nodes")
    fun reverseNodeTailRec(node: LineNode<Int>) {
        val fairAnswer = node.values.reversed().toIntArray()
        val testAnswer = reverseTailRec(current = node)?.values?.toIntArray()
        Assertions.assertArrayEquals(fairAnswer, testAnswer)
    }

    companion object {
        @JvmStatic
        fun nodes(): Stream<Arguments> =
            Stream.of(
                Arguments.of(ArrayDeque<LineNode<Int>>().also { deque ->
                    deque.addFirst(LineNode(value = 1))
                    (2..10).onEach { i ->
                        val nextNode = LineNode(value = i)
                        deque.first().next = nextNode
                        deque.addFirst(nextNode)
                    }

                }.last()),
                Arguments.of(ArrayDeque<LineNode<Int>>().also { deque ->
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