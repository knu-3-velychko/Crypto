package com.knu.lab1;

import java.math.BigInteger;
import java.util.Random;

public class RandomGenerator {
    private final Random random;

    public RandomGenerator(long seed) {
        this.random = new Random(seed);
    }

    public BigInteger getBigInteger(BigInteger lowerBound, BigInteger upperBound) {
        BigInteger result;
        int i = 0;
        do {
            result = new BigInteger(upperBound.bitLength(), random);
            i++;
            if (i >= 100)
                break;
        } while (result.compareTo(lowerBound) <= 0 || result.compareTo(upperBound) >= 0);
        return result;
    }

    public Integer getInteger(int lowerBound, int upperBound) {
        return random.nextInt(upperBound) + lowerBound;
    }
}
