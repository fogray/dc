package com.dc.api.jersey.exec.node;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.node.InspectNodeCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONObject;

public class InspectNodeCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<InspectNodeCmd, JSONObject>{

	public InspectNodeCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public JSONObject exec(InspectNodeCmd command) {
		WebTarget webTarget = getBaseResource().path("/nodes/").path(command.getNodeId());
        String ret = webTarget.request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		return JSONObject.fromObject(ret);
	}
	
}
