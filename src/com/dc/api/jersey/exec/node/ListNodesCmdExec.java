package com.dc.api.jersey.exec.node;

import static com.google.common.net.UrlEscapers.urlPathSegmentEscaper;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.node.ListNodesCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONArray;

public class ListNodesCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<ListNodesCmd, JSONArray>{

	public ListNodesCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public JSONArray exec(ListNodesCmd command) {
		WebTarget webTarget = getBaseResource().path("/nodes");

        if (command.getFilters() != null && !command.getFilters().isEmpty()) {
            webTarget = webTarget
                    .queryParam("filters", urlPathSegmentEscaper().escape(command.getFilters().toString()));
        }

        String list = webTarget.request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		return JSONArray.fromObject(list);
	}
	
}
