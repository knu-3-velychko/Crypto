package com.knu.lab1;

import java.math.BigInteger;

public class FermatTest implements PrimalityTest{
    private RandomGenerator generator;
    private int loopNumber;

    public FermatTest(RandomGenerator generator, int loopNumber) {
        this.generator = generator;
        this.loopNumber = loopNumber;
    }

    public boolean testPrimality(BigInteger value) {
        BigInteger valueSubtracted = value.subtract(BigInteger.ONE);
        for (int i = 0; i < loopNumber; i++) {
            BigInteger randomNumber = generator.getBigInteger(BigInteger.ONE, valueSubtracted);
            BigInteger testingNumber = BinPow.compute(randomNumber, valueSubtracted, value);
            if (testingNumber.compareTo(BigInteger.ONE) != 0) {
                return false;
            }
        }
        return true;
    }
}
