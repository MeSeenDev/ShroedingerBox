package hash.stribog

class Streebog256 : Streebog() {
    init {
        hashSizeValue = 256
    }

    override fun initialize() {
        for (i in 0 until BLOCK_SIZE) {
            n[i] = 0x00
            sigma[i] = 0x00
            iv[i] = 0x01
        }
    }

    override fun hashFinal(): ByteArray {
        val result = ByteArray(32)
        System.arraycopy(hashValue, 0, result, 0, 32)
        return result
    }
}