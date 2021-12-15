package hash.stribog

class Streebog512 : Streebog() {
    init {
        hashSizeValue = 512
    }

    override fun initialize() {
        for (i in 0 until BLOCK_SIZE) {
            n[i] = 0x00
            sigma[i] = 0x00
            iv[i] = 0x00
        }
    }

    override fun hashFinal(): ByteArray {
        return hashValue
    }
}