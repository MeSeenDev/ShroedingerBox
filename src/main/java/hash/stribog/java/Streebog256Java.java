package hash.stribog.java;

/**
 * Хэширование Стрибог с длиной выходного значения 256 бит.
 */
public class Streebog256Java extends StreebogJava {

    public Streebog256Java() {
        hashSizeValue = 256;
    }

    @Override
    public void initialize() {
        for (int i = 0; i < BLOCK_SIZE; i++) {
            _n[i] = 0x00;
            _sigma[i] = 0x00;
            _iv[i] = 0x01;
        }
    }

    @Override
    protected byte[] hashFinal() {
        byte[] result = new byte[32];
        System.arraycopy(hashValue, 0, result, 0, 32);
        return result;
    }
}