package com.knu.lab3;

import org.junit.Test;

import static org.junit.Assert.*;

public class EncryptorTest {

    @Test
    public void encryptDecryptString() throws Exception {
        String expectedString = "Test string to encrypt";
        Encryptor encryptor = new Encryptor(Key.generateKey(8));
        assertEquals(expectedString, encryptor.decryptString(encryptor.encryptString(expectedString)));
    }

    @Test
    public void encryptDecrypt() throws Exception {
        Encryptor encryptor1 = new Encryptor(Key.generateKey(8));
        Encryptor encryptor2 = new Encryptor(Key.generateKey(20));
        byte[] expectedData1 = new byte[]{127};
        byte[] expectedData2 = new byte[]{12, 32};

        assertArrayEquals(expectedData1, encryptor1.decrypt(encryptor1.encrypt(expectedData1)));
        assertArrayEquals(encryptor1.encrypt(expectedData1).getA(), encryptor1.encrypt(expectedData1).getA());
        assertArrayEquals(encryptor1.encrypt(expectedData1).getB(), encryptor1.encrypt(expectedData1).getB());

        assertArrayEquals(expectedData2, encryptor2.decrypt(encryptor2.encrypt(expectedData2)));
        assertArrayEquals(encryptor2.encrypt(expectedData2).getA(), encryptor2.encrypt(expectedData2).getA());
        assertArrayEquals(encryptor2.encrypt(expectedData2).getB(), encryptor2.encrypt(expectedData2).getB());
    }
}