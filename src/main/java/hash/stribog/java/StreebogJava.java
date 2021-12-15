package hash.stribog.java;

import com.google.common.primitives.UnsignedBytes;
import com.google.common.primitives.UnsignedLong;
import hash.stribog.BitArray;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * Алгоритм хэширования Стрибог.
 */
public abstract class StreebogJava {

    /**
     * Матрица для L - преобразования.
     */
    private static final UnsignedLong[] _mixMatrix = {
            UnsignedLong.valueOf("8e20faa72ba0b470", 16), UnsignedLong.valueOf("47107ddd9b505a38", 16), UnsignedLong.valueOf("ad08b0e0c3282d1c", 16), UnsignedLong.valueOf("d8045870ef14980e", 16),
            UnsignedLong.valueOf("6c022c38f90a4c07", 16), UnsignedLong.valueOf("3601161cf205268d", 16), UnsignedLong.valueOf("1b8e0b0e798c13c8", 16), UnsignedLong.valueOf("83478b07b2468764", 16),
            UnsignedLong.valueOf("a011d380818e8f40", 16), UnsignedLong.valueOf("5086e740ce47c920", 16), UnsignedLong.valueOf("2843fd2067adea10", 16), UnsignedLong.valueOf("14aff010bdd87508", 16),
            UnsignedLong.valueOf("0ad97808d06cb404", 16), UnsignedLong.valueOf("05e23c0468365a02", 16), UnsignedLong.valueOf("8c711e02341b2d01", 16), UnsignedLong.valueOf("46b60f011a83988e", 16),
            UnsignedLong.valueOf("90dab52a387ae76f", 16), UnsignedLong.valueOf("486dd4151c3dfdb9", 16), UnsignedLong.valueOf("24b86a840e90f0d2", 16), UnsignedLong.valueOf("125c354207487869", 16),
            UnsignedLong.valueOf("092e94218d243cba", 16), UnsignedLong.valueOf("8a174a9ec8121e5d", 16), UnsignedLong.valueOf("4585254f64090fa0", 16), UnsignedLong.valueOf("accc9ca9328a8950", 16),
            UnsignedLong.valueOf("9d4df05d5f661451", 16), UnsignedLong.valueOf("c0a878a0a1330aa6", 16), UnsignedLong.valueOf("60543c50de970553", 16), UnsignedLong.valueOf("302a1e286fc58ca7", 16),
            UnsignedLong.valueOf("18150f14b9ec46dd", 16), UnsignedLong.valueOf("0c84890ad27623e0", 16), UnsignedLong.valueOf("0642ca05693b9f70", 16), UnsignedLong.valueOf("0321658cba93c138", 16),
            UnsignedLong.valueOf("86275df09ce8aaa8", 16), UnsignedLong.valueOf("439da0784e745554", 16), UnsignedLong.valueOf("afc0503c273aa42a", 16), UnsignedLong.valueOf("d960281e9d1d5215", 16),
            UnsignedLong.valueOf("e230140fc0802984", 16), UnsignedLong.valueOf("71180a8960409a42", 16), UnsignedLong.valueOf("b60c05ca30204d21", 16), UnsignedLong.valueOf("5b068c651810a89e", 16),
            UnsignedLong.valueOf("456c34887a3805b9", 16), UnsignedLong.valueOf("ac361a443d1c8cd2", 16), UnsignedLong.valueOf("561b0d22900e4669", 16), UnsignedLong.valueOf("2b838811480723ba", 16),
            UnsignedLong.valueOf("9bcf4486248d9f5d", 16), UnsignedLong.valueOf("c3e9224312c8c1a0", 16), UnsignedLong.valueOf("effa11af0964ee50", 16), UnsignedLong.valueOf("f97d86d98a327728", 16),
            UnsignedLong.valueOf("e4fa2054a80b329c", 16), UnsignedLong.valueOf("727d102a548b194e", 16), UnsignedLong.valueOf("39b008152acb8227", 16), UnsignedLong.valueOf("9258048415eb419d", 16),
            UnsignedLong.valueOf("492c024284fbaec0", 16), UnsignedLong.valueOf("aa16012142f35760", 16), UnsignedLong.valueOf("550b8e9e21f7a530", 16), UnsignedLong.valueOf("a48b474f9ef5dc18", 16),
            UnsignedLong.valueOf("70a6a56e2440598e", 16), UnsignedLong.valueOf("3853dc371220a247", 16), UnsignedLong.valueOf("1ca76e95091051ad", 16), UnsignedLong.valueOf("0edd37c48a08a6d8", 16),
            UnsignedLong.valueOf("07e095624504536c", 16), UnsignedLong.valueOf("8d70c431ac02a736", 16), UnsignedLong.valueOf("c83862965601dd1b", 16), UnsignedLong.valueOf("641c314b2b8ee083", 16)
    };

    /**
     * Значения подстановки для S-преобразования.
     */
    private static final byte[] _sbox = {
            (byte) 0xFC, (byte) 0xEE, (byte) 0xDD, (byte) 0x11, (byte) 0xCF, (byte) 0x6E, (byte) 0x31, (byte) 0x16, (byte) 0xFB, (byte) 0xC4, (byte) 0xFA, (byte) 0xDA, (byte) 0x23, (byte) 0xC5, (byte) 0x04, (byte) 0x4D,
            (byte) 0xE9, (byte) 0x77, (byte) 0xF0, (byte) 0xDB, (byte) 0x93, (byte) 0x2E, (byte) 0x99, (byte) 0xBA, (byte) 0x17, (byte) 0x36, (byte) 0xF1, (byte) 0xBB, (byte) 0x14, (byte) 0xCD, (byte) 0x5F, (byte) 0xC1,
            (byte) 0xF9, (byte) 0x18, (byte) 0x65, (byte) 0x5A, (byte) 0xE2, (byte) 0x5C, (byte) 0xEF, (byte) 0x21, (byte) 0x81, (byte) 0x1C, (byte) 0x3C, (byte) 0x42, (byte) 0x8B, (byte) 0x01, (byte) 0x8E, (byte) 0x4F,
            (byte) 0x05, (byte) 0x84, (byte) 0x02, (byte) 0xAE, (byte) 0xE3, (byte) 0x6A, (byte) 0x8F, (byte) 0xA0, (byte) 0x06, (byte) 0x0B, (byte) 0xED, (byte) 0x98, (byte) 0x7F, (byte) 0xD4, (byte) 0xD3, (byte) 0x1F,
            (byte) 0xEB, (byte) 0x34, (byte) 0x2C, (byte) 0x51, (byte) 0xEA, (byte) 0xC8, (byte) 0x48, (byte) 0xAB, (byte) 0xF2, (byte) 0x2A, (byte) 0x68, (byte) 0xA2, (byte) 0xFD, (byte) 0x3A, (byte) 0xCE, (byte) 0xCC,
            (byte) 0xB5, (byte) 0x70, (byte) 0x0E, (byte) 0x56, (byte) 0x08, (byte) 0x0C, (byte) 0x76, (byte) 0x12, (byte) 0xBF, (byte) 0x72, (byte) 0x13, (byte) 0x47, (byte) 0x9C, (byte) 0xB7, (byte) 0x5D, (byte) 0x87,
            (byte) 0x15, (byte) 0xA1, (byte) 0x96, (byte) 0x29, (byte) 0x10, (byte) 0x7B, (byte) 0x9A, (byte) 0xC7, (byte) 0xF3, (byte) 0x91, (byte) 0x78, (byte) 0x6F, (byte) 0x9D, (byte) 0x9E, (byte) 0xB2, (byte) 0xB1,
            (byte) 0x32, (byte) 0x75, (byte) 0x19, (byte) 0x3D, (byte) 0xFF, (byte) 0x35, (byte) 0x8A, (byte) 0x7E, (byte) 0x6D, (byte) 0x54, (byte) 0xC6, (byte) 0x80, (byte) 0xC3, (byte) 0xBD, (byte) 0x0D, (byte) 0x57,
            (byte) 0xDF, (byte) 0xF5, (byte) 0x24, (byte) 0xA9, (byte) 0x3E, (byte) 0xA8, (byte) 0x43, (byte) 0xC9, (byte) 0xD7, (byte) 0x79, (byte) 0xD6, (byte) 0xF6, (byte) 0x7C, (byte) 0x22, (byte) 0xB9, (byte) 0x03,
            (byte) 0xE0, (byte) 0x0F, (byte) 0xEC, (byte) 0xDE, (byte) 0x7A, (byte) 0x94, (byte) 0xB0, (byte) 0xBC, (byte) 0xDC, (byte) 0xE8, (byte) 0x28, (byte) 0x50, (byte) 0x4E, (byte) 0x33, (byte) 0x0A, (byte) 0x4A,
            (byte) 0xA7, (byte) 0x97, (byte) 0x60, (byte) 0x73, (byte) 0x1E, (byte) 0x00, (byte) 0x62, (byte) 0x44, (byte) 0x1A, (byte) 0xB8, (byte) 0x38, (byte) 0x82, (byte) 0x64, (byte) 0x9F, (byte) 0x26, (byte) 0x41,
            (byte) 0xAD, (byte) 0x45, (byte) 0x46, (byte) 0x92, (byte) 0x27, (byte) 0x5E, (byte) 0x55, (byte) 0x2F, (byte) 0x8C, (byte) 0xA3, (byte) 0xA5, (byte) 0x7D, (byte) 0x69, (byte) 0xD5, (byte) 0x95, (byte) 0x3B,
            (byte) 0x07, (byte) 0x58, (byte) 0xB3, (byte) 0x40, (byte) 0x86, (byte) 0xAC, (byte) 0x1D, (byte) 0xF7, (byte) 0x30, (byte) 0x37, (byte) 0x6B, (byte) 0xE4, (byte) 0x88, (byte) 0xD9, (byte) 0xE7, (byte) 0x89,
            (byte) 0xE1, (byte) 0x1B, (byte) 0x83, (byte) 0x49, (byte) 0x4C, (byte) 0x3F, (byte) 0xF8, (byte) 0xFE, (byte) 0x8D, (byte) 0x53, (byte) 0xAA, (byte) 0x90, (byte) 0xCA, (byte) 0xD8, (byte) 0x85, (byte) 0x61,
            (byte) 0x20, (byte) 0x71, (byte) 0x67, (byte) 0xA4, (byte) 0x2D, (byte) 0x2B, (byte) 0x09, (byte) 0x5B, (byte) 0xCB, (byte) 0x9B, (byte) 0x25, (byte) 0xD0, (byte) 0xBE, (byte) 0xE5, (byte) 0x6C, (byte) 0x52,
            (byte) 0x59, (byte) 0xA6, (byte) 0x74, (byte) 0xD2, (byte) 0xE6, (byte) 0xF4, (byte) 0xB4, (byte) 0xC0, (byte) 0xD1, (byte) 0x66, (byte) 0xAF, (byte) 0xC2, (byte) 0x39, (byte) 0x4B, (byte) 0x63, (byte) 0xB6
    };

    /**
     * Подстановка для P-преобразования: транспозиция.
     */
    private static final byte[] _tau = {
            0, 8, 16, 24, 32, 40, 48, 56,
            1, 9, 17, 25, 33, 41, 49, 57,
            2, 10, 18, 26, 34, 42, 50, 58,
            3, 11, 19, 27, 35, 43, 51, 59,
            4, 12, 20, 28, 36, 44, 52, 60,
            5, 13, 21, 29, 37, 45, 53, 61,
            6, 14, 22, 30, 38, 46, 54, 62,
            7, 15, 23, 31, 39, 47, 55, 63
    };


    /**
     * Длина блока хэшируемых данных в байтах.
     */
    protected static final int BLOCK_SIZE = 64;

    /**
     * Константы для KeySchedule.
     */
    private static final byte[][] _keyConsts = {
            new byte[]{
                    (byte) 0xb1, (byte) 0x08, (byte) 0x5b, (byte) 0xda, (byte) 0x1e, (byte) 0xca, (byte) 0xda, (byte) 0xe9, (byte) 0xeb, (byte) 0xcb, (byte) 0x2f, (byte) 0x81, (byte) 0xc0, (byte) 0x65, (byte) 0x7c, (byte) 0x1f,
                    (byte) 0x2f, (byte) 0x6a, (byte) 0x76, (byte) 0x43, (byte) 0x2e, (byte) 0x45, (byte) 0xd0, (byte) 0x16, (byte) 0x71, (byte) 0x4e, (byte) 0xb8, (byte) 0x8d, (byte) 0x75, (byte) 0x85, (byte) 0xc4, (byte) 0xfc,
                    (byte) 0x4b, (byte) 0x7c, (byte) 0xe0, (byte) 0x91, (byte) 0x92, (byte) 0x67, (byte) 0x69, (byte) 0x01, (byte) 0xa2, (byte) 0x42, (byte) 0x2a, (byte) 0x08, (byte) 0xa4, (byte) 0x60, (byte) 0xd3, (byte) 0x15,
                    (byte) 0x05, (byte) 0x76, (byte) 0x74, (byte) 0x36, (byte) 0xcc, (byte) 0x74, (byte) 0x4d, (byte) 0x23, (byte) 0xdd, (byte) 0x80, (byte) 0x65, (byte) 0x59, (byte) 0xf2, (byte) 0xa6, (byte) 0x45, (byte) 0x07
            },
            new byte[]{
                    (byte) 0x6f, (byte) 0xa3, (byte) 0xb5, (byte) 0x8a, (byte) 0xa9, (byte) 0x9d, (byte) 0x2f, (byte) 0x1a, (byte) 0x4f, (byte) 0xe3, (byte) 0x9d, (byte) 0x46, (byte) 0x0f, (byte) 0x70, (byte) 0xb5, (byte) 0xd7,
                    (byte) 0xf3, (byte) 0xfe, (byte) 0xea, (byte) 0x72, (byte) 0x0a, (byte) 0x23, (byte) 0x2b, (byte) 0x98, (byte) 0x61, (byte) 0xd5, (byte) 0x5e, (byte) 0x0f, (byte) 0x16, (byte) 0xb5, (byte) 0x01, (byte) 0x31,
                    (byte) 0x9a, (byte) 0xb5, (byte) 0x17, (byte) 0x6b, (byte) 0x12, (byte) 0xd6, (byte) 0x99, (byte) 0x58, (byte) 0x5c, (byte) 0xb5, (byte) 0x61, (byte) 0xc2, (byte) 0xdb, (byte) 0x0a, (byte) 0xa7, (byte) 0xca,
                    (byte) 0x55, (byte) 0xdd, (byte) 0xa2, (byte) 0x1b, (byte) 0xd7, (byte) 0xcb, (byte) 0xcd, (byte) 0x56, (byte) 0xe6, (byte) 0x79, (byte) 0x04, (byte) 0x70, (byte) 0x21, (byte) 0xb1, (byte) 0x9b, (byte) 0xb7
            },
            new byte[]{
                    (byte) 0xf5, (byte) 0x74, (byte) 0xdc, (byte) 0xac, (byte) 0x2b, (byte) 0xce, (byte) 0x2f, (byte) 0xc7, (byte) 0x0a, (byte) 0x39, (byte) 0xfc, (byte) 0x28, (byte) 0x6a, (byte) 0x3d, (byte) 0x84, (byte) 0x35,
                    (byte) 0x06, (byte) 0xf1, (byte) 0x5e, (byte) 0x5f, (byte) 0x52, (byte) 0x9c, (byte) 0x1f, (byte) 0x8b, (byte) 0xf2, (byte) 0xea, (byte) 0x75, (byte) 0x14, (byte) 0xb1, (byte) 0x29, (byte) 0x7b, (byte) 0x7b,
                    (byte) 0xd3, (byte) 0xe2, (byte) 0x0f, (byte) 0xe4, (byte) 0x90, (byte) 0x35, (byte) 0x9e, (byte) 0xb1, (byte) 0xc1, (byte) 0xc9, (byte) 0x3a, (byte) 0x37, (byte) 0x60, (byte) 0x62, (byte) 0xdb, (byte) 0x09,
                    (byte) 0xc2, (byte) 0xb6, (byte) 0xf4, (byte) 0x43, (byte) 0x86, (byte) 0x7a, (byte) 0xdb, (byte) 0x31, (byte) 0x99, (byte) 0x1e, (byte) 0x96, (byte) 0xf5, (byte) 0x0a, (byte) 0xba, (byte) 0x0a, (byte) 0xb2
            },
            new byte[]{
                    (byte) 0xef, (byte) 0x1f, (byte) 0xdf, (byte) 0xb3, (byte) 0xe8, (byte) 0x15, (byte) 0x66, (byte) 0xd2, (byte) 0xf9, (byte) 0x48, (byte) 0xe1, (byte) 0xa0, (byte) 0x5d, (byte) 0x71, (byte) 0xe4, (byte) 0xdd,
                    (byte) 0x48, (byte) 0x8e, (byte) 0x85, (byte) 0x7e, (byte) 0x33, (byte) 0x5c, (byte) 0x3c, (byte) 0x7d, (byte) 0x9d, (byte) 0x72, (byte) 0x1c, (byte) 0xad, (byte) 0x68, (byte) 0x5e, (byte) 0x35, (byte) 0x3f,
                    (byte) 0xa9, (byte) 0xd7, (byte) 0x2c, (byte) 0x82, (byte) 0xed, (byte) 0x03, (byte) 0xd6, (byte) 0x75, (byte) 0xd8, (byte) 0xb7, (byte) 0x13, (byte) 0x33, (byte) 0x93, (byte) 0x52, (byte) 0x03, (byte) 0xbe,
                    (byte) 0x34, (byte) 0x53, (byte) 0xea, (byte) 0xa1, (byte) 0x93, (byte) 0xe8, (byte) 0x37, (byte) 0xf1, (byte) 0x22, (byte) 0x0c, (byte) 0xbe, (byte) 0xbc, (byte) 0x84, (byte) 0xe3, (byte) 0xd1, (byte) 0x2e
            },
            new byte[]{
                    (byte) 0x4b, (byte) 0xea, (byte) 0x6b, (byte) 0xac, (byte) 0xad, (byte) 0x47, (byte) 0x47, (byte) 0x99, (byte) 0x9a, (byte) 0x3f, (byte) 0x41, (byte) 0x0c, (byte) 0x6c, (byte) 0xa9, (byte) 0x23, (byte) 0x63,
                    (byte) 0x7f, (byte) 0x15, (byte) 0x1c, (byte) 0x1f, (byte) 0x16, (byte) 0x86, (byte) 0x10, (byte) 0x4a, (byte) 0x35, (byte) 0x9e, (byte) 0x35, (byte) 0xd7, (byte) 0x80, (byte) 0x0f, (byte) 0xff, (byte) 0xbd,
                    (byte) 0xbf, (byte) 0xcd, (byte) 0x17, (byte) 0x47, (byte) 0x25, (byte) 0x3a, (byte) 0xf5, (byte) 0xa3, (byte) 0xdf, (byte) 0xff, (byte) 0x00, (byte) 0xb7, (byte) 0x23, (byte) 0x27, (byte) 0x1a, (byte) 0x16,
                    (byte) 0x7a, (byte) 0x56, (byte) 0xa2, (byte) 0x7e, (byte) 0xa9, (byte) 0xea, (byte) 0x63, (byte) 0xf5, (byte) 0x60, (byte) 0x17, (byte) 0x58, (byte) 0xfd, (byte) 0x7c, (byte) 0x6c, (byte) 0xfe, (byte) 0x57
            },
            new byte[]{
                    (byte) 0xae, (byte) 0x4f, (byte) 0xae, (byte) 0xae, (byte) 0x1d, (byte) 0x3a, (byte) 0xd3, (byte) 0xd9, (byte) 0x6f, (byte) 0xa4, (byte) 0xc3, (byte) 0x3b, (byte) 0x7a, (byte) 0x30, (byte) 0x39, (byte) 0xc0,
                    (byte) 0x2d, (byte) 0x66, (byte) 0xc4, (byte) 0xf9, (byte) 0x51, (byte) 0x42, (byte) 0xa4, (byte) 0x6c, (byte) 0x18, (byte) 0x7f, (byte) 0x9a, (byte) 0xb4, (byte) 0x9a, (byte) 0xf0, (byte) 0x8e, (byte) 0xc6,
                    (byte) 0xcf, (byte) 0xfa, (byte) 0xa6, (byte) 0xb7, (byte) 0x1c, (byte) 0x9a, (byte) 0xb7, (byte) 0xb4, (byte) 0x0a, (byte) 0xf2, (byte) 0x1f, (byte) 0x66, (byte) 0xc2, (byte) 0xbe, (byte) 0xc6, (byte) 0xb6,
                    (byte) 0xbf, (byte) 0x71, (byte) 0xc5, (byte) 0x72, (byte) 0x36, (byte) 0x90, (byte) 0x4f, (byte) 0x35, (byte) 0xfa, (byte) 0x68, (byte) 0x40, (byte) 0x7a, (byte) 0x46, (byte) 0x64, (byte) 0x7d, (byte) 0x6e
            },
            new byte[]{
                    (byte) 0xf4, (byte) 0xc7, (byte) 0x0e, (byte) 0x16, (byte) 0xee, (byte) 0xaa, (byte) 0xc5, (byte) 0xec, (byte) 0x51, (byte) 0xac, (byte) 0x86, (byte) 0xfe, (byte) 0xbf, (byte) 0x24, (byte) 0x09, (byte) 0x54,
                    (byte) 0x39, (byte) 0x9e, (byte) 0xc6, (byte) 0xc7, (byte) 0xe6, (byte) 0xbf, (byte) 0x87, (byte) 0xc9, (byte) 0xd3, (byte) 0x47, (byte) 0x3e, (byte) 0x33, (byte) 0x19, (byte) 0x7a, (byte) 0x93, (byte) 0xc9,
                    (byte) 0x09, (byte) 0x92, (byte) 0xab, (byte) 0xc5, (byte) 0x2d, (byte) 0x82, (byte) 0x2c, (byte) 0x37, (byte) 0x06, (byte) 0x47, (byte) 0x69, (byte) 0x83, (byte) 0x28, (byte) 0x4a, (byte) 0x05, (byte) 0x04,
                    (byte) 0x35, (byte) 0x17, (byte) 0x45, (byte) 0x4c, (byte) 0xa2, (byte) 0x3c, (byte) 0x4a, (byte) 0xf3, (byte) 0x88, (byte) 0x86, (byte) 0x56, (byte) 0x4d, (byte) 0x3a, (byte) 0x14, (byte) 0xd4, (byte) 0x93
            },
            new byte[]{
                    (byte) 0x9b, (byte) 0x1f, (byte) 0x5b, (byte) 0x42, (byte) 0x4d, (byte) 0x93, (byte) 0xc9, (byte) 0xa7, (byte) 0x03, (byte) 0xe7, (byte) 0xaa, (byte) 0x02, (byte) 0x0c, (byte) 0x6e, (byte) 0x41, (byte) 0x41,
                    (byte) 0x4e, (byte) 0xb7, (byte) 0xf8, (byte) 0x71, (byte) 0x9c, (byte) 0x36, (byte) 0xde, (byte) 0x1e, (byte) 0x89, (byte) 0xb4, (byte) 0x44, (byte) 0x3b, (byte) 0x4d, (byte) 0xdb, (byte) 0xc4, (byte) 0x9a,
                    (byte) 0xf4, (byte) 0x89, (byte) 0x2b, (byte) 0xcb, (byte) 0x92, (byte) 0x9b, (byte) 0x06, (byte) 0x90, (byte) 0x69, (byte) 0xd1, (byte) 0x8d, (byte) 0x2b, (byte) 0xd1, (byte) 0xa5, (byte) 0xc4, (byte) 0x2f,
                    (byte) 0x36, (byte) 0xac, (byte) 0xc2, (byte) 0x35, (byte) 0x59, (byte) 0x51, (byte) 0xa8, (byte) 0xd9, (byte) 0xa4, (byte) 0x7f, (byte) 0x0d, (byte) 0xd4, (byte) 0xbf, (byte) 0x02, (byte) 0xe7, (byte) 0x1e
            },
            new byte[]{
                    (byte) 0x37, (byte) 0x8f, (byte) 0x5a, (byte) 0x54, (byte) 0x16, (byte) 0x31, (byte) 0x22, (byte) 0x9b, (byte) 0x94, (byte) 0x4c, (byte) 0x9a, (byte) 0xd8, (byte) 0xec, (byte) 0x16, (byte) 0x5f, (byte) 0xde,
                    (byte) 0x3a, (byte) 0x7d, (byte) 0x3a, (byte) 0x1b, (byte) 0x25, (byte) 0x89, (byte) 0x42, (byte) 0x24, (byte) 0x3c, (byte) 0xd9, (byte) 0x55, (byte) 0xb7, (byte) 0xe0, (byte) 0x0d, (byte) 0x09, (byte) 0x84,
                    (byte) 0x80, (byte) 0x0a, (byte) 0x44, (byte) 0x0b, (byte) 0xdb, (byte) 0xb2, (byte) 0xce, (byte) 0xb1, (byte) 0x7b, (byte) 0x2b, (byte) 0x8a, (byte) 0x9a, (byte) 0xa6, (byte) 0x07, (byte) 0x9c, (byte) 0x54,
                    (byte) 0x0e, (byte) 0x38, (byte) 0xdc, (byte) 0x92, (byte) 0xcb, (byte) 0x1f, (byte) 0x2a, (byte) 0x60, (byte) 0x72, (byte) 0x61, (byte) 0x44, (byte) 0x51, (byte) 0x83, (byte) 0x23, (byte) 0x5a, (byte) 0xdb
            },
            new byte[]{
                    (byte) 0xab, (byte) 0xbe, (byte) 0xde, (byte) 0xa6, (byte) 0x80, (byte) 0x05, (byte) 0x6f, (byte) 0x52, (byte) 0x38, (byte) 0x2a, (byte) 0xe5, (byte) 0x48, (byte) 0xb2, (byte) 0xe4, (byte) 0xf3, (byte) 0xf3,
                    (byte) 0x89, (byte) 0x41, (byte) 0xe7, (byte) 0x1c, (byte) 0xff, (byte) 0x8a, (byte) 0x78, (byte) 0xdb, (byte) 0x1f, (byte) 0xff, (byte) 0xe1, (byte) 0x8a, (byte) 0x1b, (byte) 0x33, (byte) 0x61, (byte) 0x03,
                    (byte) 0x9f, (byte) 0xe7, (byte) 0x67, (byte) 0x02, (byte) 0xaf, (byte) 0x69, (byte) 0x33, (byte) 0x4b, (byte) 0x7a, (byte) 0x1e, (byte) 0x6c, (byte) 0x30, (byte) 0x3b, (byte) 0x76, (byte) 0x52, (byte) 0xf4,
                    (byte) 0x36, (byte) 0x98, (byte) 0xfa, (byte) 0xd1, (byte) 0x15, (byte) 0x3b, (byte) 0xb6, (byte) 0xc3, (byte) 0x74, (byte) 0xb4, (byte) 0xc7, (byte) 0xfb, (byte) 0x98, (byte) 0x45, (byte) 0x9c, (byte) 0xed
            },
            new byte[]{
                    (byte) 0x7b, (byte) 0xcd, (byte) 0x9e, (byte) 0xd0, (byte) 0xef, (byte) 0xc8, (byte) 0x89, (byte) 0xfb, (byte) 0x30, (byte) 0x02, (byte) 0xc6, (byte) 0xcd, (byte) 0x63, (byte) 0x5a, (byte) 0xfe, (byte) 0x94,
                    (byte) 0xd8, (byte) 0xfa, (byte) 0x6b, (byte) 0xbb, (byte) 0xeb, (byte) 0xab, (byte) 0x07, (byte) 0x61, (byte) 0x20, (byte) 0x01, (byte) 0x80, (byte) 0x21, (byte) 0x14, (byte) 0x84, (byte) 0x66, (byte) 0x79,
                    (byte) 0x8a, (byte) 0x1d, (byte) 0x71, (byte) 0xef, (byte) 0xea, (byte) 0x48, (byte) 0xb9, (byte) 0xca, (byte) 0xef, (byte) 0xba, (byte) 0xcd, (byte) 0x1d, (byte) 0x7d, (byte) 0x47, (byte) 0x6e, (byte) 0x98,
                    (byte) 0xde, (byte) 0xa2, (byte) 0x59, (byte) 0x4a, (byte) 0xc0, (byte) 0x6f, (byte) 0xd8, (byte) 0x5d, (byte) 0x6b, (byte) 0xca, (byte) 0xa4, (byte) 0xcd, (byte) 0x81, (byte) 0xf3, (byte) 0x2d, (byte) 0x1b
            },
            new byte[]{
                    (byte) 0x37, (byte) 0x8e, (byte) 0xe7, (byte) 0x67, (byte) 0xf1, (byte) 0x16, (byte) 0x31, (byte) 0xba, (byte) 0xd2, (byte) 0x13, (byte) 0x80, (byte) 0xb0, (byte) 0x04, (byte) 0x49, (byte) 0xb1, (byte) 0x7a,
                    (byte) 0xcd, (byte) 0xa4, (byte) 0x3c, (byte) 0x32, (byte) 0xbc, (byte) 0xdf, (byte) 0x1d, (byte) 0x77, (byte) 0xf8, (byte) 0x20, (byte) 0x12, (byte) 0xd4, (byte) 0x30, (byte) 0x21, (byte) 0x9f, (byte) 0x9b,
                    (byte) 0x5d, (byte) 0x80, (byte) 0xef, (byte) 0x9d, (byte) 0x18, (byte) 0x91, (byte) 0xcc, (byte) 0x86, (byte) 0xe7, (byte) 0x1d, (byte) 0xa4, (byte) 0xaa, (byte) 0x88, (byte) 0xe1, (byte) 0x28, (byte) 0x52,
                    (byte) 0xfa, (byte) 0xf4, (byte) 0x17, (byte) 0xd5, (byte) 0xd9, (byte) 0xb2, (byte) 0x1b, (byte) 0x99, (byte) 0x48, (byte) 0xbc, (byte) 0x92, (byte) 0x4a, (byte) 0xf1, (byte) 0x1b, (byte) 0xd7, (byte) 0x20
            }
    };


    protected final byte[] _iv;
    protected byte[] _n;
    protected byte[] _sigma;

    protected StreebogJava() {
        _iv = new byte[BLOCK_SIZE];
        _n = new byte[BLOCK_SIZE];
        _sigma = new byte[BLOCK_SIZE];
    }

    protected byte[] hashValue;

    protected int hashSizeValue;

    protected abstract void initialize();

    protected abstract byte[] hashFinal();

    /**
     * Формирование хэш-значения.
     */
    protected void hashCore(byte[] array, int ibStart, int cbSize) {
        byte[] message = new byte[cbSize];
        System.arraycopy(array, ibStart, message, 0, cbSize);
        initialize();

        byte[] paddedMes = new byte[BLOCK_SIZE];
        int len = message.length * 8;
        byte[] hash = new byte[BLOCK_SIZE];
        System.arraycopy(_iv, 0, hash, 0, BLOCK_SIZE);
        byte[] n0 = {
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
        };
        byte[] n512 = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(BLOCK_SIZE * 8).array();
        int inc = 0;
        while (len >= BLOCK_SIZE * 8) {
            inc++;
            byte[] tempMes = new byte[BLOCK_SIZE];
            System.arraycopy(message, message.length - inc * BLOCK_SIZE, tempMes, 0, BLOCK_SIZE);
            hash = compress(_n, hash, tempMes);
            _n = addModulo512(_n, n512);
            _sigma = addModulo512(_sigma, tempMes);
            len -= BLOCK_SIZE * 8;
        }
        byte[] message1 = new byte[message.length - inc * BLOCK_SIZE];
        System.arraycopy(message, 0, message1, 0, message.length - inc * BLOCK_SIZE);
        if (message1.length < BLOCK_SIZE) {
            // дополнение сообщения до 512 бит.
            for (int i = 0; i < (BLOCK_SIZE - message1.length - 1); i++) {
                paddedMes[i] = 0;
            }
            paddedMes[BLOCK_SIZE - message1.length - 1] = 0x01;
            System.arraycopy(message1, 0, paddedMes, BLOCK_SIZE - message1.length, message1.length);
        }
        hash = compress(_n, hash, paddedMes);
        byte[] mesLen = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(message1.length * 8).array();
        _n = addModulo512(_n, mesLen);
        _sigma = addModulo512(_sigma, paddedMes);
        hash = compress(n0, hash, _n);
        hashValue = compress(n0, hash, _sigma);
    }

    private static byte[] addModulo512(byte[] a, byte[] b) {
        byte[] temp = new byte[BLOCK_SIZE];
        int t = 0;
        byte[] tempA = new byte[BLOCK_SIZE];
        byte[] tempB = new byte[BLOCK_SIZE];
        System.arraycopy(a, 0, tempA, BLOCK_SIZE - a.length, a.length);
        System.arraycopy(b, 0, tempB, BLOCK_SIZE - b.length, b.length);
        for (int i = 63; i >= 0; i--) {
            t = UnsignedBytes.toInt(tempA[i]) + UnsignedBytes.toInt(tempB[i]) + (t >> 8);
            temp[i] = (byte) (t & 0xFF);
        }
        return temp;
    }

    /**
     * X - преобразование.
     */
    private static byte[] addXor512(byte[] a, byte[] b) {
        byte[] c = new byte[BLOCK_SIZE];
        for (int i = 0; i < BLOCK_SIZE; i++)
            c[i] = (byte) (UnsignedBytes.toInt(a[i]) ^ UnsignedBytes.toInt(b[i]));
        return c;
    }

    /**
     * S-преобразование.
     */
    private static byte[] s(byte[] state) {
        byte[] result = new byte[BLOCK_SIZE];
        for (int i = 0; i < BLOCK_SIZE; i++) {
            System.out.println("byte "+state[i]);
            System.out.println("int " +UnsignedBytes.toInt(state[i]));
            result[i] = _sbox[UnsignedBytes.toInt(state[i])];
        }
        return result;
    }


    /**
     * P - преобразование.
     */
    private static byte[] p(byte[] state) {
        byte[] result = new byte[BLOCK_SIZE];
        for (int i = 0; i < BLOCK_SIZE; i++) {
            result[i] = state[_tau[i]];
        }
        return result;
    }

    /**
     * L - преобразование. Умножение входного вектора на бинарную матрицу.
     */
    private static byte[] l(byte[] state) {
        byte[] result = new byte[BLOCK_SIZE];
        for (int i = 0; i < 8; i++) {
            UnsignedLong t = UnsignedLong.ZERO;
            byte[] tempArray = new byte[8];
            System.arraycopy(state, i * 8, tempArray, 0, 8);
            BitArray tempBits1 = new BitArray(tempArray.length * 8, tempArray);
            boolean[] tempBits = new boolean[BLOCK_SIZE];
            boolean[] tempBits1Bool = tempBits1.toBooleanArray();
            System.arraycopy(tempBits1Bool, 0, tempBits, 0, tempBits.length);
            for (int j = 0; j < BLOCK_SIZE; j++) {
                if (tempBits[j]) {
                    t = UnsignedLong.valueOf(t.bigIntegerValue().xor(_mixMatrix[j].bigIntegerValue()));
                }
            }
            byte[] tBytes = ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN).putLong(t.longValue()).array();
            System.arraycopy(tBytes, 0, result, i * 8, 8);
        }
        return result;
    }

    /**
     * Формирование временного ключа.
     */
    private static byte[] keySchedule(byte[] key, int i) {
        key = addXor512(key, _keyConsts[i]);
        key = s(key);
        key = p(key);
        key = l(key);
        return key;
    }

    private static byte[] e(byte[] k, byte[] m) {
        byte[] state = addXor512(k, m);
        for (int i = 0; i < 12; i++) {
            state = s(state);
            state = p(state);
            state = l(state);
            k = keySchedule(k, i);
            state = addXor512(state, k);
        }
        return state;
    }

    /**
     * Функция сжатия.
     */
    private static byte[] compress(byte[] n, byte[] hash, byte[] m) {
        byte[] K = addXor512(hash, n);
        K = s(K);
        K = p(K);
        K = l(K);
        byte[] t = e(K, m);
        t = addXor512(t, hash);
        return addXor512(t, m);
    }

    /**
     * Вычисляет хэш от buffer
     */
    public byte[] computeHash(byte[] buffer) {
        if (buffer == null) {
            throw new IllegalArgumentException("buffer is null");
        }

        hashCore(buffer, 0, buffer.length);
        hashValue = hashFinal();
        byte[] hashValueCopy = Arrays.copyOf(hashValue, hashValue.length);
        initialize();
        return hashValueCopy;
    }
}


