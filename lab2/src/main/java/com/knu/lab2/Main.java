package com.knu.lab2;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String password = "passwrd";
        String str = "randomTx";

        byte[] array = new byte[8];
        new Random().nextBytes(array);
        String vectorInit = new String(array, StandardCharsets.UTF_8);

        DES d = new DES();
        String p = d.encrypt(str, password, vectorInit);
        System.out.println("Encrypt: " + p);
        str = d.decrypt(p, password, vectorInit);
        System.out.println("Decrypt: " + str);
    }
}
