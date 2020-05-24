package com.knu.lab1;

import java.math.BigInteger;

public class MontgomeryMultiplication {
    private BigInteger mod, modReversed, r;
    private int shift;

    public MontgomeryMultiplication(BigInteger mod) {
        this.mod = mod;
        this.shift = mod.bitLength();
        this.r = BigInteger.ONE.shiftLeft(shift);
        this.modReversed = ExtendedGCD.compute(mod, r).getX().negate();
    }

    public BigInteger represent(BigInteger value) {
        return value.shiftLeft(shift).mod(mod);
    }

    public BigInteger multiply(BigInteger value1Repr, BigInteger value2Repr) {
        BigInteger t = value1Repr.multiply(value2Repr);
        BigInteger u = t.add(t.multiply(this.modReversed).and(this.r.subtract(BigInteger.ONE)).multiply(this.mod)).shiftRight(this.shift);
        if (u.compareTo(this.mod) > 0) {
            u = u.subtract(this.mod);
        }
        return u;
    }

    public BigInteger pow(BigInteger value, BigInteger p) {
        BigInteger valueRepr = represent(value);
        BigInteger resultRepr = represent(BigInteger.ONE);
        while (!p.equals(BigInteger.ZERO)) {
            if (p.and(BigInteger.ONE).equals(BigInteger.ONE)) {
                resultRepr = multiply(resultRepr, valueRepr);
            }
            valueRepr = multiply(valueRepr, valueRepr);
            p = p.shiftRight(1);
        }
        return multiply(resultRepr, BigInteger.ONE);
    }
}
