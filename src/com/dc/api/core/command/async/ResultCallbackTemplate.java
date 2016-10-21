package com.dc.api.core.command.async;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.annotation.CheckForNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dc.api.core.command.async.ResultCallback;
import com.google.common.base.Throwables;

public abstract class ResultCallbackTemplate<RC_T extends ResultCallback<A_RES_T>, A_RES_T> implements
        ResultCallback<A_RES_T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultCallbackTemplate.class);

    private final CountDownLatch started = new CountDownLatch(1);

    private final CountDownLatch completed = new CountDownLatch(1);

    private Closeable stream;

    private boolean closed = false;

    private Throwable firstError = null;

    @Override
    public void onStart(Closeable stream) {
        this.stream = stream;
        this.closed = false;
        started.countDown();
    }

    @Override
    public void onError(Throwable throwable) {

        if (closed) return;

        if (this.firstError == null) {
            this.firstError = throwable;
        }

        try {
            LOGGER.error("Error during callback", throwable);
        } finally {
            try {
                close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onComplete() {
        try {
            close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        closed = true;
        if (stream != null) {
            stream.close();
        }
        completed.countDown();
    }

    @SuppressWarnings("unchecked")
    public RC_T awaitCompletion() throws InterruptedException {
        completed.await();
        // eventually (re)throws RuntimeException
        getFirstError();
        return (RC_T) this;
    }

    public boolean awaitCompletion(long timeout, TimeUnit timeUnit) throws InterruptedException {
        return completed.await(timeout, timeUnit);
    }

    @SuppressWarnings("unchecked")
    public RC_T awaitStarted() throws InterruptedException {
        started.await();
        return (RC_T) this;
    }

    public boolean awaitStarted(long timeout, TimeUnit timeUnit) throws InterruptedException {
        return started.await(timeout, timeUnit);
    }

    @CheckForNull
    protected RuntimeException getFirstError() {
        if (firstError != null) {
            // this call throws a RuntimeException
            return Throwables.propagate(firstError);
        } else {
            return null;
        }
    }
}
