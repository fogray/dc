package com.dc.api.jersey.exec.container;

import javax.ws.rs.client.WebTarget;

import com.dc.api.command.container.LogContainerCmd;
import com.dc.api.command.service.model.Frame;
import com.dc.api.core.command.async.AsyncDockerCmdExec;
import com.dc.api.core.command.async.FrameStreamProcessor;
import com.dc.api.core.command.async.ResultCallback;
import com.dc.api.jersey.AbstrAsyncDockerCmdExec;
import com.dc.api.jersey.async.AbstractCallbackNotifier;
import com.dc.api.jersey.async.GETCallbackNotifier;

public class LogContainerCmdExec extends AbstrAsyncDockerCmdExec<LogContainerCmd, Frame>
	implements AsyncDockerCmdExec<LogContainerCmd, Frame>{

	public LogContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	//TODO
	@Override
	protected AbstractCallbackNotifier<Frame> callbackNotifier(LogContainerCmd command,
			ResultCallback<Frame> resultCallback) {

        WebTarget webTarget = getBaseResource().path("/containers/{id}/logs")
        				.resolveTemplate("id", command.getContainerId());

        if (command.getTail() != null) {
            webTarget = webTarget.queryParam("tail", command.getTail());
        }

        if (command.getSinceTime() != null) {
            webTarget = webTarget.queryParam("since", command.getSinceTime());
        }

        webTarget = booleanQueryParam(webTarget, "details", command.isShowDetails());
        webTarget = booleanQueryParam(webTarget, "timestamps", command.isShowTime());
        webTarget = booleanQueryParam(webTarget, "stdout", command.isStdout());
        webTarget = booleanQueryParam(webTarget, "stderr", command.isStderr());
        webTarget = booleanQueryParam(webTarget, "follow", command.isFollow());

        return new GETCallbackNotifier<Frame>(new FrameStreamProcessor(), resultCallback, webTarget.request());
	}

}
