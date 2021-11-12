package mainKotlin

import quests.nodes.LineNode
import quests.nodes.reverseTailRec
import time.countTime

fun main() {
    val nodes = LineNode.generate(0..15_000)
    var node = LineNode()
    countTime {
        node = reverseTailRec(nodes)
    }
    println(node.values)
}









