package hash.stribog.java;

/**
 * Хэширование Стрибог с длиной выходного значения 512 бит.
 */
public class Streebog512Java extends StreebogJava {
    public Streebog512Java() {
        hashSizeValue = 512;
    }

    @Override
    public void initialize() {
        for (int i = 0; i < BLOCK_SIZE; i++) {
            _n[i] = 0x00;
            _sigma[i] = 0x00;
            _iv[i] = 0x00;
        }
    }

    @Override
    protected byte[] hashFinal() {
        return hashValue;
    }
}
