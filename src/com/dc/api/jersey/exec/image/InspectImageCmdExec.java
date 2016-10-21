package com.dc.api.jersey.exec.image;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.image.InspectImageCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONObject;

public class InspectImageCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<InspectImageCmd, JSONObject>{

	public InspectImageCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public JSONObject exec(InspectImageCmd command) {
		WebTarget webTarget = getBaseResource().path("/images/{id}/json")
								.resolveTemplate("id", command.getImageId());
        String ret = webTarget.request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		return JSONObject.fromObject(ret);
	}
	
}
