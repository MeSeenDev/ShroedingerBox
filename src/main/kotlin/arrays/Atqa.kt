package arrays


var atqa = byteArrayOf(4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

val ByteArray.atqaInt: Int
    get() {
        return atqa[0].toInt() and 0xFF or ((atqa[1].toInt() and 0xFF) shl 8)
    }
