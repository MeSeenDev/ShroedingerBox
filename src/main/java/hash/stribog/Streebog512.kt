package hash.stribog

class Streebog512() : Streebog() {
    init {
        hashSize = 512
    }

    override fun initialize() {
        super.initialize()
        for (i in 0 until BLOCK_SIZE) {
            initVector[i] = 0x00
        }
    }
}