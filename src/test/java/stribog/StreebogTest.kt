package stribog

import hash.stribog.Streebog256
import hash.stribog.Streebog512
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import utils.hexStringToByteArray
import java.nio.charset.Charset

internal class StreebogTest {

    private val testEmptyIn = ByteArray(0)
    private val testEmptyOut256: ByteArray =
       "3f539a213e97c802cc229d474c6aa32a825a360b2a933a949fd925208d9ce1bb".hexStringToByteArray()
    private val testEmptyOut512: ByteArray =
            "8e945da209aa869f0455928529bcae4679e9873ab707b55315f56ceb98bef0a7362f715528356ee83cda5f2aac4c6ad2ba3a715c1bcd81cb8e9f90bf4c1c1a8a".hexStringToByteArray()

    private val testIn = "The quick brown fox jumps over the lazy dog".toByteArray(Charset.forName("ASCII"))
    private val testOut256: ByteArray =
            "3e7dea7f2384b6c5a3d0e24aaa29c05e89ddd762145030ec22c71a6db8b2c1f4".hexStringToByteArray()
    private val testOut512: ByteArray =
            "d2b793a0bb6cb5904828b5b6dcfb443bb8f33efc06ad09368878ae4cdc8245b97e60802469bed1e7c21a64ff0b179a6a1e0bb74d92965450a0adab69162c00fe".hexStringToByteArray()

    private val testRfc1In: ByteArray =
            "303132333435363738393031323334353637383930313233343536373839303132333435363738393031323334353637383930313233343536373839303132".hexStringToByteArray()
    private val testRfc1Out256: ByteArray =
            "9d151eefd8590b89daa6ba6cb74af9275dd051026bb149a452fd84e5e57b5500".hexStringToByteArray()
    private val testRfc1Out512: ByteArray =
            "1b54d01a4af5b9d5cc3d86d68d285462b19abc2475222f35c085122be4ba1ffa00ad30f8767b3a82384c6574f024c311e2a481332b08ef7f41797891c1646f48".hexStringToByteArray()

    private val testRfc2In: ByteArray =
            "d1e520e2e5f2f0e82c20d1f2f0e8e1eee6e820e2edf3f6e82c20e2e5fef2fa20f120eceef0ff20f1f2f0e5ebe0ece820ede020f5f0e0e1f0fbff20efebfaeafb20c8e3eef0e5e2fb".hexStringToByteArray()
    private val testRfc2Out256: ByteArray =
            "9dd2fe4e90409e5da87f53976d7405b0c0cac628fc669a741d50063c557e8f50".hexStringToByteArray()
    private val testRfc2Out512: ByteArray =
            "1e88e62226bfca6f9994f1f2d51569e0daf8475a3b0fe61a5300eee46d961376035fe83549ada2b8620fcd7c496ce5b33f0cb9dddc2b6460143b03dabac9fb28".hexStringToByteArray()


    @Test
    fun streebog256Test() {
        assertArrayEquals(testEmptyOut256, Streebog256().computeHash(testEmptyIn))
        assertArrayEquals(testOut256, Streebog256().computeHash(testIn))
        assertArrayEquals(testRfc1Out256, Streebog256().computeHash(testRfc1In))
        assertArrayEquals(testRfc2Out256, Streebog256().computeHash(testRfc2In))
    }

    @Test
    fun streebog512Test() {
        assertArrayEquals(testEmptyOut512, Streebog512().computeHash(testEmptyIn))
        assertArrayEquals(testOut512, Streebog512().computeHash(testIn))
        assertArrayEquals(testRfc1Out512, Streebog512().computeHash(testRfc1In))
        assertArrayEquals(testRfc2Out512, Streebog512().computeHash(testRfc2In))
    }

}