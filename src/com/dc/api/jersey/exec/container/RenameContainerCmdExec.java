package com.dc.api.jersey.exec.container;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.container.RenameContainerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

public class RenameContainerCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<RenameContainerCmd, Void>{

	public RenameContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public Void exec(RenameContainerCmd command) {
		WebTarget webTarget = getBaseResource().path("/containers/{id}/rename")
					.resolveTemplate("id", command.getContainerId());
		if (command.getNewName() != null && !"".equals(command.getNewName())) {
			webTarget = webTarget.queryParam("name", command.getNewName());
		}
		
        webTarget.request().accept(MediaType.APPLICATION_JSON).post(null).close();
        return null;
	}
	
}
