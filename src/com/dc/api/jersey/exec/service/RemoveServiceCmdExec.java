package com.dc.api.jersey.exec.service;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dc.api.command.service.RemoveServiceCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.core.model.HttpStatus;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONObject;

public class RemoveServiceCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<RemoveServiceCmd, JSONObject>{

	public RemoveServiceCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public JSONObject exec(RemoveServiceCmd command) {
		WebTarget webTarget = getBaseResource().path("/services/").path(command.getServiceId());
        Response resp = webTarget.request().accept(MediaType.APPLICATION_JSON).delete();
        String ret = resp.readEntity(String.class);
        if (ret == null || "".equals(ret)) {
			HttpStatus hs = new HttpStatus();
			hs.setStatusCode(resp.getStatusInfo().getStatusCode());
			hs.setStatus(resp.getStatusInfo().getReasonPhrase());
			return JSONObject.fromObject(hs);
		}
        return JSONObject.fromObject(ret);
	}
}
