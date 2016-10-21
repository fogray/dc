package com.dc.api.jersey.exec.container;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.container.ResizeContainerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

public class ResizeContainerCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<ResizeContainerCmd, Void>{

	public ResizeContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public Void exec(ResizeContainerCmd command) {
		WebTarget webTarget = getBaseResource().path("/containers/{id}/resize")
					.resolveTemplate("id", command.getContainerId())
					.queryParam("h", command.getHeight())
					.queryParam("w", command.getWidth());
		
        webTarget.request().accept(MediaType.APPLICATION_JSON).post(null).close();
        return null;
	}
	
}
