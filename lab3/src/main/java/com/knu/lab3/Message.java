package com.knu.lab3;

public class Message {
    private final byte[] a, b;

    public Message(byte[] a, byte[] b) {
        this.a = a.clone();
        this.b = b.clone();
    }

    public byte[] getA() {
        return a;
    }

    public byte[] getB() {
        return b;
    }
}
