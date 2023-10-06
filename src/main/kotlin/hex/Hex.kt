package hex

import java.math.BigInteger
import java.nio.charset.StandardCharsets


/**
 * @author Vyacheslav Doroshenko
 */

val String.hex2Bytes: ByteArray
    get() {
        check(length % 2 == 0) { "Must have an even length" }

        val byteIterator = chunkedSequence(2)
            .map { it.toInt(16).toByte() }
            .iterator()

        return ByteArray(length / 2) { byteIterator.next() }
    }

val String.hexToDec: ULong get() = toULong(16)

val ByteArray.bytes2Hex: String
    get() = joinToString(separator = "") { String.format("%02X", it.toInt() and 0xFF) }

val String.hex2Ascii: String
    get() {
        if (!(this.length % 2 == 0 && this.matches("[0-9A-Fa-f]+".toRegex()))) {
            return "null"
        }
        val bytes = this.hex2Bytes
        // Replace non printable ASCII with ".".
        for (i in bytes.indices) {
            if (bytes[i] < 0x20.toByte() || bytes[i] == 0x7F.toByte()) {
                bytes[i] = 0x2E.toByte()
            }
        }
        // Hex to ASCII.
        return String(bytes, StandardCharsets.US_ASCII)
    }

val String.ascii2Hex: String
    get() =
        takeIf { isNotBlank() }?.map { String.format("%02X", it.code) }
            ?.joinToString(separator = "") ?: "null"

val String.hex2Bin: String
    get() {
        if (!(length % 2 == 0 && matches("[0-9A-Fa-f]+".toRegex()))) {
            return "null"
        }
        var bin = BigInteger(this, 16).toString(2)
        // Pad left with zeros (have not found a better way...).
        if (bin.length < length * 4) {
            val diff = length * 4 - bin.length
            val pad = StringBuilder()
            for (i in 0 until diff) {
                pad.append("0")
            }
            pad.append(bin)
            bin = pad.toString()
        }
        return bin
    }

val String.bin2Hex: String
    get() {
        if (!(length % 8 == 0 && matches("[0-1]+".toRegex()))) {
            return "null"
        }
        var hex = BigInteger(this, 2).toString(16)
        if (hex.length % 2 != 0) {
            hex = "0$hex"
        }
        return hex
    }
