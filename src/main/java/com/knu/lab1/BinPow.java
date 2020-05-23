package com.knu.lab1;

import java.math.BigInteger;

public class BinPow {
    static BigInteger compute(BigInteger value, BigInteger exponent, BigInteger mod) {
        BigInteger result = BigInteger.ONE;

        while (!exponent.equals(BigInteger.ZERO)) {
            if (exponent.and(BigInteger.ONE).equals(BigInteger.ONE)) {
                result = result.multiply(value);
                result = modulo(result, mod);
            }
            value = value.multiply(value);
            value = modulo(value, mod);
            exponent = exponent.shiftRight(1);
        }
        return result;
    }

    private static BigInteger modulo(BigInteger value, BigInteger mod) {
        return mod == null ? value : value.mod(mod);
    }
}
