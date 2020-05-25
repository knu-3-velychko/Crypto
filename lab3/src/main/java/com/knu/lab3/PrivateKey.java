package com.knu.lab3;

import java.math.BigInteger;

public class PrivateKey {
    private final BigInteger key;

    public PrivateKey(BigInteger x){
        this.key = x;
    }

    public BigInteger getKey() {
        return new BigInteger(String.valueOf(key));
    }
}
