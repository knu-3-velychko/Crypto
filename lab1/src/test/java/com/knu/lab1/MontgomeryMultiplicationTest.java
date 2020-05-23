package com.knu.lab1;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class MontgomeryMultiplicationTest {
    private static final BigInteger lowerBound = BigInteger.ONE, upperBound = BigInteger.valueOf(Short.MAX_VALUE);
    private static final RandomGenerator generator = new RandomGenerator(System.currentTimeMillis());

    private static final BigInteger mod = new BigInteger("170141183460469231731687303715884105727");
    private static final MontgomeryMultiplication montgomeryMultiplication = new MontgomeryMultiplication(mod);

    @Test
    public void multiply() {
        BigInteger value1 = generator.getBigInteger(lowerBound, upperBound);
        BigInteger value2 = generator.getBigInteger(lowerBound, upperBound);
        BigInteger expectedResult = value1.multiply(value2).mod(mod);
        BigInteger value1Repr = montgomeryMultiplication.represent(value1);
        BigInteger value2Repr = montgomeryMultiplication.represent(value2);
        BigInteger resultRepr = montgomeryMultiplication.multiply(value1Repr, value2Repr);
        BigInteger result = montgomeryMultiplication.multiply(resultRepr, BigInteger.ONE);

        assertEquals(expectedResult, result);
    }

    @Test
    public void pow() {
        BigInteger value1 = generator.getBigInteger(lowerBound, upperBound);
        BigInteger value2 = generator.getBigInteger(lowerBound, upperBound);
        BigInteger binPowerResult = BinPow.compute(value1, value2, mod);
        BigInteger montgomeryResult = montgomeryMultiplication.pow(value1, value2);
        assertEquals(binPowerResult, montgomeryResult);
    }
}