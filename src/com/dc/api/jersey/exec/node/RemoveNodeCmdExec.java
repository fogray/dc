package com.dc.api.jersey.exec.node;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.node.RemoveNodeCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

public class RemoveNodeCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<RemoveNodeCmd, Void>{

	public RemoveNodeCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public Void exec(RemoveNodeCmd command) {
		WebTarget webTarget = getBaseResource().path("/nodes/").path(command.getNodeId());
		booleanQueryParam(webTarget, "force", command.getForce());
		
        webTarget.request().accept(MediaType.APPLICATION_JSON).delete().close();
		return null;
	}
	
}
