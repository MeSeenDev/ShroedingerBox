package quests.nodes

/**
 * @author Vyacheslav Doroshenko
 */
data class LineNode(val value: Int = 0, var next: LineNode? = null) {
    /**
     * Возвращает список следующих value вместе с текущим
     */
    val values: List<Int>
        get() = mutableListOf<Int>().also { list ->
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
    val nodes: List<LineNode>
        get() = mutableListOf<LineNode>().also { list ->
            var current = this
            while (current.next != null) {
                list.add(current)
                current = current.next!!
            }
        }

    companion object {
        fun generate(range: IntRange): LineNode =
            ArrayDeque<LineNode>().also { deque ->
                range.forEach { i ->
                    val nextNode = LineNode(value = i)
                    if (deque.isEmpty()) deque.addFirst(nextNode)
                    deque.first().next = nextNode
                    deque.addFirst(nextNode)
                }
            }.last()

    }
}
