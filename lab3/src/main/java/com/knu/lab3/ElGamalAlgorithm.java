package com.knu.lab3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class ElGamalAlgorithm {
    private final HashFunction hashFunction;

    public ElGamalAlgorithm(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
    }

    public String encode(String data) {
        byte[] bytes = data.getBytes();
        int[] extendedData = processBytes(bytes);
        int[] encoded = hashFunction.encode(extendedData);
        return Util.intArrayToHexString(encoded);
    }

    public void encode(String inputPath, String outputPath) throws IOException {
        FileReader reader = new FileReader(inputPath);
        BufferedReader bufferedReader = new BufferedReader(reader);
        FileWriter writer = new FileWriter(outputPath);

        bufferedReader.lines().forEach(
                line -> {
                    try {
                        writer.write(encode(line));
                    } catch (IOException exception) {
                        Logger.getLogger(ElGamalAlgorithm.class.getName()).warning(exception.getMessage());
                    }
                }

        );
        reader.close();
        writer.close();
    }

    private int[] processBytes(byte[] data) {
        byte[] extendedData = new byte[(data.length + 1 + 8 + 63) / 64 * 64];
        System.arraycopy(data, 0, extendedData, 0, data.length);
        extendedData[data.length] = (byte) 0b10000000;
        for (int i = data.length + 1; i < extendedData.length - 8; i++) {
            extendedData[i] = 0;
        }
        byte[] encodedLength = Util.longToByteArray((long) data.length * 8);
        System.arraycopy(encodedLength, 0, extendedData, extendedData.length - 8, encodedLength.length);
        return Util.byteArrayToIntArray(extendedData);
    }
}
