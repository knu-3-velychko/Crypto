package com.knu.lab2;

import java.util.BitSet;

import static com.knu.lab2.Constants.*;

public class Key {
    private final BitSet newKey = new BitSet(64);
    private final BitSet[] keys = new BitSet[16];

    public Key(String keyS) {
        if (keyS.length() != 7)
            throw new IllegalArgumentException("Key has incorrect size");

        BitSet key = BitSet.valueOf(keyS.getBytes());


        for (int i = 0; i < 8; i++) {
            boolean b1 = true;
            for (int j = 0; j < 7; j++) {
                newKey.set(i * 8 + j, key.get(i * 7 + j));
                b1 = b1 ^ key.get(i * 7 + j);
            }
            newKey.set(i * 7 + 7, b1);
        }
        initSubKeys();
    }

    private void leftShift(BitSet bitArray, int a) {
        while (a != 0) {
            a--;
            boolean b = bitArray.get(0);
            for (int i = 0; i < 27; i++) {
                bitArray.set(i, bitArray.get(i + 1));
            }
            bitArray.set(27, b);
        }
    }


    private void initSubKeys() {
        BitSet C = new BitSet(28), D = new BitSet(28);

        for (int i = 0; i < 28; i++) {
            C.set(i, newKey.get(replaceTableC[i]));
            D.set(i, newKey.get(replaceTableD[i]));
        }

        for (int i = 0; i < 16; i++) {
            leftShift(C, shiftTable[i]);
            leftShift(D, shiftTable[i]);
            keys[i] = new BitSet(48);
            for (int j = 0; j < 48; j++) {
                if (keyCreateTable[i] < 28)
                    keys[i].set(i, C.get(keyCreateTable[i]));
                else
                    keys[i].set(i, D.get(keyCreateTable[i] - 28));
            }
        }

    }

    public BitSet getKey(int i) {
        return keys[i];
    }
}
