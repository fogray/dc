package com.dc.api.jersey.exec.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dc.api.command.service.model.Frame;
import com.dc.api.core.command.async.ResultCallbackTemplate;

public class AttachContainerResultCallback extends ResultCallbackTemplate<AttachContainerResultCallback, Frame> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttachContainerResultCallback.class);

    @Override
    public void onNext(Frame item) {
        LOGGER.debug(item.toString());
    }
}
