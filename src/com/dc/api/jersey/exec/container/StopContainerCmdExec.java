package com.dc.api.jersey.exec.container;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.container.StopContainerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

public class StopContainerCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<StopContainerCmd, Void>{

	public StopContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public Void exec(StopContainerCmd command) {
		WebTarget webTarget = getBaseResource().path("/containers/{id}/stop")
					.resolveTemplate("id", command.getContainerId());
		if (command.getWaitSeconds() != null) {
			webTarget = webTarget.queryParam("t", command.getWaitSeconds());
		}
		
        webTarget.request().accept(MediaType.APPLICATION_JSON).post(null).close();
        return null;
	}
	
}
