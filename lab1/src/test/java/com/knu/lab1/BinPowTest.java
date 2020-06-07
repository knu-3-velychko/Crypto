package com.knu.lab1;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.Assert.*;


public class BinPowTest {
    private final static RandomGenerator generator = new RandomGenerator(System.currentTimeMillis());
    private final static Random random = new Random();
    private final static int length = 1024;

    private int pow(int value, int power, int mod) {
        int result = 1;
        for (int i = 0; i < power; i++) {
            result = (result * value) % mod;
        }
        return result;
    }

    @Test
    public void computeInt() {
        int lowerBound = 1, upperBound = Short.MAX_VALUE;

        int value = generator.getInteger(lowerBound, upperBound);
        int power = generator.getInteger(lowerBound, upperBound);
        int mod = generator.getInteger(lowerBound, upperBound);

        int expectedResult = pow(value, power, mod);

        BigInteger result = BinPow.compute(BigInteger.valueOf(value), BigInteger.valueOf(power), BigInteger.valueOf(mod));
        assertEquals(BigInteger.valueOf(expectedResult), result);
    }

    @Test
    public void computeBigInteger() {
        BigInteger value = new BigInteger(length, random);
        BigInteger power = new BigInteger(length, random);
        BigInteger mod = new BigInteger(length, random);

        BigInteger result = BinPow.compute(value, power, mod);

        assertEquals(result.compareTo(mod), -1);
    }

    @Test
    public void valueZero() {
        BigInteger value = BigInteger.ZERO;
        BigInteger power = new BigInteger(length, random);
        BigInteger mod = new BigInteger(length, random);

        BigInteger result = BinPow.compute(value, power, mod);

        assertEquals(result, BigInteger.ZERO);
    }

    @Test
    public void powZero() {
        BigInteger value = new BigInteger(length, random);
        BigInteger power = BigInteger.ZERO;
        BigInteger mod = new BigInteger(length, random);

        BigInteger result = BinPow.compute(value, power, mod);

        assertEquals(result, BigInteger.ONE);
    }

    @Test
    public void valueOne() {
        BigInteger value = BigInteger.ONE;
        BigInteger power = new BigInteger(length, random);
        BigInteger mod = new BigInteger(length, random);

        BigInteger result = BinPow.compute(value, power, mod);

        assertEquals(result, BigInteger.ONE);
    }

    @Test
    public void powOne() {
        BigInteger value = new BigInteger(length, random);
        BigInteger power = BigInteger.ONE;
        BigInteger mod = new BigInteger(length, random);

        BigInteger result = BinPow.compute(value, power, mod);

        assertEquals(result, value.mod(mod));
    }

    @Test
    public void nullMod() {
        BigInteger value = new BigInteger(length, random);
        BigInteger power = BigInteger.ONE;

        BigInteger result = BinPow.compute(value, power, null);

        assertEquals(result, value);
    }
}