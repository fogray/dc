package com.dc.api.core.command.async;

import java.io.IOException;
import java.io.InputStream;

import com.dc.api.command.service.model.Frame;

public class FrameStreamProcessor implements ResponseStreamProcessor<Frame> {

    @Override
    public void processResponseStream(InputStream response, ResultCallback<Frame> resultCallback) {
        resultCallback.onStart(response);

        FrameReader frameReader = new FrameReader(response);
        try {

            Frame frame = frameReader.readFrame();
            while (frame != null) {
                try {
                    resultCallback.onNext(frame);
                } catch (Exception e) {
                    resultCallback.onError(e);
                } finally {
                    frame = frameReader.readFrame();
                }
            }
        } catch (Throwable t) {
            resultCallback.onError(t);
        } finally {
            try {
                frameReader.close();
                response.close();
            } catch (IOException e) {
                resultCallback.onError(e);
            } finally {
                resultCallback.onComplete();
            }
        }
    }
}
