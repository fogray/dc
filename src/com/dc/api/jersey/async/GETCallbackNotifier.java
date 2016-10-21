package com.dc.api.jersey.async;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import com.dc.api.core.command.async.ResponseStreamProcessor;
import com.dc.api.core.command.async.ResultCallback;

public class GETCallbackNotifier<T> extends AbstractCallbackNotifier<T> {

    public GETCallbackNotifier(ResponseStreamProcessor<T> responseStreamProcessor, ResultCallback<T> resultCallback,
            Builder requestBuilder) {
        super(responseStreamProcessor, resultCallback, requestBuilder);
    }

    protected Response response() {
        return requestBuilder.get(Response.class);
    }

}
