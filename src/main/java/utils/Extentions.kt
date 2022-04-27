package utils

import java.math.BigInteger


fun String.hexStringToByteArray(): ByteArray {
    check(length % 2 == 0) { "Must have an even length" }

    return chunked(2)
        .map { it.toInt(16).toByte() }
        .toByteArray()
}

fun ULong.toBigInteger(): BigInteger = BigInteger(this.toString())
fun UInt.toBigInteger(): BigInteger = BigInteger(this.toString())


fun BigInteger.toULong(): ULong = this.toLong().toULong()
fun BigInteger.toUInt(): UInt = this.toInt().toUInt()