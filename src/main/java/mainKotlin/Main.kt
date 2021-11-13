package mainKotlin

import time.countTime

fun main() {

    val intArray = IntArray(1){0}
    countTime {
        (0..9).onEach {
            ++intArray[0]
        }
    }

    println(intArray.toList())
}









