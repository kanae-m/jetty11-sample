package com.example.chapter5.section3.sample3;

import org.eclipse.jetty.util.Callback;

import java.nio.ByteBuffer;

@SuppressWarnings("squid:S1068")
public class Chunk {

    private final ByteBuffer buffer;
    private final Callback callback;

    Chunk(ByteBuffer buffer, Callback callback) {
        this.buffer = buffer;
        this.callback = callback;
    }

}
