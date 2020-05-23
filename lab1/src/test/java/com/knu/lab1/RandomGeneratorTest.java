package com.knu.lab1;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class RandomGeneratorTest {
    private RandomGenerator generator = new RandomGenerator(System.currentTimeMillis());
    private final int lowerBound = 1, upperBound = Short.MAX_VALUE - 1;
    private final BigInteger lowerBoundBigInteger = BigInteger.valueOf(lowerBound), upperBoundBigInteger = BigInteger.valueOf(upperBound);

    @Test

    public void getBigInteger() {
        BigInteger value = generator.getBigInteger(lowerBoundBigInteger, upperBoundBigInteger);
        assertTrue(value.compareTo(lowerBoundBigInteger) >= 0 && value.compareTo(upperBoundBigInteger) <= 0);
    }

    @Test
    public void getInteger() {
        Integer value = generator.getInteger(lowerBound, upperBound);
        assertTrue(value >= lowerBound && value <= upperBound);
    }
}