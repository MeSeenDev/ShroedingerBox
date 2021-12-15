package hash.stribog

import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.experimental.and
import kotlin.experimental.or

class BitArray : Cloneable {
    private var repn: ByteArray
    private var length: Int

    /**
     * Creates a BitArray of the specified size, initialized to zeros.
     */
    constructor(length: Int) {
        require(length >= 0) { "Negative length for BitArray" }
        this.length = length
        repn = ByteArray((length + BITS_PER_UNIT - 1) / BITS_PER_UNIT)
    }

    /**
     * Creates a BitArray of the specified size, initialized from the
     * specified byte array.  The most significant bit of a[0] gets
     * index zero in the BitArray.  The array a must be large enough
     * to specify a value for every bit in the BitArray.  In other words,
     * 8*a.length <= length.
     */
    constructor(length: Int, a: ByteArray) {
        require(length >= 0) { "Negative length for BitArray" }
        require(a.size * BITS_PER_UNIT >= length) {
            "Byte array too short to represent " +
                    "bit array of given length"
        }
        this.length = length
        val repLength = (length + BITS_PER_UNIT - 1) / BITS_PER_UNIT
        val unusedBits = repLength * BITS_PER_UNIT - length
        val bitMask = (0xFF shl unusedBits).toByte()

        /*
         normalize the representation:
          1. discard extra bytes
          2. zero out extra bits in the last byte
         */repn = ByteArray(repLength)
        System.arraycopy(a, 0, repn, 0, repLength)
        if (repLength > 0) {
            repn[repLength - 1] = repn[repLength - 1] and bitMask
        }
    }

    /**
     * Create a BitArray whose bits are those of the given array
     * of Booleans.
     */
    constructor(bits: BooleanArray) {
        length = bits.size
        repn = ByteArray((length + 7) / 8)
        for (i in 0 until length) {
            set(i, bits[i])
        }
    }

    /**
     * Copy constructor (for cloning).
     */
    private constructor(ba: BitArray) {
        length = ba.length
        repn = ba.repn.clone()
    }

    /**
     * Returns the indexed bit in this BitArray.
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    operator fun get(index: Int): Boolean {
        if (index < 0 || index >= length) {
            throw ArrayIndexOutOfBoundsException(Integer.toString(index))
        }
        return (repn[subscript(index)] and position(index).toByte()) != 0.toByte()
    }

    /**
     * Sets the indexed bit in this BitArray.
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    operator fun set(index: Int, value: Boolean) {
        if (index < 0 || index >= length) {
            throw ArrayIndexOutOfBoundsException(Integer.toString(index))
        }
        val idx = subscript(index)
        val bit = position(index)
        if (value) {
            repn[idx] = repn[idx] or bit.toByte()
        } else {
            repn[idx] = repn[idx] and bit.inv().toByte()
        }
    }

    /**
     * Returns the length of this BitArray.
     */
    fun length(): Int {
        return length
    }

    /**
     * Returns a Byte array containing the contents of this BitArray.
     * The bit stored at index zero in this BitArray will be copied
     * into the most significant bit of the zeroth element of the
     * returned byte array.  The last byte of the returned byte array
     * will be contain zeros in any bits that do not have corresponding
     * bits in the BitArray.  (This matters only if the BitArray's size
     * is not a multiple of 8.)
     */
    fun toByteArray(): ByteArray {
        return repn.clone()
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other == null || other !is BitArray) return false
        val ba = other
        if (ba.length != length) return false
        var i = 0
        while (i < repn.size) {
            if (repn[i] != ba.repn[i]) return false
            i += 1
        }
        return true
    }

    /**
     * Return a boolean array with the same bit values a this BitArray.
     */
    fun toBooleanArray(): BooleanArray {
        val bits = BooleanArray(length)
        for (i in 0 until length) {
            bits[i] = get(i)
        }
        return bits
    }

    /**
     * Returns a hash code value for this bit array.
     *
     * @return  a hash code value for this bit array.
     */
    override fun hashCode(): Int {
        var hashCode = 0
        for (i in repn.indices) hashCode = 31 * hashCode + repn[i]
        return hashCode xor length
    }

    public override fun clone(): Any {
        return BitArray(this)
    }

    /**
     * Returns a string representation of this BitArray.
     */
    override fun toString(): String {
        val out = ByteArrayOutputStream()
        for (i in 0 until repn.size - 1) {
            out.write(NYBBLE[(repn[i].toInt() shr 4) and 0x0F], 0, 4)
            out.write(NYBBLE[repn[i].toInt() and 0x0F], 0, 4)
            if (i % BYTES_PER_LINE == BYTES_PER_LINE - 1) {
                out.write('\n'.code)
            } else {
                out.write(' '.code)
            }
        }

        // in last byte of repn, use only the valid bits
        for (i in BITS_PER_UNIT * (repn.size - 1) until length) {
            out.write(if (get(i)) '1'.code else '0'.code)
        }
        return String(out.toByteArray())
    }

    fun truncate(): BitArray {
        for (i in length - 1 downTo 0) {
            if (get(i)) {
                return BitArray(i + 1, Arrays.copyOf(repn, (i + BITS_PER_UNIT) / BITS_PER_UNIT))
            }
        }
        return BitArray(1)
    }

    companion object {
        private const val BITS_PER_UNIT = 8
        private fun subscript(idx: Int): Int {
            return idx / BITS_PER_UNIT
        }

        private fun position(idx: Int): Int { // bits big-endian in each unit
            return 1 shl BITS_PER_UNIT - 1 - idx % BITS_PER_UNIT
        }

        private val NYBBLE = arrayOf(
            byteArrayOf('0'.code.toByte(), '0'.code.toByte(), '0'.code.toByte(), '0'.code.toByte()),
            byteArrayOf('0'.code.toByte(), '0'.code.toByte(), '0'.code.toByte(), '1'.code.toByte()),
            byteArrayOf('0'.code.toByte(), '0'.code.toByte(), '1'.code.toByte(), '0'.code.toByte()),
            byteArrayOf('0'.code.toByte(), '0'.code.toByte(), '1'.code.toByte(), '1'.code.toByte()),
            byteArrayOf('0'.code.toByte(), '1'.code.toByte(), '0'.code.toByte(), '0'.code.toByte()),
            byteArrayOf('0'.code.toByte(), '1'.code.toByte(), '0'.code.toByte(), '1'.code.toByte()),
            byteArrayOf('0'.code.toByte(), '1'.code.toByte(), '1'.code.toByte(), '0'.code.toByte()),
            byteArrayOf('0'.code.toByte(), '1'.code.toByte(), '1'.code.toByte(), '1'.code.toByte()),
            byteArrayOf('1'.code.toByte(), '0'.code.toByte(), '0'.code.toByte(), '0'.code.toByte()),
            byteArrayOf('1'.code.toByte(), '0'.code.toByte(), '0'.code.toByte(), '1'.code.toByte()),
            byteArrayOf('1'.code.toByte(), '0'.code.toByte(), '1'.code.toByte(), '0'.code.toByte()),
            byteArrayOf('1'.code.toByte(), '0'.code.toByte(), '1'.code.toByte(), '1'.code.toByte()),
            byteArrayOf('1'.code.toByte(), '1'.code.toByte(), '0'.code.toByte(), '0'.code.toByte()),
            byteArrayOf('1'.code.toByte(), '1'.code.toByte(), '0'.code.toByte(), '1'.code.toByte()),
            byteArrayOf('1'.code.toByte(), '1'.code.toByte(), '1'.code.toByte(), '0'.code.toByte()),
            byteArrayOf('1'.code.toByte(), '1'.code.toByte(), '1'.code.toByte(), '1'.code.toByte())
        )
        private const val BYTES_PER_LINE = 8
    }
}
