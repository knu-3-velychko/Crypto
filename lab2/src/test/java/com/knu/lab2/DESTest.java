package com.knu.lab2;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import static org.junit.Assert.*;

public class DESTest {

    @Test
    public void encryptDecrypt() {

        String password = "passwrd";
        String str = "randomTx";

        byte[] array = new byte[8];
        new Random().nextBytes(array);
        String vectorInit = new String(array, StandardCharsets.UTF_8);

        assertEquals(DES.encrypt(str, password, vectorInit), DES.encrypt(str, password, vectorInit));
        String encryptedString = DES.encrypt(str, password, vectorInit);
        assertEquals(str, DES.decrypt(encryptedString, password, vectorInit));
    }
}