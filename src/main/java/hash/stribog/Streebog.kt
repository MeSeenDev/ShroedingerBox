package hash.stribog

import utils.toBigInteger
import utils.toULong
import java.math.BigInteger
import kotlin.experimental.xor

abstract class Streebog : BaseStreebog() {

    private var buffer: MutableList<Byte> = ArrayList()
    private var epsilon: ByteArray = ByteArray(BLOCK_SIZE)
    private var n: ByteArray = ByteArray(BLOCK_SIZE)

    /**
     * Инициализационный вектор.
     */
    protected var initVector: ByteArray = ByteArray(BLOCK_SIZE)


    open fun initialize() {
        for (i in 0 until BLOCK_SIZE) {
            n[i] = 0x00
            epsilon[i] = 0x00
        }
        buffer.clear()
    }

    /**
     * Формирование хэш-значения.
     */
    protected open fun hashCore(array: ByteArray, ibStart: Int, cbSize: Int) {
        initialize()
        prepareBuffer(array, ibStart, cbSize)
        processBuffer(512)
    }

    protected var hashSize = 0

    protected open fun hashFinal(): ByteArray {
        val length = addPadding()
        processBuffer(length)
        compress(n, true)
        compress(epsilon, true)
        val result = ByteArray(hashSize / 8)
        System.arraycopy(initVector, 0, result, 0, result.size)
        reverseBytes(result)
        return result
    }

    open fun processBlock(message: ByteArray, length: Int) {
        var curLength = length
        reverseBytes(message)
        compress(message, false)

        var index = BLOCK_SIZE - 1
        while (curLength > 0) {
            require(index >= 0)
            curLength += n[index].toUByte().toInt()
            n[index] = curLength.toByte()
            curLength = curLength shr 8
            index--
        }

        var carry = 0
        index = BLOCK_SIZE - 1
        while (index >= 0) {
            carry += epsilon[index].toUByte().toInt()
            carry += message[index].toUByte().toInt()
            epsilon[index] = carry.toByte()
            carry = carry shr 8
            index--
        }
    }

    private fun prepareBuffer(array: ByteArray, ibStart: Int, cbSize: Int) {
        val message = ByteArray(cbSize)
        System.arraycopy(array, ibStart, message, 0, cbSize)
        for (b in message) {
            buffer.add(b)
        }
    }

    private fun processBuffer(length: Int) {
        while (buffer.size >= BLOCK_SIZE) {
            val message = ByteArray(BLOCK_SIZE)
            for (i in 0 until BLOCK_SIZE) {
                message[i] = buffer[i]
            }
            processBlock(message, length)
            buffer = ArrayList(buffer.subList(BLOCK_SIZE, buffer.size))
        }
    }

    private fun addPadding(): Int {
        val result = buffer.size * 8
        buffer.add(1.toByte())
        while (buffer.size < BLOCK_SIZE) buffer.add(0.toByte())
        return result
    }

    /**
     * S-преобразование. Нелинейное биективное преобразование.
     */
    private fun transformS(state: ByteArray) {
        for (i in 0 until BLOCK_SIZE) state[i] = sbox[state[i].toUByte().toInt()]
    }

    /**
     * P - преобразование. Перестановка байт.
     */
    private fun transformP(state: ByteArray) {
        val prevState = ByteArray(BLOCK_SIZE)
        System.arraycopy(state, 0, prevState, 0, BLOCK_SIZE)
        for (i in 0 until BLOCK_SIZE) {
            state[i] = prevState[tau[i].toInt()]
        }
    }

    /**
     * L - преобразование. Умножение входного вектора на бинарную матрицу.
     */
    private fun transformL(state: ByteArray) {
        for (i in 0..7) {
            var t: ULong = 0u
            for (j in 0..7) {
                val stateIndex = i shl 3 or j
                val stateValue = state[stateIndex].toUByte().toInt()
                t = t.toBigInteger().xor(BigInteger(mixMatrix[j][stateValue].toString())).toULong()
            }
            val index = i shl 3
            state[index] = t.toBigInteger().shiftRight(56).toByte()
            state[index + 1] = t.toBigInteger().shiftRight(48).toByte()
            state[index + 2] = t.toBigInteger().shiftRight(40).toByte()
            state[index + 3] = t.toBigInteger().shiftRight(32).toByte()
            state[index + 4] = t.toBigInteger().shiftRight(24).toByte()
            state[index + 5] = t.toBigInteger().shiftRight(16).toByte()
            state[index + 6] = t.toBigInteger().shiftRight(8).toByte()
            state[index + 7] = t.toBigInteger().toByte()
        }
    }

    private fun transformationsLPS(vector: ByteArray) {
        transformS(vector)
        transformP(vector)
        transformL(vector)
    }

    /**
     * Функция сжатия.
     */
    private fun compress(state: ByteArray, zero: Boolean) {
        val sigma = ByteArray(BLOCK_SIZE)
        if (zero) System.arraycopy(initVector, 0, sigma, 0, 64) else for (i in 0..63) sigma[i] =
            (initVector[i] xor n[i]).toByte()
        transformationsLPS(sigma)
        val result = ByteArray(BLOCK_SIZE)
        System.arraycopy(state, 0, result, 0, BLOCK_SIZE)
        for (round in 0..12) {
            for (i in 0 until BLOCK_SIZE) result[i] = result[i] xor sigma[i]
            if (round == 12) break
            transformationsLPS(result)

            // sigma = LPS(sigma xor _keyConsts)
            val cround = keyConsts[round]
            for (i in 0 until BLOCK_SIZE) sigma[i] = sigma[i] xor cround[i]
            transformationsLPS(sigma)
        }
        for (i in 0 until BLOCK_SIZE) result[i] = (result[i] xor initVector[i] xor state[i]).toByte()
        System.arraycopy(result, 0, initVector, 0, 64)
    }

    private fun reverseBytes(data: ByteArray) {
        val length = data.size
        if (length > 1) {
            for (i in 0 until length / 2) {
                val tmp = data[i]
                data[i] = data[length - i - 1]
                data[length - i - 1] = tmp
            }
        }
    }

    /**
     * Вычисляет хэш от buffer
     */
    open fun computeHash(buffer: ByteArray): ByteArray {
        requireNotNull(buffer) { "buffer is null" } // Оставлю на случай вызова из Java
        hashCore(buffer, 0, buffer.size)
        val hashValue = hashFinal()
        initialize()
        return hashValue
    }


    companion object {
        /**
         * Длина блока хэшируемых данных в байтах.
         */
        const val BLOCK_SIZE: Int = 64
    }
}