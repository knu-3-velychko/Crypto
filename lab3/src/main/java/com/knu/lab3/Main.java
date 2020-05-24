package com.knu.lab3;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ElGamalAlgorithm algorithm = new ElGamalAlgorithm(new SHA2());
        algorithm.encode("input.txt", "output.txt");
    }
}
