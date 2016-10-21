package com.dc.api.jersey.exec.container;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.container.TopContainerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONObject;

public class TopContainerCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<TopContainerCmd, JSONObject>{

	public TopContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}
	
	@Override
	public JSONObject exec(TopContainerCmd command) {
		
		WebTarget webTarget = getBaseResource().path("/containers/{id}/top")
				.resolveTemplate("id", command.getContainerId())
				.queryParam("ps_args", command.getPsArgs());

        String ret = webTarget.request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		return JSONObject.fromObject(ret);
	}
	
}
