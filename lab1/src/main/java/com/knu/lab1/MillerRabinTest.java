package com.knu.lab1;

import java.math.BigInteger;

public class MillerRabinTest implements PrimalityTest {
    private RandomGenerator generator;
    private int loopNumber;

    public MillerRabinTest(RandomGenerator generator, int loopNumber) {
        this.generator = generator;
        this.loopNumber = loopNumber;
    }

    private boolean isOdd(BigInteger value) {
        return value.and(BigInteger.ONE).equals(BigInteger.ONE);
    }

    @Override
    public boolean testPrimality(BigInteger value) {
        if (!isOdd(value)) {
            return false;
        }

        BigInteger valueSubtracted = value.subtract(BigInteger.ONE);
        BigInteger d = valueSubtracted;
        int s = 0;
        while (!isOdd(d)) {
            s += 1;
            d = d.shiftRight(1);
        }

        for (int i = 0; i < loopNumber; i++) {
            BigInteger a = generator.getBigInteger(BigInteger.ONE, valueSubtracted);
            BigInteger x = BinPow.compute(a, d, value);
            if ((!x.equals(BigInteger.ONE)) && (!x.equals(valueSubtracted))) {
                boolean isAlive = false;
                for (int j = 0; j < s - 1 && !isAlive; j++) {
                    x = x.multiply(x).mod(value);
                    if (x.equals(BigInteger.ONE)) {
                        return false;
                    }
                    if (x.equals(valueSubtracted)) {
                        isAlive = true;
                    }
                }
                if (!isAlive) {
                    return false;
                }
            }
        }

        return true;
    }
}
