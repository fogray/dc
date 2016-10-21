package com.dc.api.core.command.async;

import java.io.Closeable;

public interface ResultCallback<A_RES_T> extends Closeable {
    void onStart(Closeable closeable);

    void onNext(A_RES_T object);

    void onError(Throwable throwable);

    void onComplete();
}
