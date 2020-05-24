package com.knu.lab3;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ElGamalAlgorithmTest {
    private final static ElGamalAlgorithm algorithm = new ElGamalAlgorithm(new SHA2());

    @Test
    public void encodeString() {
        String str1 = "First string to encode one";
        String str2 = "Second string to encode one";


        String hash1 = algorithm.encode(str1);
        assertEquals(64, hash1.length());
        assertEquals(hash1, algorithm.encode(str1));


        String hash2 = algorithm.encode(str2);

        assertNotEquals(hash1, hash2);
    }

    @Test
    public void encodeFile() throws IOException {
        String inputPath = "input.txt";
        String outputPath = "output.txt";

        algorithm.encode(inputPath, outputPath);

        FileReader readerInput = new FileReader(inputPath);
        FileReader readerOutput = new FileReader(outputPath);

        BufferedReader bufferedReaderInput = new BufferedReader(readerInput);
        BufferedReader bufferedReaderOutput = new BufferedReader(readerOutput);


        String line = bufferedReaderInput.readLine();
        String encodedLine = bufferedReaderOutput.readLine();

        assertEquals(64, encodedLine.length());
        assertEquals(encodedLine, algorithm.encode(line));

        readerInput.close();
        readerOutput.close();
    }
}