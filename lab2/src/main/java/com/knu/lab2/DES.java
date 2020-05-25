package com.knu.lab2;

import sun.security.util.BitArray;

import static com.knu.lab2.Util.*;

public class DES {

    private BitArray[] splitBlock(BitArray E) {
        BitArray[] blocks = new BitArray[8];

        for (int i = 0; i < 8; i++) {
            blocks[i] = new BitArray(6);
            for (int j = 0; j < 6; j++) {
                blocks[i].set(j, E.get(i * 6 + j));
            }
        }
        return blocks;
    }

    private BitArray extendBlock(BitArray R) {
        BitArray E = new BitArray(48);
        for (int i = 0; i < 48; i++) {
            E.set(i, R.get(extensionTable[i]));
        }
        return E;
    }

    private BitArray xor(BitArray a, BitArray b) {
        if (a.length() != b.length()) throw new IllegalArgumentException("input has no same size");

        BitArray result = new BitArray(b.length());
        for (int i = 0; i < a.length(); i++) {
            result.set(i, a.get(i) ^ b.get(i));
        }
        return result;
    }


    private BitArray feistelHelper(BitArray R, BitArray key) {
        BitArray result = new BitArray(32);

        BitArray extendBlock = extendBlock(R);
        extendBlock = xor(extendBlock, key);


        BitArray[] blocks = splitBlock(extendBlock);
        BitArray tempRes = new BitArray(32);
        for (int i = 0; i < 8; i++) {
            int a = toInt(blocks[i].get(0)) * 2 + toInt(blocks[i].get(5));
            int b = toInt(blocks[i].get(1)) * 8 + toInt(blocks[i].get(2)) * 4 + toInt(blocks[i].get(3)) * 2 + toInt(blocks[i].get(4));
            int c = blockTransformationTables[i][a][b];
            for (int j = 3; j >= 0; j--) {
                tempRes.set(i * 4 + j, (c % 2 == 1));
                c = c / 2;
            }

        }
        for (int i = 0; i < 32; i++) {
            result.set(i, tempRes.get(replaseTableP[i]));
        }
        return result;
    }


    private BitArray feistelEncrypt(BitArray input, Key key) {
        BitArray output = new BitArray(64),
                L = new BitArray(32),
                R = new BitArray(32),
                temp, f;
        for (int i = 0; i < 32; i++) {
            L.set(i, input.get(i));
            R.set(i, input.get(i + 32));
        }

        for (int i = 0; i < 16; i++) {
            BitArray subKey = key.getKey(i);
            temp = R;
            f = feistelHelper(R, subKey);
            R = xor(L, f);
            L = temp;
        }

        for (int i = 0; i < 32; i++) {
            output.set(i, L.get(i));
            output.set(i + 32, R.get(i));
        }

        return output;
    }

    private BitArray feistelDecrypt(BitArray b, Key key) {
        BitArray output = new BitArray(64),
                L = new BitArray(32),
                R = new BitArray(32),
                temp, f;
        for (int i = 0; i < 32; i++) {
            L.set(i, b.get(i));
            R.set(i, b.get(i + 32));
        }

        for (int i = 0; i < 16; i++) {
            BitArray subKey = key.getKey(15 - i);
            temp = L;
            f = feistelHelper(L, subKey);
            L = xor(R, f);
            R = temp;
        }

        for (int i = 0; i < 32; i++) {
            output.set(i, L.get(i));
            output.set(i + 32, R.get(i));
        }
        return output;
    }

    private BitArray replaseS(BitArray input) {
        BitArray output = new BitArray(input.length());
        for (int i = 0; i < 64; i++) {
            output.set(i, input.get(replaseTable1[i]));
        }
        return output;
    }

    private BitArray replaseB(BitArray input) {
        BitArray output = new BitArray(input.length());
        for (int i = 0; i < 64; i++) {
            output.set(replaseTable1[i], input.get(i));
        }
        return output;
    }

    public String encrypt(String input, String keyS, String initStr) {
        Key key = new Key(keyS);

        while (input.length() % 8 != 0)
            input = input.concat(String.valueOf('\0'));
        String output = "";

        BitArray previousEncryptedBlock = new BitArray(64, initStr.getBytes());


        for (int i = 0; i < input.length() / 8; i++) {
            BitArray arr = new BitArray(64, input.substring(i * 8, (i + 1) * 8).getBytes());

            arr = xor(previousEncryptedBlock, arr);

            arr = replaseS(arr);
            arr = feistelEncrypt(arr, key);
            arr = replaseB(arr);

            previousEncryptedBlock = arr;
            output = output.concat(new String(arr.toByteArray()));
        }

        return output;
    }

    public String decrypt(String input, String keyS, String initStr) {
        if (input.length() % 8 != 0)
            throw new IllegalArgumentException("Input of incorrect size.");

        Key key = new Key(keyS);
        String result = "";

        BitArray previousEncryptedBlock = new BitArray(64, initStr.getBytes());

        for (int i = 0; i < input.length() / 8; i++) {
            if (i != 0)
                previousEncryptedBlock = new BitArray(64, input.substring((i - 1) * 8, (i) * 8).getBytes());

            BitArray arr = new BitArray(64, input.substring(i * 8, (i + 1) * 8).getBytes());

            arr = replaseS(arr);
            arr = feistelDecrypt(arr, key);
            arr = replaseB(arr);

            arr = xor(previousEncryptedBlock, arr);
            result = result.concat(new String(arr.toByteArray()));
        }

        return result;
    }

}