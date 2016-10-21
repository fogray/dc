package com.dc.api.jersey.exec.container;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.container.InspectContainerChangesCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONArray;

public class InspectContainerChangesCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<InspectContainerChangesCmd, JSONArray>{

	public InspectContainerChangesCmdExec(WebTarget baseResource) {
		super(baseResource);
	}
	
	@Override
	public JSONArray exec(InspectContainerChangesCmd command) {
		
		WebTarget webTarget = getBaseResource().path("/containers/{id}/changes")
					.resolveTemplate("id", command.getContainerId());

        String list = webTarget.request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		return JSONArray.fromObject(list);
	}
	
}
