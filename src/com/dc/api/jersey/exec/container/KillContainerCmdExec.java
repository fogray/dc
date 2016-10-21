package com.dc.api.jersey.exec.container;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.container.KillContainerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

public class KillContainerCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<KillContainerCmd, Void>{

	public KillContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public Void exec(KillContainerCmd command) {
		WebTarget webTarget = getBaseResource().path("/containers/{id}/kill")
					.resolveTemplate("id", command.getContainerId());
		if (command.getSignal() != null) {
			webTarget = webTarget.queryParam("signal", command.getSignal());
		}
		
        webTarget.request().accept(MediaType.APPLICATION_JSON).post(null).close();
        return null;
	}
	
}
