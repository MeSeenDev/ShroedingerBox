package quests.nodes

/**
 * @author Vyacheslav Doroshenko
 */


fun reverseNode(node: LineNode): LineNode {
    var (prev, next, current)
            = Triple<LineNode?, LineNode?, LineNode?>(null, null, node)
    while (current != null) {
        next = current.next;
        current.next = prev;
        prev = current;
        current = next;
    }
    return prev!!

}