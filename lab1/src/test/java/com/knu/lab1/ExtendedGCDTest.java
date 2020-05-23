package com.knu.lab1;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class ExtendedGCDTest {
    private static final RandomGenerator generator = new RandomGenerator(System.currentTimeMillis());
    private static final int lowerBound = 1, upperBound = Short.MAX_VALUE;

    private int gcd(int value1, int value2) {
        for (int i = Math.min(value1, value2); i >= 1; --i) {
            if (value1 % i == 0 && value2 % i == 0) {
                return i;
            }
        }
        return 0;
    }

    @Test
    public void computeInteger() {
        int value1 = generator.getInteger(lowerBound, upperBound);
        int value2 = generator.getInteger(lowerBound, upperBound);

        int expectedResult = gcd(value1, value2);
        BigInteger result = ExtendedGCD.compute(BigInteger.valueOf(value1), BigInteger.valueOf(value2)).getGcd();

        assertEquals(BigInteger.valueOf(expectedResult), result);
    }

    @Test
    public void computeBigInteger() {
        BigInteger value1 = generator.getBigInteger(BigInteger.valueOf(lowerBound), BigInteger.valueOf(upperBound));
        BigInteger value2 = generator.getBigInteger(BigInteger.valueOf(lowerBound), BigInteger.valueOf(upperBound));

        ExtendedGCD.GCDResult result = ExtendedGCD.compute(value1, value2);
        assertEquals(result.getGcd(),result.getX().multiply(value1).add(result.getY().multiply(value2)));
    }
}