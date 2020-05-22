package com.knu.lab1;

import java.math.BigInteger;
import java.util.Random;

public class RandomGenerator {
    private Random random;

    public RandomGenerator(long seed) {
        this.random = new Random(seed);
    }

    public BigInteger getBigInteger(BigInteger lowerBound, BigInteger upperBound) {
        BigInteger result;
        do {
            result = new BigInteger(upperBound.bitLength(), random);
        } while (result.compareTo(upperBound) >= 0 || result.compareTo(lowerBound) <= 0);
        return result;
    }

    public Integer getInteger(int lowerBound, int upperBound) {
        return random.nextInt(upperBound) + lowerBound;
    }
}
