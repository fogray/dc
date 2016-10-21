package com.dc.api.jersey.exec.container;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.container.InspectContainerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONObject;

public class InspectContainerCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<InspectContainerCmd, JSONObject>{

	public InspectContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public JSONObject exec(InspectContainerCmd command) {
		WebTarget webTarget = getBaseResource().path("/containers/{id}/json")
								.resolveTemplate("id", command.getContainerId())
								.queryParam("size", command.isShowSize());
        String ret = webTarget.request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		return JSONObject.fromObject(ret);
	}
	
}
