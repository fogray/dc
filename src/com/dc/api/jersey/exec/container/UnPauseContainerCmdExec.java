package com.dc.api.jersey.exec.container;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.container.UnPauseContainerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

public class UnPauseContainerCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<UnPauseContainerCmd, Void>{

	public UnPauseContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public Void exec(UnPauseContainerCmd command) {
		WebTarget webTarget = getBaseResource().path("/containers/{id}/unpause")
					.resolveTemplate("id", command.getContainerId());
		
        webTarget.request().accept(MediaType.APPLICATION_JSON).post(null).close();
        return null;
	}
	
}
