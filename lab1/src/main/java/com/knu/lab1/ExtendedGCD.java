package com.knu.lab1;

import java.math.BigInteger;

public class ExtendedGCD {
    public static class GCDResult {
        private BigInteger gcd, x, y;

        public BigInteger getGcd() {
            return gcd;
        }

        public BigInteger getX() {
            return x;
        }

        public BigInteger getY() {
            return y;
        }

        private void setGcd(BigInteger gcd) {
            this.gcd = gcd;
        }

        private void setX(BigInteger x) {
            this.x = x;
        }

        private void setY(BigInteger y) {
            this.y = y;
        }
    }

    static GCDResult compute(BigInteger value1, BigInteger value2) {
        GCDResult result = new GCDResult();
        if (value1.equals(BigInteger.ZERO)) {
            result.setGcd(value2);
            result.setX(BigInteger.ZERO);
            result.setY(BigInteger.ONE);
        } else {
            GCDResult previousResult = compute(value2.mod(value1), value1);
            result.setGcd(previousResult.getGcd());
            result.setX(previousResult.getY().subtract(value2.divide(value1).multiply(previousResult.x)));
            result.setY(previousResult.getX());
        }

        return result;
    }
}
