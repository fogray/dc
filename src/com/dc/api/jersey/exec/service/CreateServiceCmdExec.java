package com.dc.api.jersey.exec.service;

import static javax.ws.rs.client.Entity.entity;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.service.CreateServiceCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONObject;

public class CreateServiceCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<CreateServiceCmd, JSONObject>{

	public CreateServiceCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public JSONObject exec(CreateServiceCmd command) {
		WebTarget webTarget = getBaseResource().path("/services/create");
		
        String ret = webTarget.request().accept(MediaType.APPLICATION_JSON)
                .post(entity(command.getSettings(), MediaType.APPLICATION_JSON), String.class);
        return JSONObject.fromObject(ret);
	}
	
}
