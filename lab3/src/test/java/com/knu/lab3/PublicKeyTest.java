package com.knu.lab3;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class PublicKeyTest {
    private final BigInteger expectedP1 = new BigInteger("2789328934982349279292358597292040");
    private final BigInteger expectedY1 = new BigInteger("278932893498234927925859729112112134");
    private final BigInteger expectedG1 = new BigInteger("27893289349823492792923585972897854858");

    private final BigInteger expected2 = new BigInteger("0");
    private PublicKey publicKey1, publicKey2;

    @Before
    public void setUp() throws Exception{
        publicKey1 = new PublicKey(expectedY1, expectedG1, expectedP1);
        publicKey2 = new PublicKey(expected2, expected2, expected2);
    }

    @Test
    public void getKeyY() {
        assertEquals(0, expectedY1.compareTo(publicKey1.getKeyY()));
        assertEquals(0, expected2.compareTo(publicKey2.getKeyY()));
    }

    @Test
    public void getKeyG() {
        assertEquals(0, expectedG1.compareTo(publicKey1.getKeyG()));
        assertEquals(0, expected2.compareTo(publicKey2.getKeyG()));
    }

    @Test
    public void getKeyP() {
        assertEquals(0, expectedP1.compareTo(publicKey1.getKeyP()));
        assertEquals(0, expected2.compareTo(publicKey2.getKeyP()));
    }
}