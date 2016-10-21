package com.dc.api.jersey.exec.container;

import javax.ws.rs.client.WebTarget;

import com.dc.api.command.container.AttachContainerCmd;
import com.dc.api.command.service.model.Frame;
import com.dc.api.core.command.async.AsyncDockerCmdExec;
import com.dc.api.core.command.async.FrameStreamProcessor;
import com.dc.api.core.command.async.ResultCallback;
import com.dc.api.jersey.AbstrAsyncDockerCmdExec;
import com.dc.api.jersey.async.AbstractCallbackNotifier;
import com.dc.api.jersey.async.POSTCallbackNotifier;

public class AttachContainerCmdExec extends AbstrAsyncDockerCmdExec<AttachContainerCmd, Frame>
	implements AsyncDockerCmdExec<AttachContainerCmd, Frame>{

	public AttachContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	//TODO
	@Override
	protected AbstractCallbackNotifier<Frame> callbackNotifier(AttachContainerCmd command,
			ResultCallback<Frame> resultCallback) {

        WebTarget webTarget = getBaseResource().path("/containers/{id}/attach")
        				.resolveTemplate("id", command.getContainerId());

        if (command.getDetachKeys() != null) {
            webTarget = webTarget.queryParam("detachKeys", command.getDetachKeys());
        }

        webTarget = booleanQueryParam(webTarget, "logs", command.isLogs());
        webTarget = booleanQueryParam(webTarget, "stream ", command.isStream());
        webTarget = booleanQueryParam(webTarget, "stdin", command.isStdin());
        webTarget = booleanQueryParam(webTarget, "stdout", command.isStdout());
        webTarget = booleanQueryParam(webTarget, "stderr", command.isStderr());

        return new POSTCallbackNotifier<Frame>(new FrameStreamProcessor(), resultCallback, webTarget.request(), null);
	}

}
