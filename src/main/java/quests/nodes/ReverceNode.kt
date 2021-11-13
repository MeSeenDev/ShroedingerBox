package quests.nodes

/**
 * @author Vyacheslav Doroshenko
 */


fun reverseNodeClassic(node: LineNode): LineNode {
    var (prev, next, current)
            = Triple<LineNode?, LineNode?, LineNode?>(null, null, node)
    while (current != null) {
        next = current.next
        current.next = prev
        prev = current
        current = next
    }
    return prev!!

}

tailrec fun reverseTailRec(
    prev: LineNode? = null,
    current: LineNode? = null,
): LineNode? {
    if (current == null) return prev
    val next = current.next
    current.next = prev
    return reverseTailRec(prev = current, current = next)
}
