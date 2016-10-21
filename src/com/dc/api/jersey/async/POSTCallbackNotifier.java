package com.dc.api.jersey.async;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import com.dc.api.core.command.async.ResponseStreamProcessor;
import com.dc.api.core.command.async.ResultCallback;

public class POSTCallbackNotifier<T> extends AbstractCallbackNotifier<T> {

    Entity<?> entity = null;

    public POSTCallbackNotifier(ResponseStreamProcessor<T> responseStreamProcessor, ResultCallback<T> resultCallback,
            Builder requestBuilder, Entity<?> entity) {
        super(responseStreamProcessor, resultCallback, requestBuilder);
        this.entity = entity;
    }

    protected Response response() {
        return requestBuilder.post(entity, Response.class);
    }

}
