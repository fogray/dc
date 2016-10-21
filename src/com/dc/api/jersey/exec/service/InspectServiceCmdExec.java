package com.dc.api.jersey.exec.service;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.service.InspectServiceCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONObject;

public class InspectServiceCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<InspectServiceCmd, JSONObject>{

	public InspectServiceCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public JSONObject exec(InspectServiceCmd command) {
		WebTarget webTarget = getBaseResource().path("/services/").path(command.getServiceId());
        String ret = webTarget.request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		return JSONObject.fromObject(ret);
	}
	
}
