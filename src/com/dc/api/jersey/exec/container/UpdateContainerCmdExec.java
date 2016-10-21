package com.dc.api.jersey.exec.container;

import static javax.ws.rs.client.Entity.entity;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dc.api.command.container.UpdateContainerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONObject;

public class UpdateContainerCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<UpdateContainerCmd, JSONObject>{

	public UpdateContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public JSONObject exec(UpdateContainerCmd command) {
		WebTarget webTarget = getBaseResource().path("/containers/{id}/update")
				.resolveTemplate("id", command.getContainerId());
		Response response = webTarget.request().accept(MediaType.APPLICATION_JSON)
									.post(entity(command.getSettings(), MediaType.APPLICATION_JSON));
        String ret = response.readEntity(String.class);
        response.close();
        return JSONObject.fromObject(ret);
	}
}
