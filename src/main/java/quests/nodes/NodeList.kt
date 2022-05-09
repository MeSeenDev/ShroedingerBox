package quests.nodes

class NodeList<Type> {

    var rootNode: LineNode<Type>? = null


    fun add(node: LineNode<Type>) {
        if (rootNode == null) {
            rootNode = node
        } else {
            rootNode?.let {
                getLastNode(it)?.next = node
            }
        }
    }

    private tailrec fun getLastNode(lineNode: LineNode<Type>?): LineNode<Type>? {
        if (lineNode?.next == null) return lineNode
        return getLastNode(lineNode.next)
    }

    fun remove(node: LineNode<Type>): LineNode<Type>? =
        rootNode?.let { root ->
            var curNode = root
            if (node == root) deleteRoot()
            while (curNode.next != null) {
                if(node == curNode.next){
                   curNode.next = curNode.next!!.next
                }else{
                    curNode = curNode.next!!
                }
            }
            null
        }


    private fun deleteRoot(): LineNode<Type>? =
        rootNode?.let {
            val deleted = rootNode
            rootNode = null
            deleted
        }

}