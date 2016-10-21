package com.dc.api.jersey.exec.container;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.container.StartContainerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

public class StartContainerCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<StartContainerCmd, Void>{

	public StartContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public Void exec(StartContainerCmd command) {
		WebTarget webTarget = getBaseResource().path("/containers/{id}/start")
					.resolveTemplate("id", command.getContainerId());
		if (command.getDetachKeys() != null && !"".equals(command.getDetachKeys())) {
			webTarget = webTarget.queryParam("detachKeys", command.getDetachKeys());
		}
		
        webTarget.request().accept(MediaType.APPLICATION_JSON).post(null).close();
        return null;
	}
	
}
