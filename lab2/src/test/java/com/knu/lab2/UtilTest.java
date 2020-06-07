package com.knu.lab2;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void toInt() {
        assertEquals(1, Util.toInt(true));
        assertEquals(0, Util.toInt(false));
    }
}