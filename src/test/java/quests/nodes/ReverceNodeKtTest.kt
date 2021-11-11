package quests.nodes

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class ReverceNodeKtTest {

    @ParameterizedTest
    @MethodSource("Args")
    fun reverseNode() {


    }

    companion object {
        fun asc() = ArrayDeque<LineNode>().also { deque ->
            deque.addFirst(LineNode(value = 0))
            (1..10).onEach { i ->
                val nextNode = LineNode(value = i)
                deque.first().next = nextNode
                deque.addFirst(nextNode)
            }

        }
        fun desc() = ArrayDeque<LineNode>().also { deque ->
            deque.addFirst(LineNode(value = 0))
            (10 downTo 1).onEach { i ->
                val nextNode = LineNode(value = i)
                deque.first().next = nextNode
                deque.addFirst(nextNode)
            }

        }
    }
}