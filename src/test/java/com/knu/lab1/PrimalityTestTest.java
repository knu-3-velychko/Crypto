package com.knu.lab1;

import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PrimalityTestTest {

    private static final RandomGenerator generator = new RandomGenerator(System.currentTimeMillis());

    private static final List<Integer> primes = List.of(
            7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
            43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
            101, 103, 107, 109, 113, 127, 131, 137, 139, 149,
            151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199,
            271, 331, 397, 547, 631, 919, 1657, 1801, 1951, 2269, 2437,
            2791, 3169, 3571, 4219, 4447, 5167, 5419, 6211, 7057, 7351,
            8269, 9241, 10267, 11719, 12097, 13267, 13669, 16651, 19441, 19927, 22447, 23497
    );

    private static final List<BigInteger> composites = new ArrayList<>();

    static {
        BigInteger lowerBound = BigInteger.ONE, upperBound = BigInteger.valueOf(Short.MAX_VALUE);
        for (Integer prime : primes) {
            composites.add(generator.getBigInteger(lowerBound, upperBound).multiply(BigInteger.valueOf(prime)));
        }
    }

    @Test
    public void primesFermatTest() {
        PrimalityTest primalityTest = new FermatTest(PrimalityTestTest.generator, 5);
        for (Integer prime : primes) {
            assertTrue(primalityTest.testPrimality(BigInteger.valueOf(prime)));
        }
    }

    @Test
    public void compositesFermatTest() {
        PrimalityTest primalityTest = new FermatTest(PrimalityTestTest.generator, 5);
        for (BigInteger composite : composites) {
            assertFalse(primalityTest.testPrimality(composite));
        }
    }

    @Test
    public void primesMillerRabinTest() {
        PrimalityTest primalityTest = new MillerRabinTest(PrimalityTestTest.generator, 5);
        for (Integer prime : primes) {
            assertTrue(primalityTest.testPrimality(BigInteger.valueOf(prime)));
        }
    }

    @Test
    public void compositesMillerRabinTest() {
        PrimalityTest primalityTest = new MillerRabinTest(PrimalityTestTest.generator, 5);
        for (BigInteger composite : composites) {
            assertFalse(primalityTest.testPrimality(composite));
        }
    }
}