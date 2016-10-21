package com.dc.api.jersey.exec.task;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.task.InspectTaskCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONObject;

public class InspectTaskCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<InspectTaskCmd, JSONObject>{

	public InspectTaskCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public JSONObject exec(InspectTaskCmd command) {
		WebTarget webTarget = getBaseResource().path("/tasks/").path(command.getTaskId());
        String ret = webTarget.request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		return JSONObject.fromObject(ret);
	}
	
}
