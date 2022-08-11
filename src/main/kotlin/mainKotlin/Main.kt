package mainKotlin

import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun main(): Unit = runBlocking {


}


private fun getFileExtension(file: File): String {
    val name = file.name
    val lastIndexOf = name.lastIndexOf(".")
    return if (lastIndexOf == -1) {
        "" // empty extension
    } else name.substring(lastIndexOf)
}


var atqa = byteArrayOf(4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

val atqaInt: Int
    get() = atqa[0].toInt() and 0xFF or ((atqa[1].toInt() and 0xFF) shl 8)


suspend fun computate(): String {
    return suspendCoroutine { continuation ->

        continuation.resumeWithException(Throwable("HErota"))


    }
}

class Sisko {

    fun kilo(spusk: String) {
        println(" SPUPK ")
    }
}

fun Sisko.kilo(kokushki: String) {
    println("KOKUSHKI")
}


/**
 * Конвертирует строку, представляющую набор байтов в 16с.с. в массив байтов
 *
 * @param s строка, представляющая массив байтов в 16 с.с.
 * @return массив байтов
 */
fun hexStringToByteArray(s: String?): ByteArray? {
    if (s == null || s.isEmpty()) return ByteArray(0)
    val tmpString = s.replace(" ", "")
    val len = tmpString.length
    val data = ByteArray(len / 2)
    var i = 0
    while (i < len) {
        data[i / 2] = ((((tmpString[i].digitToIntOrNull(16) ?: (-1 shl 4)) + tmpString[i + 1].digitToIntOrNull(16)!!)
            ?: -1)).toByte()
        i += 2
    }
    return data
}

val Byte.intVal: Int
    get() {
        val ub = this.toUByte()
        return ub.toInt()
    }

fun byteToUnsigned(b: Byte): UByte {
    return b.toUByte()
}


fun hexToDec(s: String): ULong {
    return s.toULong(16)
}











