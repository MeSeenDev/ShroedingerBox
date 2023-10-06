package mainKotlin

import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.time.Duration


fun main(): Unit = runBlocking {

}

fun inp(dig: Int): Int {
    return if (dig > 9) dig % 10 else dig
}

typealias TimeProvider2 = (String) -> Unit

typealias TimeProvider = () -> Long
inline fun runWithTimeCountS(calculateTime: (TimeProvider2) -> Unit) {
    val startTimer = System.currentTimeMillis()
    calculateTime.invoke {
        println("$it ${System.currentTimeMillis() - startTimer}")
    }
}
inline fun runWithTimeCount(calculateTime: (TimeProvider) -> Unit) {
    val startTimer = System.currentTimeMillis();
    calculateTime { System.currentTimeMillis() - startTimer }
}

inline fun runWithTimeCount0(calculateTime: (TimeStringProvider) -> Unit) {
    val startTimer = System.currentTimeMillis();
    calculateTime { " - " + (System.currentTimeMillis() - startTimer) + "mc"; }
}

inline fun runWithTimeCount2(timeCounter: (execTime: Long) -> Unit) {
    System.currentTimeMillis().also { startExec ->
        timeCounter(System.currentTimeMillis() - startExec)
    }

}


fun Duration.toLogFormat(): String =
    " - " + this.inWholeMilliseconds + "mc"

typealias TimeStringProvider = () -> String


val currentTime: Long
    get() = System.currentTimeMillis()

private fun getFileExtension(file: File): String {
    val name = file.name
    val lastIndexOf = name.lastIndexOf(".")
    return if (lastIndexOf == -1) {
        "" // empty extension
    } else name.substring(lastIndexOf)
}

val Byte.intVal: Int
    get() {
        val ub = this.toUByte()
        return ub.toInt()
    }

fun byteToUnsigned(b: Byte): UByte {
    return b.toUByte()
}














