package stribog

class Streebog256() : Streebog() {

    init {
        hashSize = 256
    }

    override fun initialize() {
        super.initialize()
        for (i in 0 until BLOCK_SIZE) {
            _iv[i] = 0x01
        }
    }
}