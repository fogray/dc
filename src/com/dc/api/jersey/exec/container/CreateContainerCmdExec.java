package com.dc.api.jersey.exec.container;

import static javax.ws.rs.client.Entity.entity;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.container.CreateContainerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONObject;

public class CreateContainerCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<CreateContainerCmd, JSONObject>{

	public CreateContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public JSONObject exec(CreateContainerCmd command) {
		WebTarget webTarget = getBaseResource().path("/containers/create");
		
		if (command.getName() !=null && !"".equals(command.getName())) {
			webTarget = webTarget.queryParam("name", command.getName());
		}
		
        return webTarget.request().accept(MediaType.APPLICATION_JSON)
                .post(entity(command.getSettings(), MediaType.APPLICATION_JSON), JSONObject.class);
	}
	
}
