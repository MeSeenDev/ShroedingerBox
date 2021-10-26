package quests;

import java.math.BigInteger;

public class Factorial {
    public static BigInteger calculate(BigInteger infactorial) {
        BigInteger out = BigInteger.ONE;
        for (int i = 1; i < infactorial.longValue() + 1; i++) {
            out = out.multiply(new BigInteger(String.valueOf(i))).abs();
        }
        return out.abs();
    }

    public static BigInteger reverse(BigInteger factorial) {
        BigInteger out = BigInteger.ONE;
        for (long i = 1; i < factorial.longValue(); i++) {
            factorial = factorial.divide(new BigInteger(String.valueOf(i)));
            out = out.add(BigInteger.ONE);
            if (factorial.compareTo(BigInteger.ZERO) < 0) {
                break;
            }
        }
        return out;
    }

    public static long reverse(long digit) {
        long out = 1L, init = 1L;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            out *= i;
            if (out >= digit) {
                break;
            }
            init++;
        }
        return init;
    }
}
