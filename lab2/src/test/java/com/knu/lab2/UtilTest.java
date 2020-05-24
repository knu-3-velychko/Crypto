package com.knu.lab2;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class UtilTest {

    @Test
    public void rotateLeft() {
        assertEquals(8, Util.rotateLeft(2, 2));
    }

    @Test
    public void rotateRight() {
        assertEquals(2, Util.rotateRight(8, 2));
    }

    @Test
    public void longToByteArray() {
        long value = 9910;
        byte[] bytes = {0, 0, 0, 0, 0, 0, 38, -74};
        for (byte i : Util.longToByteArray(value)) {
            System.out.println(i);
        }
        assertArrayEquals(bytes, Util.longToByteArray(value));
    }

    @Test
    public void byteArrayToInt() {
        byte[] bytes = {10, 20, 30, 40, 50, 60, 70, 80};
        assertEquals(169090600, Util.byteArrayToInt(bytes, 0, 4));
    }

    @Test
    public void byteArrayToIntArray() {
        byte[] bytes = {10, 20, 30, 40};
        assertArrayEquals(new int[]{169090600}, Util.byteArrayToIntArray(bytes));
    }

    @Test
    public void intArrayToHexString() {
        int[] ints = {10, 20, 30, 40};
        assertEquals("0000000a000000140000001e00000028", Util.intArrayToHexString(ints));
    }
}