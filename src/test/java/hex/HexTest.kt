package hex

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class HexTest {

    @ParameterizedTest
    @MethodSource("hexLines")
    fun hexToBytes(hexString: String, hexBytes: ByteArray) {
        val bytes = hexString.hex2Bytes
        Assertions.assertArrayEquals(hexBytes, bytes, "")
    }


    companion object {


        @JvmStatic
        fun hexLines(): Stream<Arguments> = Stream.of(
            Arguments.of(
                "", byteArrayOf()
            ),

            )
    }
}