package quests.nodes

class DeleteNode {

    fun <T> deleteNode(node: LineNode<T>?) {
        node?.value = node?.next?.value!!
        node.next = node.next!!.next
    }

}