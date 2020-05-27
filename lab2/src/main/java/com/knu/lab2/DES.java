package com.knu.lab2;


import java.util.Arrays;
import java.util.BitSet;

import static com.knu.lab2.Constants.*;

public class DES {

    private BitSet[] splitBlock(BitSet E) {
        BitSet[] blocks = new BitSet[8];

        for (int i = 0; i < 8; i++) {
            blocks[i] = new BitSet(6);
            for (int j = 0; j < 6; j++) {
                blocks[i].set(j, E.get(i * 6 + j));
            }
        }
        return blocks;
    }

    private BitSet extendBlock(BitSet R) {
        BitSet E = new BitSet(48);
        for (int i = 0; i < 48; i++) {
            E.set(i, R.get(extensionTable[i]));
        }
        return E;
    }

    private BitSet feistelHelper(BitSet R, BitSet key) {
        BitSet result = new BitSet(32);

        BitSet extendBlock = extendBlock(R);
        extendBlock.xor(BitSet.valueOf(Arrays.copyOf(key.toByteArray(), extendBlock.length())));

        BitSet[] blocks = splitBlock(extendBlock);
        BitSet tempRes = new BitSet(32);
        for (int i = 0; i < 8; i++) {
            int a = Util.toInt(blocks[i].get(0)) * 2 + Util.toInt(blocks[i].get(5));
            int b = Util.toInt(blocks[i].get(1)) * 8 + Util.toInt(blocks[i].get(2)) * 4 + Util.toInt(blocks[i].get(3)) * 2 + Util.toInt(blocks[i].get(4));
            int c = blockTransformationTables[i][a][b];
            for (int j = 3; j >= 0; j--) {
                tempRes.set(i * 4 + j, (c % 2 == 1));
                c = c / 2;
            }

        }
        for (int i = 0; i < 32; i++) {
            result.set(i, tempRes.get(replaceTableP[i]));
        }
        return result;
    }


    private BitSet feistelEncrypt(BitSet input, Key key) {
        BitSet output = new BitSet(64),
                L = new BitSet(32),
                R = new BitSet(32),
                temp, f;

        initArrays(L, R, input);

        for (int i = 0; i < 16; i++) {
            BitSet subKey = key.getKey(i);
            temp = R;

            f = feistelHelper(R, subKey);
            R = L;
            R.xor(f);
            L = temp;
        }

        for (int i = 0; i < 32; i++) {
            output.set(i, L.get(i));
            output.set(i + 32, R.get(i));
        }

        return output;
    }

    private BitSet feistelDecrypt(BitSet b, Key key) {
        BitSet output = new BitSet(64),
                L = new BitSet(32),
                R = new BitSet(32),
                temp, f;
        initArrays(L, R, b);

        for (int i = 0; i < 16; i++) {
            BitSet subKey = key.getKey(15 - i);
            temp = L;
            f = feistelHelper(L, subKey);
            L = R;
            L.xor(f);
            R = temp;
        }

        for (int i = 0; i < 32; i++) {
            output.set(i, L.get(i));
            output.set(i + 32, R.get(i));
        }
        return output;
    }

    private BitSet replaceS(BitSet input) {
        BitSet output = new BitSet(input.length());
        for (int i = 0; i < 64; i++) {
            output.set(i, input.get(replaseTable1[i]));
        }
        return output;
    }

    private BitSet replaceB(BitSet input) {
        BitSet output = new BitSet(input.length());
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

        BitSet previousEncryptedBlock = BitSet.valueOf(Arrays.copyOf(initStr.getBytes(), 64));


        for (int i = 0; i < input.length() / 8; i++) {
            byte[] tmp = Arrays.copyOf(input.substring(i * 8, (i + 1) * 8).getBytes(), 64);
            BitSet arr = BitSet.valueOf(tmp);

            arr.xor(previousEncryptedBlock);

            arr = replaceS(arr);
            arr = feistelEncrypt(arr, key);
            arr = replaceB(arr);

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

        BitSet previousEncryptedBlock = BitSet.valueOf(Arrays.copyOf(initStr.getBytes(), 64));

        for (int i = 0; i < input.length() / 8; i++) {
            if (i != 0)
                previousEncryptedBlock = BitSet.valueOf(input.substring((i - 1) * 8, (i) * 8).getBytes());

            BitSet arr = BitSet.valueOf(input.substring(i * 8, (i + 1) * 8).getBytes());

            arr = replaceS(arr);
            arr = feistelDecrypt(arr, key);
            arr = replaceB(arr);

            arr.xor(previousEncryptedBlock);
            result = result.concat(new String(arr.toByteArray()));
        }

        return result;
    }

    private void initArrays(BitSet L, BitSet R, BitSet input) {
        for (int i = 0; i < 32; i++) {
            L.set(i, input.get(i));
            R.set(i, input.get(i + 32));
        }
    }

}