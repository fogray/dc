package com.dc.api.jersey.exec.node;

import static javax.ws.rs.client.Entity.entity;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.node.UpdateNodeCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

public class UpdateNodeCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<UpdateNodeCmd, Void>{

	public UpdateNodeCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public Void exec(UpdateNodeCmd command) {
		WebTarget webTarget = getBaseResource().path("/nodes/").path(command.getNodeId());
		webTarget = webTarget.queryParam("version", command.getVersion());
        webTarget.request().accept(MediaType.APPLICATION_JSON)
        		.post(entity(command.getConf(), MediaType.APPLICATION_JSON)).close();
		return null;
	}
	
}
