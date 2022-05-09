package quests.nodes

/**
 * @author Vyacheslav Doroshenko
 */


fun <T> reverseNodeClassic(node: LineNode<T>): LineNode<T> {
    var (prev, next, current)
            = Triple<LineNode<T>?, LineNode<T>?, LineNode<T>?>(null, null, node)
    while (current != null) {
        next = current.next
        current.next = prev
        prev = current
        current = next
    }
    return prev!!

}

tailrec fun <T> reverseTailRec(
    prev: LineNode<T>? = null,
    current: LineNode<T>? = null,
): LineNode<T>? {
    if (current == null) return prev
    val next = current.next
    current.next = prev
    return reverseTailRec(prev = current, current = next)
}
