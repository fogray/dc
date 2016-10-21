package com.dc.api.jersey.async;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

public class WrappedResponseInputStream extends InputStream {

    private Response response;

    private InputStream delegate;

    private boolean closed = false;

    public WrappedResponseInputStream(Response response) {
        this.response = response;
        this.delegate = response.readEntity(InputStream.class);
    }

    public int read() throws IOException {
        return delegate.read();
    }

    public int hashCode() {
        return delegate.hashCode();
    }

    public int read(byte[] b) throws IOException {
        return delegate.read(b);
    }

    public boolean equals(Object obj) {
        return delegate.equals(obj);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        return delegate.read(b, off, len);
    }

    public long skip(long n) throws IOException {
        return delegate.skip(n);
    }

    public int available() throws IOException {
        return delegate.available();
    }

    public void close() throws IOException {
        closed = true;
        response.close();
        delegate.close();
    }

    public void mark(int readlimit) {
        delegate.mark(readlimit);
    }

    public void reset() throws IOException {
        delegate.reset();
    }

    public boolean markSupported() {
        return delegate.markSupported();
    }

    public boolean isClosed() {
        return closed;
    }

}