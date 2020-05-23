package com.knu.lab1;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class KaratsubaMultiplicationTest {
    private static final RandomGenerator generator = new RandomGenerator(System.currentTimeMillis());
    private static final BigInteger lowerBound = BigInteger.ONE, upperBound = BigInteger.valueOf(Short.MAX_VALUE);

    @Test
    public void compute() {
        BigInteger value1 = generator.getBigInteger(lowerBound, upperBound);
        BigInteger value2 = generator.getBigInteger(lowerBound, upperBound);

        BigInteger expectedResult = value1.multiply(value2);
        BigInteger result = KaratsubaMultiplication.compute(value1, value2);
        assertEquals(expectedResult, result);

        result = KaratsubaMultiplication.compute(value2, value1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void computeZero() {
        BigInteger value1 = BigInteger.ZERO;
        BigInteger value2 = generator.getBigInteger(lowerBound, upperBound);

        BigInteger result = KaratsubaMultiplication.compute(value1, value2);
        assertEquals(value1, result);
        assertEquals(BigInteger.ZERO, result);

        result = KaratsubaMultiplication.compute(value2, value1);
        assertEquals(value1, result);
        assertEquals(BigInteger.ZERO, result);
    }

    @Test
    public void computeOne() {
        BigInteger value1 = BigInteger.ONE;
        BigInteger value2 = generator.getBigInteger(lowerBound, upperBound);

        BigInteger result = KaratsubaMultiplication.compute(value1, value2);
        assertEquals(value2, result);

        result = KaratsubaMultiplication.compute(value2, value1);
        assertEquals(value2, result);
    }
}