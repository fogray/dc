package com.dc.api.jersey.exec.container;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dc.api.command.container.StatsContainerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONObject;

public class StatsContainerCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<StatsContainerCmd, JSONObject>{

	public StatsContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	//TODO 
	@Override
	public JSONObject exec(StatsContainerCmd command) {
		WebTarget webTarget = getBaseResource().path("/containers/{id}/stats")
								.resolveTemplate("id", command.getContainerId());
		if (command.isStream() != null) {
			webTarget.queryParam("stream", command.isStream());
		}
		Response response = webTarget.request().accept(MediaType.APPLICATION_JSON).get();
        String ret = response.readEntity(String.class);
        response.close();
		return JSONObject.fromObject(ret);
	}
	
}
