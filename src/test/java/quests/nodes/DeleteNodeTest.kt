package quests.nodes

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

internal class DeleteNodeTest {

    @ParameterizedTest
    @MethodSource("nodes")
    fun deleteNode(root: LineNode<Int>, node: LineNode<Int>, list: List<Int>) {
        DeleteNode().deleteNode(node)
        assertEquals(list, root.values)
    }

    companion object {

        private val root1 = ArrayDeque<LineNode<Int>>().also { deque ->
            deque.addFirst(LineNode(value = 1))
            (2..10).onEach { i ->
                val nextNode = LineNode(value = i)
                deque.first().next = nextNode
                deque.addFirst(nextNode)
            }
        }
        private val root2 = ArrayDeque<LineNode<Int>>().also { deque ->
            deque.addFirst(LineNode(value = 1))
            (2..10).onEach { i ->
                val nextNode = LineNode(value = i)
                deque.first().next = nextNode
                deque.addFirst(nextNode)
            }
        }

        @JvmStatic
        fun nodes(): Stream<Arguments> =
            Stream.of(
                Arguments.of(root1.last(), root1[5], listOf(1, 2, 3, 4,  /*5,*/6, 7, 8, 9, 10)),
                Arguments.of(root2.last(), root2[2], listOf(1, 2, 3, 4, 5, 6, 7, /*8,*/ 9, 10))
            )
    }
}