package com.knu.lab3;

import java.math.BigInteger;

public class PublicKey {
    private final BigInteger keyY, keyG, keyP;

    public PublicKey(BigInteger y, BigInteger g, BigInteger p) {
        this.keyY = y;
        this.keyG = g;
        this.keyP = p;
    }

    public BigInteger getKeyY() {
        return keyY;
    }

    public BigInteger getKeyG() {
        return keyG;
    }

    public BigInteger getKeyP() {
        return keyP;
    }
}
