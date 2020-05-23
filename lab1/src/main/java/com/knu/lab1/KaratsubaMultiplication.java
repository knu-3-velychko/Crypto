package com.knu.lab1;

import java.math.BigInteger;

public class KaratsubaMultiplication {
    static BigInteger compute(BigInteger value1, BigInteger value2) {
        int length = Math.max(value1.bitLength(), value2.bitLength());
        if (length < 2) {
            return value1.multiply(value2);
        }
        int shift = length / 2;
        BigInteger x = BigInteger.ONE.shiftLeft(shift);
        BigInteger a = value1.shiftRight(shift);
        BigInteger b = value1.and(x.subtract(BigInteger.ONE));
        BigInteger c = value2.shiftRight(shift);
        BigInteger d = value2.and(x.subtract(BigInteger.ONE));
        BigInteger ac = KaratsubaMultiplication.compute(a, c);
        BigInteger bd = KaratsubaMultiplication.compute(b, d);
        BigInteger xCoefficient = KaratsubaMultiplication.compute(a.add(b), c.add(d)).subtract(ac).subtract(bd);
        return ac.shiftLeft(shift * 2).add(xCoefficient.shiftLeft(shift)).add(bd);
    }
}
