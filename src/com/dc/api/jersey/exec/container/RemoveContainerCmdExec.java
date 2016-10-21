package com.dc.api.jersey.exec.container;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.container.RemoveContainerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

public class RemoveContainerCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<RemoveContainerCmd, Void>{

	public RemoveContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public Void exec(RemoveContainerCmd command) {
		WebTarget webTarget = getBaseResource().path("/containers/{id}")
					.resolveTemplate("id", command.getContainerId());
		if (command.isAlsoVolumes() != null) {
			webTarget = webTarget.queryParam("v", command.isAlsoVolumes());
		}
		if (command.isForce() != null) {
			webTarget = webTarget.queryParam("force", command.isForce());
		}
        webTarget.request().accept(MediaType.APPLICATION_JSON).delete().close();
        return null;
	}
	
}
