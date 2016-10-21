package com.dc.api.command.service.model;

import java.io.Serializable;
import java.util.Arrays;

public class Frame implements Serializable {
    private static final long serialVersionUID = 1L;

    private final StreamType streamType;

    private final byte[] payload;

    public Frame(StreamType streamType, byte[] payload) {
        this.streamType = streamType;
        this.payload = payload;
    }

    public StreamType getStreamType() {
        return streamType;
    }

    public byte[] getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", streamType, new String(payload).trim());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Frame frame = (Frame) o;

        return streamType == frame.streamType && Arrays.equals(payload, frame.payload);

    }

    @Override
    public int hashCode() {
        int result = streamType.hashCode();
        result = 31 * result + Arrays.hashCode(payload);
        return result;
    }
    
    public enum StreamType {
        STDIN, STDOUT, STDERR, RAW
    }
}
