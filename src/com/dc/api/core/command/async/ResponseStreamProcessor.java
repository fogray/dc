package com.dc.api.core.command.async;

import java.io.InputStream;

import com.dc.api.core.command.async.ResultCallback;

public interface ResponseStreamProcessor<T> {

    void processResponseStream(InputStream response, ResultCallback<T> resultCallback);

}
