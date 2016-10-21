package com.dc.api.jersey.exec.container;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.container.PauseContainerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

public class PauseContainerCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<PauseContainerCmd, Void>{

	public PauseContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public Void exec(PauseContainerCmd command) {
		WebTarget webTarget = getBaseResource().path("/containers/{id}/pause")
					.resolveTemplate("id", command.getContainerId());
		
        webTarget.request().accept(MediaType.APPLICATION_JSON).post(null).close();
        return null;
	}
	
}
