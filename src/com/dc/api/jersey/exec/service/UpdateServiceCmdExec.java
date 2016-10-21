package com.dc.api.jersey.exec.service;

import static javax.ws.rs.client.Entity.entity;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dc.api.command.service.UpdateServiceCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.core.model.HttpStatus;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONObject;

public class UpdateServiceCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<UpdateServiceCmd, JSONObject>{

	public UpdateServiceCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public JSONObject exec(UpdateServiceCmd command) {
		WebTarget webTarget = getBaseResource().path("/services/{id}/update")
					.resolveTemplate("id", command.getServiceId());
		webTarget = webTarget.queryParam("version", Integer.parseInt(command.getVersion()));
		Response response = webTarget.request().accept(MediaType.APPLICATION_JSON)
        		.post(entity(command.getConf(), MediaType.APPLICATION_JSON));
		String ret = response.readEntity(String.class);
		if (ret == null || "".equals(ret)) {
			HttpStatus hs = new HttpStatus();
			hs.setStatusCode(response.getStatusInfo().getStatusCode());
			hs.setStatus(response.getStatusInfo().getReasonPhrase());
			return JSONObject.fromObject(hs);
		}
        return JSONObject.fromObject(ret);
	}
}
