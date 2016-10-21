package com.dc.api.jersey.exec.task;

import static com.google.common.net.UrlEscapers.urlPathSegmentEscaper;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.task.ListTasksCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONArray;

public class ListTasksCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<ListTasksCmd, JSONArray>{

	public ListTasksCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public JSONArray exec(ListTasksCmd command) {
		WebTarget webTarget = getBaseResource().path("/tasks");

        if (command.getFilters() != null && !command.getFilters().isEmpty()) {
            webTarget = webTarget
                    .queryParam("filters", urlPathSegmentEscaper().escape(command.getFilters().toString()));
        }

        String list = webTarget.request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		return JSONArray.fromObject(list);
	}
	
}
