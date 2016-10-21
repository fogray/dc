package com.dc.api.jersey.exec.container;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dc.api.command.container.WaitContainerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONObject;

public class WaitContainerCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<WaitContainerCmd, JSONObject>{

	public WaitContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public JSONObject exec(WaitContainerCmd command) {
		WebTarget webTarget = getBaseResource().path("/containers/{id}/wait")
					.resolveTemplate("id", command.getContainerId());
        Response response = webTarget.request().accept(MediaType.APPLICATION_JSON).post(null);
        String ret = response.readEntity(String.class);
        response.close();
        return JSONObject.fromObject(ret);
	}
	
}
