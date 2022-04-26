package utils


    fun String.hexStringToByteArray(): ByteArray {
        if (isEmpty()) return ByteArray(0)
        val tmpString = replace(" ", "")
        val len = tmpString.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((tmpString[i].digitToIntOrNull(16)
                ?: (-1 shl 4)) + tmpString[i + 1].digitToIntOrNull(16)!!).toByte()
            i += 2
        }
        return data
    }
