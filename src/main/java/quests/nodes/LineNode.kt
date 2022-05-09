package quests.nodes

/**
 * @author Vyacheslav Doroshenko
 */
data class LineNode<Type>(var value: Type, var next: LineNode<Type>? = null) {
    /**
     * Возвращает список следующих value вместе с текущим
     */
    val values: List<Type>
        get() = mutableListOf<Type>().also { list ->
            var current = this
            while (current.next != null) {
                list.add(current.value)
                current = current.next!!
            }
            list.add(current.value)
        }

    /**
     * Возвращает список следующих node вместе с текущим
     */
    val nodes: List<LineNode<Type>>
        get() = mutableListOf<LineNode<Type>>().also { list ->
            var current = this
            while (current.next != null) {
                list.add(current)
                current = current.next!!
            }
        }

    companion object {
        fun generate(range: IntRange): LineNode<Int> =
            ArrayDeque<LineNode<Int>>().also { deque ->
                range.forEach { i ->
                    val nextNode = LineNode(value = i)
                    if (deque.isEmpty()) deque.addFirst(nextNode)
                    deque.first().next = nextNode
                    deque.addFirst(nextNode)
                }
            }.last()

    }
}
